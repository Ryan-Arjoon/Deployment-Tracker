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
                        @echo off

                        if exist k8s\\dev\\kustomization.yaml del /f /q k8s\\dev\\kustomization.yaml

                        powershell -NoProfile -Command "$p='k8s/dev/kustomization.yml'; $tag=$env:BUILD_NUMBER; (Get-Content $p -Raw) -replace 'newTag: .*', ('newTag: ' + [char]34 + $tag + [char]34) | Set-Content $p"

                        if errorlevel 1 exit /b 1

                        kubectl kustomize k8s/dev/ >NUL

                        if errorlevel 1 exit /b 1

                        kubectl apply -k k8s/dev/

                        if errorlevel 1 exit /b 1

                        kubectl rollout status deployment/deployment-tracker -n dev --timeout=180s

                        if errorlevel 1 exit /b 1
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