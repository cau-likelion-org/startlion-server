#!/bin/bash

kill_process_on_port() {
    local PORT=$1
    local PID=$(lsof -t -i :$PORT)

    if [ -n "$PID" ]; then
        echo "Killing process on port $PORT with PID $PID"
        kill -9 $PID
    else
        echo "No process is listening on port $PORT"
    fi
}

if [ $# -ne 2 ]; then
    echo "Usage: $0 <environment> <port>"
    exit 1
fi

ENV=$1
PORT=$2

./gradlew clean build -x test

if [ $? -ne 0 ]; then
    echo "Gradle build failed!"
    exit 2
fi

kill_process_on_port $PORT

JAR_NAME="startlionserver-0.0.1-SNAPSHOT.jar"

cd build/libs

nohup java -Dspring.profiles.active=${ENV} -jar $JAR_NAME &

echo "Server started with profile: ${ENV} on port ${PORT}"