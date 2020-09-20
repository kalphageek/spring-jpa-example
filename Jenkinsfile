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
        sh 'mvn -Dmaven.test.failure.ignore clean package'
      }
    }

    stage('Deploy') {
      steps {
        echo "This is build number : ${BUILD_ID}"
      }
    }

  }
  environment {
    Name = 'Jindeok Jeong'
  }
}