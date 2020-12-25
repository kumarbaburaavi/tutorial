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
* [NPM](https://docs.npmjs.com/about-npm) the package manager for the Node JavaScript platform
* [Angular JS](https://docs.angularjs.org) for building web application.
* [MockMVC](https://spring.io/guides/gs/testing-web/) for testing the Web Layer
* [Mockito](https://site.mockito.org/) for testing the Services Layer
* [Postgres](https://www.postgresql.org/) as database
* [Maven](https://maven.apache.org/) for managing the project's build
* [Docker](https://www.docker.com/) for building and managing the application distribution using containers

## Install
#### Download the repository
```
$ git clone https://github.com/kumarbaburaavi/tutorial.git
```
#### Run urlshortener ReST service with maven build tool
```
$ cd urlshortener
$ mvn clean test                         # Run the testcases and code coverage
$ mvn clean package -DskipTests=true    # Compile and Build the package
$ mvn spring-boot:run                    # Run the spring boot application.

Note: All the generated artifcats shall be found in target folder 
target/
|-- classes
|   |-- META-INF
|   |-- application.properties
|   `-- com
|-- generated-sources
|   `-- annotations
|-- generated-test-sources
|   `-- test-annotations
|-- jacoco.exec
|-- maven-status
|   `-- maven-compiler-plugin
|-- site
|   `-- jacoco
|-- surefire-reports
`-- test-classes
    `-- com
```
API Reference for Developer : http://localhost:8080/swagger-ui.html

#### Run Angular JS Web application (npm installed)
```
$ cd urlshortener/angular
$ npm install 
$ npm install -g @angular/cli@6.0.8 
$ ng serve --host
```
Web application: http://localhost:4200/

#### With docker and docker-compose installed
(**Limitation**: *ERROR: Service 'xxxxxx' failed to build : toomanyrequests: You have reached your pull rate limit. You may increase the limit by authenticating and upgrading: https://www.docker.com/increase-rate-limit*)

```
$ cd urlshortener 
$ docker-compose up --build
```
## Usage

Request Method | URI | Body (JSON) | Description |  
:---: | :--- | :---: | :--- |
GET | http://localhost:8080/shorten-url/api/v1/geturls | - | Get all urls | 
GET | http://localhost:8080/shorten-url/api/v1/{code} | - | Find long url and redirect | 
GET | http://localhost:8080/shorten-url/api/v1/{code}/url | - | Find and return long url | 
POST | http://localhost:8080/shorten-url/api/v1/addurl | { "url": "[http...]" } | create url and return its shorten url in response headers | 
PUT | http://localhost:8080/shorten-url/api/v1/{code} |  { "url": "[http...]" } | Update url | 
DELETE | http://localhost:8080/shorten-url/api/v1/{code} | - | Remove url | 
