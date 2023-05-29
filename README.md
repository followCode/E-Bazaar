# E-Bazaar
E-Bazaar is an e-commerce backend developed with a microservice architecture. It provides features for storing information about various products, their availability, and ordering.

Here are some of the benefits of using a microservice architecture for e-commerce backends:

 - Scalability: Microservices can be scaled independently, which makes it easy to add new features or handle increased traffic.
 - Flexibility: Microservices can be developed and deployed independently, which gives you more flexibility in how you build and manage your e-    commerce backend.
 - Resilience: Microservices are less likely to be affected by failures than monolithic applications. This is because each microservice is responsible for a small part of the overall application.

Microservices present in this project -
- Product Service - for storing product details.
- Inventory Service - for storing product availability.
- Order Service - for placing orders, it connects to inventory for checking availability and notification service for sending notifications.
- Notification Service - for sending notifications 


![Screenshot (60)](https://github.com/followCode/E-Bazaar/assets/47175098/a1a7ff34-5432-448c-8cb6-8d96cb935601)

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
- Select the file - 'spring-cloud-client.json' and check all
- Click on import

Kafka Setup
- 




