pipeline {
    agent any

    triggers {
        scm 'H/5 * * * *'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}