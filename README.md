# backend-d0020e

This is the backend for the project in the course D0020e. 


### Prerequisites


* [Spring](https://spring.io/projects/spring-boot) - Spring framework

* [Java JDK](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html) - Java jdk 13 (tested, and works.)

* [Download Guide](https://www.theserverside.com/video/5-steps-for-an-easy-JDK-13-install-on-Ubuntu) - Download and setup for Ubuntu.  


### Installing

A step by step series of examples that tell you how to get a development env running

Clone the repository and enter the folder

```
git clone https://github.com/altzin/backend-d0020e
cd backend-d0020e
```

## Getting Started

To run the webserver and start using the backend run the command below.
```
./mvnw spring-boot:run
```


## Testing and using the API

Sends a request to get the latest generated simulation from the server.
```
 curl http://localhost:8081/mostRecent
```

Send a post request to generate simulation files
```
curl -X POST localhost:8081/process -H 'Content-type:application/json' -d '{
      "nodes": "5",
      "runtime": "10",
      "seed":"1234",
      "arrivalSpeed":"2"
    }'     

```
