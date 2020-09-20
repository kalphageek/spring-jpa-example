pipeline {
    agent { node { label 'agent-1' } }
    tools {
        maven "M3"
    }
    stages {
        stage('Build') {
            environment {
                AN_ACCESS_KEY = credentials('jenkins-private-key')
            }
            steps {
                // Get some code from a GitHub repository
                git 'git@github.com:kalphageek/spring-jpa-example.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}