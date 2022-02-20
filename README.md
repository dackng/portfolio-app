# Technical test

There are two projects:

- portfolio-service : API with two endpoints, GET is for getting the porftolio by user and PUT is for updating all the porftolio.
- portfolio-webapp : java web app like the front part with two API as consummers that are `portfolio API` and `Twitter API`

## portfolio-service: Software prerequisites
- Gradle
- Java 11

## portfolio-service: Technologies used
- Unit tests
- Validations
- Swagger
- SpringBoot

## portfolio-service: Steps to build the app
Run the following commands:

`./gradlew build` 
`./gradlew bootRun` 

This is the Swagger URL:

`http://localhost:8011/swagger-ui.html` 

And these are the endpoints:

- GET: Getting portfolio by user id

`http://localhost:8011/api/v1/portfolio/{userId}` 

- PUT: Updating all fields from portfolio

`http://localhost:8011/api/v1/portfolio/{userId}`

Also there is a JSON file `porftolio-service.postman_collection.json` to import in Postman

## portfolio-webapp: Software prerequisites
- Maven
- Java 8

## portfolio-webapp: Technologies used
- Twitter API
- JSF
- Primefaces
- SpringBoot

## portfolio-service: Steps to build the app
Run the following commands:

`mvn clean` 
`mvn install` 
`mvn spring-boot:run` 

This is the first page

`http://localhost:8080/index.xhtml` 


