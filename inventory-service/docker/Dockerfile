FROM openjdk:15-slim

RUN mkdir /inventory-service
COPY ./target/*.jar /inventory-service/inventory-service.jar
WORKDIR /inventory-service
CMD "java" "-jar" "inventory-service.jar"
