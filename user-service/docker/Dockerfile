FROM openjdk:15-slim

RUN mkdir /user-service
COPY ./target/*.jar /user-service/user-service.jar
WORKDIR /user-service
CMD "java" "-jar" "user-service.jar"
