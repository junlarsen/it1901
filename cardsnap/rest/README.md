# CardSnap REST

This is where the backend, and core logic of CardSnap's backend service lives.

## Project Status

MVP complete

## Development

### Built with

- [Java](https://www.java.com/en/)
  - Modern Java, using Java 19
- [Spring Boot](https://spring.io/projects/spring-boot)
  - An extensive and heavily tested server application framework for Java

#### Dependencies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [SpringDoc](https://springdoc.org/)
- [Swagger Annotations](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations)
- [Gson](https://github.com/google/gson)

### Installation

(from cardsnap directory (..))

```bash
mvn clean install
```

### Running locally

```bash
mvn -f rest/pom.xml spring-boot:run
```

### Running unit and integration tests

```bash
mvn test
```

### Generate coverage report (in respective /target/site) folders

```bash
mvn site
```

### Run checkstyle or spotbugs or all verify phases

```
mvn spotbugs:check   # spotbugs only
mvn checkstyle:check # checkstyle only
mvn verify           # spotbugs, checkstyle, and all tests
```
