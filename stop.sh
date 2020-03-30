#!/bin/bash
echo 'Stopping container...'

APP_NAME=amaro-backend-challenge

sudo docker stop $(sudo docker ps -q --filter ancestor=$APP_NAME)

echo 'Container down!'