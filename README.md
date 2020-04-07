# International Shop refactoring kata

International Shop is a trading platform that enables users to buy and sell items, relieving them from the hassle
of find the buyer for the article you want to sell, or find sellers if you are interested in buying.

## Exercise

The objective of this kata is to identify some design anti-patterns and perform some refactoring that will make the system more maintainable.

Some references that might help:
 - [Dependency Inversion principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle)


### Current functionalities

1. Administrators can create new `regions` representing a set of postal codes.
2. Customers can create `contracts` in a given `region`. A `contract` is like a wish list of desired products in a certain `region`
3. Users can try to sell `articles` in a given `region`. 
4. If there are `contracts` in that `region` with criteria matching a new `article` being published, a new `purchase` is generated
5. If there are NO `contracts` in that `region` with criteria matching a new `article` being published, the `article` is discarded
6. Customers can see a list of the `purchases` they've been assigned


### User stories in the backlog

1. As a User, when I'm selling an article, I want to specify my postal code instead of a region ID, so I don't have to 
know about the regions that exist in the system and it is easier for me to sell my articles.

2a. As a Customer, I want to be able to return an article if it was in bad conditions or if it was not really matching
the description of the article. 

2b. As an Administrator, I want to review and approve the requests for returning back the articles from customers, so 
I can avoid customers fraudulent behaviour of just using my platform to contact with users for free

3. As a User, when I try to sell an article but there were no buyers, I want to maintain the article in the shop until:
   a. a new buyer in the platform buys it
   b. I decide to cancel the publication

4. As a Customer who signs a new contract, I want to automatically purchase available unsold products if they match 
the criteria specified in my contract


## Pre-requisites

* Java 11
* Docker and Docker-compose
* Basic knowledge about, Spring Framework, JPA and REST APIs

## Lifecycle

For developers maintaining the application here are the instructions to be able to run the application and execute the tests.

#### Running the application locally

The application requires a database to run, which can be started via `docker-compose` with the following command: 
```bash
docker-compose -f scripts/docker-local.yml up -d
```

To start the application, it is possible to do it from: 
* The IDE, you need to ensure the spring profile `local` is used.
* From the command line: 
```bash
./gradlew bootRun
```

For convenience, here is a script that does both in one single command: 
```bash
scripts/run-local.sh
```
And type Ctrl-C when you want to terminate it

#### Running the tests

To run the tests, the integration tests require a database to be available, which can be started via `docker-compose` with the following command:
```bash
docker-compose -f scripts/docker-test.yml up -d
```

To execute the tests, it can be done from:
* The IDE, either with gradle or JUnit
* From the command line:
```bash
./gradlew test
```

For convenience, here is a script that spins up the database, runs the tests and shuts down the database
```bash
scripts/run-test.sh
```
