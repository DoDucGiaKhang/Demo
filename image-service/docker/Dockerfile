FROM openjdk:15-slim

RUN mkdir /image-service
COPY ./target/*.jar /image-service/image-service.jar
WORKDIR /image-service
CMD "java" "-jar" "image-service.jar"
