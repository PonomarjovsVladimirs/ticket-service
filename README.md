Springboot application that returns draft ticket prices for a bus trip.
This app is not a part of a real system. Underlying services are mocked.

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
3- Run a request to confirm application is working
```
curl -X POST -H "Content-Type:application/json" -H "Accept:application/json" -d "{\"currency\":\"EUR\",\"destination\":\"Lithuania\",\"passengers\":[{\"bags\":[\"bag1\",\"bag2\"],\"category\":\"Adult\"}]}" http://localhost:7070/tickets

```
