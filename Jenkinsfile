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
    stage("Starting API Server"){
      withEnv(['BUILD_ID=dontkill']) {
        sh '''
          JAR_NAME=$(ls target/*.jar | tail -n 1)
          echo "> jar name : $JAR_NAME"
          echo "> checking pid on the running process"
          CURRENT_PID=$(ps -ef | grep java | grep $JAR_NAME | awk '{print $2}')
          if [ -z $CURRENT_PID ];
          then
            echo "> there is no running process."
          else
            echo "> kill -9 $CURRENT_PID"
            kill -9 $CURRENT_PID sleep 10
          fi
          echo "> deploy new version."
          java -jar $JAR_NAME &
        '''
      }
    }
  }

  }
  environment {
    Name = 'Jindeok Jeong'
  }

  tools {
    maven 'M3'
  }
}