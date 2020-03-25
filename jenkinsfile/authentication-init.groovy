pipeline {
    agent {
        node {
            label 'docker'
        }
    }

    stages {
        def image
        
        stage('checkout') {
            agent any
            steps {
                    git url: 'https://github.com/myportail/authentication'
            }
        }
        
        stage('build') {
            agent {
                docker {
                    reuseNode: true
                }
            }
            steps {
                image = docker.build("myportail/authentication-init:1.0.${env.BUILD_ID}", "-f ./Docker/authInit/Dockerfile .")
            }
        }
        
        stage('push') {
            agent {
                docker {
                    reuseNode: true
                }
            }
            steps: {
                docker.withRegistry("", "dockerhub") {
                    image.push()
                }
            }
        }
    }
}
