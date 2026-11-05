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
                bat 'docker build -t ryanarjoon123/deployment-tracker:%BUILD_NUMBER% .'
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
                    docker login -u %DOCKERHUB_USERNAME% -p %DOCKERHUB_TOKEN%
                    docker push ryanarjoon123/deployment-tracker:%BUILD_NUMBER%
                    docker logout
                    '''
                }
            }
        }

        stage('Deploy Container to Kubernetes'){
            steps{
                withCredentials([file(
                    credentialsId: 'kubeconfig-docker-desktop',
                    variable: 'KUBECONFIG'
                )]) {
                    bat '''
                        powershell -NoProfile -Command "(Get-Content k8s/deployment.yml) -replace 'image: ryanarjoon123/deployment-tracker:.*', 'image: ryanarjoon123/deployment-tracker:%BUILD_NUMBER%' | Set-Content k8s/deployment.yml"

                        kubectl apply -f k8s/deployment.yml

                        kubectl rollout status deployment/deployment-tracker
                    '''
                }
            }
        }

        stage('Smoke Tests'){
            steps{
                bat 'curl.exe --fail http://localhost:30080/actuator/health'
            }
        }
    }
}