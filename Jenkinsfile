pipeline {
  agent {
    node {
      label 'agent-1'
    }

  }
  stages {
    stage('Source') {
      steps {
        git(url: 'git@github.com:kalphageek/spring-jpa-example.git', branch: 'master', credentialsId: 'jenkins-private-key')
      }
    }

    stage('Build') {
      steps {
        tool 'M3'
        sh 'mvn -Dmaven.test.failure.ignore clean package'
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "Success deploy"'
        echo "This is build number : ${BUILD_ID}"
      }
    }

  }
  environment {
    Name = 'Jindeok Jeong'
  }
}