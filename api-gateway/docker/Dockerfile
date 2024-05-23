FROM openjdk:15-slim

RUN mkdir /api-gateway
COPY ./target/*.jar /api-gateway/api-gateway.jar
WORKDIR /api-gateway
CMD "java" "-jar" "api-gateway.jar"