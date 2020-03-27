FROM openjdk:8
LABEL version="1.0"
LABEL description="Amaro back-end challenge"
COPY target/amaro-backend-challenge-1.0.0.jar /var/www/app.jar
WORKDIR /var/www
ENTRYPOINT java -jar app.jar