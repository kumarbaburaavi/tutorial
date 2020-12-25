# URL Shortener Service


## About
#### Req: Develop a URL Shortener Service
The URL Shortener Service is an API for creation of tiny URL, each shortened url should be unique and specific to a single original url.  

The main requirements that guided the design and implementation of urlshortener:
*	Support of scalability/availability
*   Design and implement an REST API for short URL creation
*	Implement forwarding of short URLs to the original ones
*	There should be some form of persistent storage
*	It should be readable, maintainable, and extensible where appropriate
*	The implementation should preferably be in Java
*   Create a web page for easy to use to the users while creation short url using Angular JS.

## Usecase
-  When users enter a long URL, “urlshortener” service should generate a unique short link with characters length of 7 (assumption the length of the short URL should be configurable)
-  Entering the short link via ReST API, it should redirect the user to the original long URL.

## Architecture overview

#### Project structure
Created maven project followed by Spring MVC guidelines. 

```
|-- src
|   |-- main
|   |   |-- java
|   |   `-- resources
|   `-- test
|       `-- java
|-- angular
|   |-- package.json
|   |-- protractor.conf.js
|   |-- src
|   `-- .....
|-- Dockerfile.angular
|-- Dockerfile.spring
|-- docker-compose.yml
|-- README.md
|-- pom.xml
```
#### Technology stack
* [Spring Boot](http://spring.io/projects/spring-boot) for creating the RESTful Web Services
* [Angular JS](https://docs.angularjs.org) for building web application.
* [MockMVC](https://spring.io/guides/gs/testing-web/) for testing the Web Layer
* [Mockito](https://site.mockito.org/) for testing the Services Layer
* [Postgres](https://www.postgresql.org/) as database
* [Maven](https://maven.apache.org/) for managing the project's build
* [Docker](https://www.docker.com/) for building and managing the application distribution using containers

