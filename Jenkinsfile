
pipeline {

    agent any
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'rm -rf camel_rest; git clone https://github.com/cherepakhin/camel_rest'
            }
        }

        stage('Unit tests') {
            steps {
                sh 'pwd;cd camel_rest;./gradlew clean test --tests *Test'
            }
        }

        stage('Build bootJar') {
            steps {
                sh 'pwd;cd camel_rest;./gradlew bootJar'
            }
        }

        stage('Publish to Nexus') {
            environment {
                NEXUS_CRED = credentials('nexus_admin')
            }
            steps {
                sh 'cd camel_rest;./gradlew publish'
            }
        }
    }
}