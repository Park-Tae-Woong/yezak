pipeline {
    agent any

    stages {
        stage('Pull Docker Compose Project') {
            steps {
                script {
                    sh "git pull https://github.com/m-ultiply/yezak-api.git"
                }
            }
        }
        stage('Run Docker Compose') {
            steps {
                script {
                    sh "docker-compose up -d"
                }
            }
        }
//         stage('Update Nginx Config') {
//             steps {
//                 script {
//                     // Here you would update the Nginx configuration to point to the new service.
//                     sh "sed -i 's/old_service/new_service/g' /etc/nginx/sites-available/default"
//                 }
//             }
//         }
//         stage('Reload Nginx') {
//             steps {
//                 script {
//                     sh "sudo systemctl reload nginx"
//                 }
//             }
//         }
//         stage('Stop Old Services') {
//             steps {
//                 script {
//                     sh "docker-compose down"
//                 }
//             }
//         }
    }
}