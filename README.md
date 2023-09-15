# Volkswagen Digital Hub Technical Evaluation

## Author
All files contained in this repository have been created by me, Tomás García-Pozuelo, for Volkswagen technical evaluation during September 2023

## Introduction
For further reference, please consider the following sections

## Requirements
For further reference, please consider the following sections

## Project structure (Packages)
The project has been done following Hexagonal Architecture and [CQS](https://martinfowler.com/bliki/CommandQuerySeparation.html).

The main packages are:
- [application](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fapplication)
  <br /> Bla bla bla
- [domain](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fdomain)
  <br /> Bla bla bla
- [infrastructure](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Finfrastructure)
  <br /> Bla bla bla
- [config](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Ftechchallenge%2Fconfig)
  <br /> Bla bla bla
- [common](src%2Fmain%2Fkotlin%2Fcom%2Fvolkswagen%2Fcommon)
  <br />This represents all stuff that should be common to several microservices following the same patterns.
  <br />In this case it contains the interfaces to work with CQS in a tidy way.

## Assumptions and some considerations
- If a robot collides with another robot it will return exception, and it doesn't continue with its moving sequence. These assumption is ought to the fact that colliding between robots should be considered serious error (From my point of view!)
- For the same reason if a robot gets out of the workspace it's also considered an error, so it won't continue with its programmed sequence.
- Workspaces have a known unique identifier (UUID), this is for correct implementation of CQS.
- Robots have a known unique identifier (UUID), this is for correct implementation of CQS.
- I haven't added unit tests for now. There are enough integration tests and no complex operations at the time that should need explicitly a unit test.

## Integration tests
Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla

## How to build & run
Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla

````
curl --request POST \
  --url http://localhost:8080/factory/cleaning-robots/process-input-sequence \
  --header 'Content-Type: text/plain' \
  --data  '5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM'
````

## Future work (ideas)
- #### Go for [CQRS](https://martinfowler.com/bliki/CQRS.html)
  add an event bus (Kafka!?) and explore the possibility of using Axon or other similar framework for event architecture.
- #### Add some [DGS GraphQL](https://netflix.github.io/dgs/)
  Just for demonstrations purposes it would be cool to create a few GraphQL queries using [DGS](https://netflix.github.io/dgs/)
- #### Angular web showing the robots in real time
  It would be cool to create a GUI where the user can do inputs and see the result in a simple FE webpage.
- #### Change MetricPublisher by something more useful like Datadog or similar
  Bla bla bla bla bla bla bla bla bla bla bla bla bla bla