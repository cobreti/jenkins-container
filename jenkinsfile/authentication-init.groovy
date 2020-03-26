pipeline {
    node any
    
    stages {
        def image

        stage('checkout') {
            agent any
            steps {
                    git url: 'https://github.com/myportail/authentication'
            }
        }
        
        stage('build') {
            agent any
            steps {
                docker.build("myportail/authentication-init:1.0.${env.BUILD_ID}", "-f ./Docker/authInit/Dockerfile .")
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
