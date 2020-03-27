#!/bin/bash
echo 'Generating application package'
mvn clean package -DskipTests

APP_NAME=amaro-backend-challenge

sudo docker build -f Dockerfile -t $APP_NAME .
sudo docker run -p 9091:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m" $APP_NAME