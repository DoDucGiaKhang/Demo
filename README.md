# Smartcommerce
The project creates a simple online shopping application to sell the products. The application is designed based on 
Model-View-Controller(MVC) architectural pattern. Clients access and manipulate products, orders information in data repositories which are distributed
on mulitple servers. At the front-end side, the client application is split into model and view components where model corresponds to the
bussiness logic: searching and ordering products and a web page representing the list of products and orders.
The back-end is decomposed into multiple micro-services. Each service has its own domain model and data repository. 
The services communicate with each other via Kafka message broker. 
The front-end and back-end communicate with each other via REST APIs.    

## Techonologies
Project is created with:
* Back-end: Java Spring Boot with JPA and Postgres database at persistence layer.  
* Front-end: Angular Version 11.2.5 and NodeJS version 14.16.0    
* Messaging: Kafka  

## The architecture back-end services 
![alt text](./resources/solution/iCommerce.png?raw=true)

## Application UI
![alt text](./resources/solution/UI.png?raw=true)

## Installation 
1. Back-end application: you can use your favorite IDE (IntelliJ/Eclipse) to run application IcommerceApplication.
Or, you can use Maven to build and run the application from project home directory.  
    `mvn install`  
    `mvn spring-boot:run`
      
2. Front-end application: First, you need to install the latest version of npm, Angular and Node.js. Then, go to front-end 
home directory and run application with following commands:   
    `cd front-end`  
    `npm update`  
    `npm start`   
    Then you can access the web page at http://localhost:4200/
   
## Deployment
You can simply deploy the whole application using docker-compose.  
    `cd docker`  
    `docker-compose up -d`

## API Verification
Please execute the following CURL commands to verify APIs
* Products  
`curl -v http://localhost:8000/api/products`  
* Orders  
`curl -v http://localhost:9000/api/orders`
