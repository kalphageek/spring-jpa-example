#!/bin/bash

APP_NAME="sping-jpa-example"
TODAY=$(date +%Y%m%d%H%M)

cd ~/workspace
echo "pwd"
pwd
JAR_NAME=$(ls *.jar | grep tail -n 1)
echo "jar name : $JAR_NAME"
echo "today : $TODAY"
echo "> Checking pid on the running process."
CURRENT_PID=$(ps -ef | grep java | grep $APP_NAME | awk '{print $2}')
echo "pid : $CURRENT_PID"
if [ -z $CURRENT_PID ];
  then echo "> There is no running process."
else echo "> kill -9 $CURRENT_PID"
  kill -9 $CURRENT_PID sleep 10
fi

echo "> Deploy new version."
nohup java -jar $JAR_NAME & > logs/$APP_NAME.logs