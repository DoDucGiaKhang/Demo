FROM openjdk:15-slim

RUN mkdir /eureka-server
COPY ./target/*.jar /eureka-server/eureka-server.jar
WORKDIR /eureka-server
CMD "java" "-jar" "eureka-server.jar"