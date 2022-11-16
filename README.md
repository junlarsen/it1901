[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2217/gr2217)

# IT1901 Fall 2022 GR2217 -- CardSnap

This project contains the IT1901 project for group 17. It is a Java server
application with a TypeScript + React frontend application.

Our application is called CardSnap, and it is an application intended to
accelerate learning by providing an easy and intuitive way to create and
practice using flash cards. Details about the application can be found in the
[cardsnap documentation](cardsnap/README.md).

The main application source is found inside the [cardsnap](cardsnap) directory/

### Building the project

The project uses [Apache Maven](https://maven.apache.org/) to build and manage
the code. The project uses Java 19. Additionally we require any recent version
of Node.js and the Yarn package manager to build and run the client-side
application.

To build the application, follow these steps:

#### Backend

```shell
# Clone the application code
git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2217/gr2217
cd gr2217/cardsnap

# Build the backend application
mvn clean install

# Run tests and generate Jacoco, Spotbugs, and Checkstyle reports for the backend
mvn verify site

# Run REST API
mvn -f rest/pom.xml spring-boot:run
```

#### Frontend

```shell
# Navigate to frontend directory
cd cardsnap/ui

# Install dependencies for the frontend application
yarn install

# Run frontend applications
yarn start # Front-end application runs at port 3000

# Running unit tests
yarn test

# Running unit tests with coverage
yarn coverage

# Running snapshot tests
yarn snapshot

# Linting the application
yarn lint
```

## Documentation

[Release 1](docs/release1/README.md)
[Release 2](docs/release2/README.md)
[Release 3](docs/release3/README.md)

[About](cardsnap/README.md)
[Frontend](cardsnap/ui/README.md)

## Authors

-   Tale Eikenes
-   Magnus Ouren
-   Isak Solheim
-   Mats Larsen
