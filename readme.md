## 1. 참조정보
### 관계 엔티티(BOOK_AUTHOR)에 Insert
> [POST] http://localhost:8080/api/bookAuthors
```json
{
	"book": "http://localhost:8080/api/books/1",
	"author": "http://localhost:8080/api/authors/5",
	"seq":"1"
}
```
### 조인테이블 컬럼값으로 filter
> [GET] http://localhost:8080/api/books/search/findByAuthorName?authorName=정진덕

## 2. Jenkins CICdePloy 등록 정보
### profile:postgres
* VM Options : -Dspring.profiles.active=postgres
### Build 매개변수
 ```
매개변수명 : appName
Default Value : spring-jpa-example
``` 
### Pipeline
```groovy
pipeline {
     agent any
 
     tools {
         // Install the Maven version configured as "M3" and add it to the path.
         maven "M3"
     }
 
     stages {
         stage('Build') {
             steps {
                 // Get some code from a GitHub repository
                 git 'git@github.com:kalphageek/spring-jpa-example.git'
 
                 // Run Maven on a Unix agent.
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
 
                 // To run Maven on a Windows agent, use
                 // bat "mvn -Dmaven.test.failure.ignore=true clean package"
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
         stage('Delivery') {
             steps {
                 sh '''
                     cd target
                     JAR_NAME=$(ls *.jar | tail -n 1)
                     echo ".jar name : $JAR_NAME"
                     ssh jjd@api1.deogi mkdir -p /home/jjd/workspace/${appName}/target
                     scp ../service-start.sh jjd@api1.deogi:/home/jjd/workspace/${appName}/
                     ssh jjd@api1.deogi chmod 755 /home/jjd/workspace/${appName}/service-start.sh
                     scp $JAR_NAME jjd@api1.deogi:/home/jjd/workspace/${appName}/target/
                 '''
             }
         }
         stage('Start Service') {
             steps {
                 sh 'ssh jjd@api1.deogi /home/jjd/workspace/${appName}/service-start.sh ${appName} &'
             }
         }
     }
}
```