Springboot application that returns draft ticket prices for a bus trip

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
curl -X POST -H "Content-Type:application/json" -H "Accept:application/json" -d "[{\"bags\":[\"bag1\",\"bag2\"],\"category\":\"Adult\"}]" http://localhost:7070/tickets/Lithuania

```
