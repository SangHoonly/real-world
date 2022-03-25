#!/bin/bash
source /home/ec2-user/.bash_profile

REPOSITORY=/home/ec2-user/realWorld

echo "> build 파일 복사"
cp $REPOSITORY/build/libs/*SNAPSHOT.jar $REPOSITORY/

CURRENT_PID=$(pgrep -fl realWorld)
echo "> 현재 실행중인 애플리케이션 pid 확인: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없습니다!"
else
  echo "> 현재 애플리케이션이 실행 중인 애플리케이션을 먼저 종료합니다."
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

echo "> 새 애플리케이션을 실행합니다."
nohup java -jar $JAR_NAME > /home/ec2-user/realWorld/nohup.out 2>&1 &
