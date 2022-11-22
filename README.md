[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2217/gr2217)

# IT1901 Fall 2022 GR2217 -- CardSnap

This project contains the IT1901 project for group 17. It is a Java server
application with a TypeScript + React frontend application.

Our application is called CardSnap, and it is an application intended to
accelerate learning by providing an easy and intuitive way to create and
practice using flash cards. Details about the application can be found in the
[cardsnap documentation](cardsnap/README.md).

The main application source is found inside the [cardsnap](cardsnap) directory/

## Documentation

[Release 1](docs/release1/README.md)
[Release 2](docs/release2/README.md)
[Release 3](docs/release3/README.md)

[About](cardsnap/README.md)
[JavaFX](cardsnap/fx/README.md)
[Frontend](cardsnap/ui/README.md)
[Backend](cardsnap/rest/README.md)

### Building the project

The project uses [Apache Maven](https://maven.apache.org/) to build and manage
the code. The project uses Java 19. Additionally we require any recent version
of Node.js and the Yarn package manager to build and run the client-side
application.

To build the application, follow these steps:

```shell
# Clone the application code
git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2217/gr2217
cd gr2217/cardsnap

# Build the backend application
mvn clean install

# Install dependencies for the frontend application
cd ui && yarn install

# If you plan on running e2e tests, install playwright browsers
# without this, the e2e test cannot run
npx playwright install

# Build a distributable with JPackage
mvn -f fx/pom.xml javafx:jlink jpackage:jpackage
```

### Running the server and client applications

Because the application consists of a client and a server, there are two
processes required to run the entire system, one for the frontend, and one for
the backend. This can easily be done by having two terminal windows open at 
once.

```
# Run the backend application
#
# Pro tip: after running backend app, visit
# http://localhost:8080/swagger-ui/index.html#/ for api docs!
mvn -f rest/pom.xml spring-boot:run

# Run the frontend application
cd ui && yarn start

# Run the JavaFX app
mvn -f fx/pom.xml javafx:run
```

The backend is now available on port `8080`, and the frontend application on
port `3000`.

### Running CardSnap test suite

The CardSnap test suite consists of three different test types, split across
three different test runners.

- Backend unit tests (with JUnit)
- Backend integration tests (with JUnit)
- Frontend unit tests (with Vitest)
- Frontend end-to-end tests (with Playwright)
- JavaFX end-to-end tests (with JUnit & TestFX)

Coverage of tests (apart from end-to-end tests) is collected with the following
tools:

- Jacoco: Java code coverage reported that runs when running test suite
- C8: Node.js code coverage runner, integrated into Vitest

Additionally, we use a set of linting and code style tools to ensure that the
code style is consistent across the entire project.

- Prettier: formatting of frontend app
- ESLint: linting and bug-spotter for frontend app
- CheckStyle: style checker for backend app
- SpotBugs: linting and bug-spotter for backend app

Instructions on how to run tests:

```shell
# Run unit and integration tests on backend, ensure checkstyle passes, ensure
# spotbugs finds no problems, and generate Jacoco coverage report
mvn verify site

# Run unit and end-to-end tests on frontend, ensure eslint and prettier pass
#
# Note: e2e tests require both frontend and backend apps to be running on the
# :3000 and :8080 ports respectively. Run e2e tests with zero decks in app
# to ensure everything is inside viewport when expected.
cd ui && \
  yarn lint && \
  yarn test && \
  yarn e2e

# If you want to run the code coverage for the frontend, simply replace the
# `yarn test` with `yarn coverage`.
```

## Authors

-   Tale Eikenes
-   Magnus Ouren
-   Isak Solheim
-   Mats Larsen
