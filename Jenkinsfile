
pipeline {
    agent any
    parameters {
    choice(
        name: 'ANALYSIS_MODE',
        choices: ['STANDARD', 'DETAILED'],
        description: 'Select biomedical sensor analysis mode'
    )
}

    stages {
        stage('Checkout') {
            steps {
                echo "Selected Analysis Mode: ${params.ANALYSIS_MODE}"
                echo 'Checking out Biomedical Sensor Data Analyser'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Unit Testing') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'

            publishHTML(target: [
                reportDir: '.',
                reportFiles: 'Biomedical_Report.html',
                reportName: 'Biomedical Sensor Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            archiveArtifacts artifacts: 'target/*.jar, Biomedical_Report.html',
                             fingerprint: true,
                             allowEmptyArchive: true
        }

        success {
            echo 'BUILD SUCCESSFUL!'
        }

        failure {
            echo 'BUILD FAILED!'
        }
    }
}

