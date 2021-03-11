#!/usr/bin/env bash

PORT=9000

PID=$(lsof -ti tcp:${PORT})

if [ -z ${PID} ]; then
  echo "서버가 실행되고 있지 않습니다"
else
  echo "> kill -9 $PID"
  kill -9 ${PID}
  echo "기존에 실행중인 서버를 종료합니다"
  sleep 3
fi

cd /home/ubuntu/homecafe-backend

./gradlew clean build

nohup java -jar ./homecafe-api/build/libs/homecafe-api.jar &