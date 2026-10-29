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
    }
}