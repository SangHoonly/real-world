#!/bin/bash

BUILD_JAR=$(ls /home/ec2-user/realWorld/build/libs/*.jar)
REPOSITORY=/home/ec2-user/realWorld/
JAR_NAME=$(basename $BUILD_JAR)

echo "> build 파일 복사"
cp $BUILD_JAR $REPOSITORY

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f "$JAR_NAME")

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME

chmod +x $DEPLOY_JAR

echo "> 새 애플리케이션 실행"
nohup java -jar $DEPLOY_JAR > /home/ec2-user/realWorld/nohup.out 2>&1 &
