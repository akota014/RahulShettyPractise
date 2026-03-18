pipeline {
    agent any


    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/akota014/RahulShettyPractise.git'
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
