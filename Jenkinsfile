pipeline {
    agent any
    
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = '73a53345-c223-4cfc-aa23-0a92815a7a22'
        DOCKER_IMAGE = 'vernere/inclass2'
        DOCKER_TAG = '1.0'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    dir('demo') {
                        if (isUnix()) {
                            sh 'mvn clean package -DskipTests'
                        } else {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    dir('demo') {
                        if (isUnix()) {
                            sh 'mvn verify'
                        } else {
                            bat 'mvn verify'
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    } else {
                        bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
    }

    post {
        always {
            junit(testResults: 'demo/target/surefire-reports/*.xml', allowEmptyResults: true)
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'demo/target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Coverage Report'
            ])
            jacoco(
                execPattern: 'demo/target/jacoco.exec',
                classPattern: 'demo/target/classes',
                sourcePattern: 'demo/src/main/java',
                inclusionPattern: '**/*.class',
                exclusionPattern: ''
            )
        }
    }
}