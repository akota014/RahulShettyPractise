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
        
        stage('Start Grid') {
			steps{
				bat 'docker compose up -d'
			}
		}

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -DsuitexmlFile=testng-grid.xml'
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

    }
    
    post{
		always{
			bat 'docker compose down'
		}
	}
}
