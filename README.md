# E-Bazaar
E-Bazaar is an e-commerce backend developed with a microservice architecture. It provides features for storing information about various products, their availability, and placing order and sending notification for the same.

Here are some of the benefits of using a microservice architecture for e-commerce backends:

 - Scalability: Microservices can be scaled independently, which makes it easy to add new features or handle increased traffic.
 - Flexibility: Microservices can be developed and deployed independently, which gives you more flexibility in how you build and manage your e-    commerce backend.
 - Resilience: Microservices are less likely to be affected by failures than monolithic applications. This is because each microservice is responsible for a small part of the overall application.

Microservices present in this project -
- Product Service - for storing product details.
- Inventory Service - for storing product availability.
- Order Service - for placing orders, it connects to inventory for checking availability and notification service for sending notifications.
- Notification Service - for sending notifications 

![Screenshot (60)](https://github.com/followCode/E-Bazaar/assets/47175098/7683e553-8807-4d40-ad21-87e29dc9707c)


This project makes use of the following patterns -
- Service discorvery (Netflix Eureka)
- Circuit Breaker (Resilience4J)
- Distributed tracing (Zipkin, Sleuth)
- API Gateway (Spring cloud gateway)
- Database per Service (MongoDB, MySQL)
- Event-Driven Architecture (Apache Kafka)

##Setup
Database Setup:
- Install MySQL and setup the port, user and password.
- Inside application.properties in product-service, change the database credentials

- Install MongoDB
- Inside application.properties in order-service and inventory-service, change the database credentials

KeyClock Setup:
- Go to terminal and run this command:
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.1 start-dev

- Go to url - http://localhost:8080 and go to realm settings
- Click on action -> Partial import
- Select the file - realm/realm-export and check all
- Click on import

Kafka Setup
- Go to the terminal --> move into the project 
- Execute the command : docker compose up -d

Services
-Run the services like usual spring boot project




