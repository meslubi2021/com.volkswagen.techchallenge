# Volkswagen Digital Hub Technical Evaluation

## Author
All files contained in this repository have been created by me, Tomás García-Pozuelo, during September 2023

## Introduction
This is the technical evaluation project for Volkswagen job application.

## Project structure (Packages and chosen architecture)
The project has been done following Hexagonal Architecture and [CQS](https://martinfowler.com/bliki/CommandQuerySeparation.html).

The goal of hexagonal architecture is to separate **Domain** from **Use cases**, and also from all external dependencies that are needed to make the application work contained in the **Infrastructure** layer.

Hexagonal architecture pattern can be understood easily looking at the following diagram:
![hexagonalArchitecture.png](static%2FhexagonalArchitecture.png)

Also, as said, I've followed the **CommandQuerySeparation** pattern to make a migration to CQRS easier in the future.
The main idea of CQS is to separate **Commands** and **Queries**, being commands the use cases that write and queries the
use cases that read at the Domain.

For evolving to CQRS we would need to add an event bus and separate reads and writes from repositories (in this case, the DB)

For following the hexagonal architecture and CQS patterns there are three main layers that correspond to the following packages:

- [application](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fapplication)

  It contains the **Use cases**, in this concrete example the command and queries for doing different operations with the domain entities.

 
- [domain](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fdomain) 

  It contains the **Domain** entities and its business logic. There could be domain services too if needed. It also contains needed validations to have a valid entities. 


- [infrastructure](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Finfrastructure)
  
  It contains all **Infrastructure** components needed to make the application work (AKA "Adapters"). In this concrete example there is not much. A database and a REST controller. In the future, if we would need to add GraphQL, web clients, event consumers and producers (with Kafka or RabbitMQ) they would go into this package.

Some other packages:
- [config](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fconfig)

  A folder containing configurations. Depending on the profile we could load some packages or others. In this particular case no profiles used, so only one configuration that loads everything.


- [common](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Fcommon)

  This represents all stuff that should be common to several microservices following the same patterns. In a real world it would be a library and added to the project with a dependency.
  
  In this case it contains the interfaces to work with CQS in a tidy way.

  It also contains a simple metrics publisher that does nothing but logging. In a real world app it would publish metrics to Datadog or any other similar monitoring service.  

## Assumptions and some considerations
- Each call creates a new workspace from (0,0) to upper right corner in the input. In a real world application, the way of making robots moving would be totally different with a dedicated endpoint instead of sending a String containing workspace size, and all robot movements. 
- If a robot collides with another robot it will return exception, and it doesn't continue with its moving sequence. These assumption is ought to the fact that colliding between robots should be considered serious error (From my point of view!)
- For the same reason if a robot gets out of the workspace it's also considered an error, so it won't continue with its programmed sequence.
- Workspaces have a known unique identifier (UUID), this is for correct implementation of CQS.
- Robots have a known unique identifier (UUID), this is for correct implementation of CQS.
- I haven't added unit tests for now. There are enough integration tests and no complex methods at the moment that should need explicitly a unit test. In any case it wouldn't be complex at all adding them using Mockito.

## Tech stack
- JDK 17
- SpringBoot
- Cucumber for integration tests
- In memory database (H2)

## Integration tests
Cucumber and SpringBootTest have been used for integration testing.

Tests can be found in the *.features contained in the [features](src%2Ftest%2Fresources%2Ffeatures) folder.
- [Factory_RestAPI.feature](src%2Ftest%2Fresources%2Ffeatures%2FFactory_RestAPI.feature) All tests regarding the controller. It tests also the request validation
- [Robot_CQS.feature](src%2Ftest%2Fresources%2Ffeatures%2FRobot_CQS.feature) All tests regarding the Robot commands and queries. It tests also domain entity validation.
- [Workspace_CQS.feature](src%2Ftest%2Fresources%2Ffeatures%2FWorkspace_CQS.feature) Test regarding the workspace commands and queries. It tests also domain entity validation.

## How to build & run

First clone the repository, then go to the main folder and execute:
```
./gradlew :bootRun
```

Then, once the microservice is up in local, it's possible to call the controller with the following curl:
```
curl --request POST \
  --url http://localhost:8080/factory/cleaning-robots/process-input-sequence \
  --header 'Content-Type: text/plain' \
  --data  '5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM'
```

## Future work (ideas)
- #### Go for [CQRS](https://martinfowler.com/bliki/CQRS.html)
  Add an event bus (Kafka!?) and explore the possibility of using [Axon](https://www.axoniq.io/) or other similar framework for event driven microservices.
- #### Add some [DGS GraphQL](https://netflix.github.io/dgs/)
  Just for demonstrations purposes it would be cool to create a few GraphQL queries using [DGS](https://netflix.github.io/dgs/).
- #### Angular web showing the robots in real time
  It would be cool to create a GUI where the user can do inputs and see the result in a simple FE webpage.
- #### Change MetricPublisher by something more useful like Datadog or similar
  Now the metrics publisher component is just a logger. It would be cool to make a real metrics publisher using Datadog or similar.
