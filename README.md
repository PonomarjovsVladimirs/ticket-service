Springboot application that returns ticket prices for a bus trip

0- Prerequisites
```
1- To run the application locally you need to have Java 15
2- Maven wrapper included in the project
```
1- Clone repository

2- Compile and start app
```
mvnw clean spring-boot:run
```
You should see something like:
```
Started TicketApp in 1.764 seconds (JVM running for 2.051)
```
4- Confirm that the application is running
```
curl http://localhost:7070/tickets/Lithuania
```
