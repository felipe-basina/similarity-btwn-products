#!/bin/bash
echo 'Stopping container...'

APP_NAME=amaro-backend-challenge

sudo docker stop $(sudo docker ps -q --filter ancestor=amaro-backend-challenge)

echo 'Container down!'