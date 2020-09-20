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
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "Success deply"'
      }
    }

  }
  environment {
    Name = 'Jindeok Jeong'
  }
}