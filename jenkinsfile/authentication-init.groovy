pipeline {
    agent {
        node {   
            def image
            
            stage('checkout') {
                git url: 'https://github.com/myportail/authentication'
            }
            
            stage('build') {
                image = docker.build("myportail/authentication-init:1.0.${env.BUILD_ID}", "-f ./Docker/authInit/Dockerfile .")
            }
            
            stage('push') {
                docker.withRegistry("", "dockerhub") {
                    image.push()
                }
            }
        }
    }
}
