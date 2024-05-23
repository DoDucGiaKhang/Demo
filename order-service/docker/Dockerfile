FROM openjdk:15-slim

RUN mkdir /order-service
COPY ./target/*.jar /order-service/order-service.jar
WORKDIR /order-service
CMD "java" "-jar" "order-service.jar"