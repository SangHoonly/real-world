#!/bin/bash

REPOSITORY=/home/ec2-user/realWorld

echo "> build 파일 복사"
cp $REPOSITORY/build/libs/*.jar $REPOSITORY/

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl realWorld | grep jar | awk '{print $1}')

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

echo "> 새 애플리케이션 실행"
nohup java -jar $JAR_NAME > /home/ec2-user/realWorld/nohup.out 2>&1 &
