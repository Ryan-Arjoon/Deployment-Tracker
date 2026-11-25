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
                        powershell -NoProfile -Command "(Get-Content k8s/dev/kustomization.yml) -replace  'newTag: .*', 'newTag: \\"%BUILD_NUMBER%\\"' | Set-Content k8s/dev/kustomization.yaml"

                        kubectl apply -k k8s/dev/

                        kubectl rollout status deployment/deployment-tracker -n dev --timeout=180s
                    '''
                }
            }
        }

        stage('Smoke Tests'){
            steps{
                bat 'curl.exe --fail http://localhost:30081/actuator/health'
            }
        }

        stage('Commit Deployment Manifest') {
            steps {
                withCredentials([gitUsernamePassword(
                    credentialsId: '27f6f692-e06b-4581-b033-12df4cd1f639',
                    gitToolName: 'Default'
                )]) {
                    bat '''
                        @echo off

                        git config user.name "Jenkins"
                        git config user.email "jenkins@users.noreply.github.com"

                        git add k8s/dev/kustomization.yml

                        git diff --cached --quiet
                        if errorlevel 1 (
                            git commit -m "[skip ci] Deploy image build %BUILD_NUMBER%"
                            git push origin HEAD:dev
                        ) else (
                            echo No manifest changes to commit. 
                        )
                    '''
                }
            }
        }
    }
}