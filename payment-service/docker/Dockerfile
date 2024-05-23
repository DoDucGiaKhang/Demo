FROM openjdk:15-slim

RUN mkdir /payment-service
COPY ./target/*.jar /payment-service/payment-service.jar
WORKDIR /payment-service
CMD "java" "-jar" "payment-service.jar"