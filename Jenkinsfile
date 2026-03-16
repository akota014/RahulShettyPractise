pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Java'
    }

    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/username/selenium-project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

    }
}
