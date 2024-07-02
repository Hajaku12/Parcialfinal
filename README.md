This project outlines a RESTful server, concurrent test client, and web client architecture. Here’s a breakdown of its features and deployment instructions:

- **Repository** - [Main Repo](https://github.com/Hajaku12/Parcialfinal)

## Architecture

The architecture involves the following components:

1. **User Browser**: Connects via HTTP on port 3000 to the React-based client, which is hosted on an Amazon EC2 instance.
2. **Spring Server**: Another EC2 instance running on port 8080 (Tomcat) manages synchronization for all client dots, delivering JSON arrays to be processed and interpreted by clients.
3. **Client-Side Processing**: Clients draw or erase dots based on user input, with certain commands reserved for debugging purposes.

## Getting Started

### Prerequisites

- **Java**: Ensure you have the appropriate Java version.
  ```
  java -version
  ```

- **Maven**: Verify the Maven version.
  ```
  mvn -version
  ```

### Installation

1. Clone the project repository.
2. Build the project using Maven.
   ```
   mvn clean install
   ```

## Generating Javadoc

Generate the project documentation using the following Maven commands:
```
mvn javadoc:javadoc
mvn javadoc:jar
mvn javadoc:aggregate
mvn javadoc:aggregate-jar
mvn javadoc:test-javadoc 
```

## Deployment

### Server Deployment

1. Ensure the program is executed in the desired directory.
2. Start the server with Maven.
   ```
   mvn spring-boot:run
   ```

3. Verify server functionality:
    - Check server status: [http://localhost:8080/status](http://localhost:8080/status)
    - Access the JavaScript client: [http://localhost:8080](http://localhost:8080)
    - Query endpoint example: [http://localhost:8080/rest?quer=MSFT&type=TIME_SERIES_DAILY_ADJUSTED](http://localhost:8080/rest?quer=MSFT&type=TIME_SERIES_DAILY_ADJUSTED)

### Concurrent Test Client Deployment

Run the concurrent test client with Maven.
```
mvn -e exec:java -Dexec.mainClass=parcial.arsw.parcial1.model.Client
```

## Built With

### Server Side

- [Maven](https://maven.apache.org/)
- [Java](https://www.oracle.com/java/technologies/)
- [Spring](https://spring.io/)

### Client Side

- [HTML 5](https://html.spec.whatwg.org/multipage/)
- [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript)

## Versioning

Uses [SemVer](http://semver.org/) for versioning.

## Authors

- **Hann Jang** - *Initial work* - [Hajaku12](https://github.com/Hajaku12)

This guide should help you set up and deploy the project locally or on a live system. If you have any questions or need further assistance, feel free to ask!