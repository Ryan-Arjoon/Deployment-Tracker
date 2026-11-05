pipeline{
    agent any

    stages{
        stage('Test'){
            steps{
                script{
                    if (isUnix()){
                        sh 'chmod +x mvnw && ./mvnw test'
                    }
                    else{
                        bat 'mvnw.cmd test'
                    }
                }
            }
        }

        stage("Build Docker Image"){
            steps{
                bat 'mvnw.cmd package -DskipTests'
                bat 'docker build -t ryanarjoon123/deployment-tracker:1.1 .'
            }
        }

        stage("Push Docker Image"){
            steps{
                withCredentials([
                    usernamePassword(
                        credentialsId: "dockerhub-credentials",
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: "DOCKERHUB_TOKEN"
                    )
                ]) {
                    bat '''
                    docker login -u %DOCKER_USER% -p %DCOKER_TOKEN%
                    docker push ryanarjoon123/deployment-tracker:%BUILD_NUMBER%
                    docker logout
                    '''
                }
            }
        }

        stage('Test Kubernetes Access'){
            steps{
                withCredentials([file(
                    credentialsId: 'kubeconfig-docker-desktop',
                    variable: 'KUBECONFIG'
                )]) {
                    bat 'kubectl get nodes'
                    bat 'kubectl get pods'
                }
            }
        }
    }
}