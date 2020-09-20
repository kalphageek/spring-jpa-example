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
        sh '''def mvnHome = tool \'M3\'

stage(\'Build\') {
    withEnv(["MVN_HOME=$mvnHome"]) {
        if (isUnix()) {
            sh \'"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package\'
        } else {
            bat(/"%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }
}
'''
      }
    }

  }
  environment {
    Name = 'Jindeok Jeong'
  }
}