#!/usr/bin/env bash

#eureka-server
cd ../eureka-server
mvn clean package -DskipTests


#order-service
cd ../order-service
./mvnw clean package -DskipTests


#product-service
cd ../product-service
./mvnw clean package -DskipTests

#auth-server
cd ../auth-server
./mvnw clean package -DskipTests

#user-service
cd ../user-service
./mvnw clean package -DskipTests
