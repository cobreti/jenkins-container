pipeline {
    stages {
        stage('checkout') {
            agent any
            steps {
                    git url: 'https://github.com/myportail/authentication'
            }
        }
        
        stage('build') {
            agent any
            steps {
                def image = docker.build("myportail/authentication-init:1.0.${env.BUILD_ID}", "-f ./Docker/authInit/Dockerfile .")
            }
        }
        
        stage('push') {
            agent any
            steps: {
                docker.withRegistry("", "dockerhub") {
                    image.push()
                }
            }
        }
    }
}
