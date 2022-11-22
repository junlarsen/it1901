# FX

This is where the JavaFX app of CardSnap lives.

## Project Status

Under development 🚧

## Development

### Built with

- [Java](https://www.java.com/en/)
    - Modern Java, using Java 19
- [JavaFX](https://openjfx.io/)
    - A client application library for Java

#### Dependencies

- [JavaFX](https://openjfx.io/)
- [Gson](https://github.com/google/gson)

### Installation

(from cardsnap directory (..))

```bash
mvn clean install
```

### Running locally

```bash
mvn -f fx/pom.xml javafx:run
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

### Build JLink and JPackage archive

```
mvn javafx:jlink jpackage:jpackage
```