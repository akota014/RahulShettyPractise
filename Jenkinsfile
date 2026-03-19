pipeline {
    agent any
    
    tools{
		git 'Git'
	}


    stages {

        stage('Clone Repo') {
            steps {
                git branch: 'main', url:'https://github.com/akota014/RahulShettyPractise.git'
            }
        }
        
        stage('Clean Old Containers') {
		    steps {
		        bat 'docker compose down'
		    }
		}
        
        stage('Start Grid') {
			steps{
				bat 'docker compose up -d'
			}
		}
		
		stage('Time to start grid'){
			steps{
				bat ':waitloop
						curl http://localhost:4444/status | find "ready"
						IF %ERRORLEVEL% NEQ 0 (
						    timeout /t 5
						    goto waitloop
						)'
			}
		}

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng-grid.xml'
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
