# Micro-Service Architecture Project
This project was created to introduce myself to micro-service architecture and its supporting tech stack. The focus of this project is on the architecture and technology, rather than the actual functionality of the services.

The project consists of three main services that communicate with each other through APIs and message queues.

## Services
The following are the three main services in this project:

### Customer Service
The customer service is responsible for customer registration. It uses an API to check whether a registered customer is fraudulent or not. If a customer is not found to be fraudulent, the service sends a notification through a message queue to the notification service.

### Fraud Service
The fraud service emulates a fraud database control. It is responsible for providing information about fraudulent customers to the customer service.

### Notification Service
The notification service emulates a notification service. It is responsible for sending notifications to customers when needed. It receives notification requests from the customer service through a message queue.

## Tech Stack
The following technologies are used in this project:

Java, 
Spring Framework, 
Kafka, 
Docker, 
Kubernetes, 
PostgreSQL.

Note: The focus of this project is on the architecture and technology, rather than the actual functionality of the services.
