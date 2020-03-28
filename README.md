# Similarity Between Products [![Build Status](https://travis-ci.org/felipe-basina/similarity-btwn-products.svg?branch=master)](https://travis-ci.org/felipe-basina/similarity-btwn-products)
The aim of this application is to provide a minimum solution for Amaro’s back-end challenge which consists in providing two endpoints for operations related to products

#### Operations
The application exposes two endpoints for specific product operations to be processed:
  1. Tags Vector - `POST /tagsVector`
**Creates characteristics tags for each one of the available products in the input payload**
  2. Similar Product Finder - `POST /similarProductFinder/{productId}`
**Returns a list with the three most similar products according to the product specified by its id as parameter**

There is also an **API Swagger Documentation** in the following path `GET /swagger-ui.html` and from them it is also possible to test the exposed endpoints!

#### Assumptions
These following requirements are considered on this solution:
* For the **Similar Product Finder** operation the **similarity** and **productId** model attributes are both considered when sorting to find the most similar products
* Running on an **Unix** environments it would be a nice play! But with all the stack dependencies resolved **(Java, Maven, Docker...)** it would not be a problem using another one
* At least Java version `openjdk version "1.8.0_242"
OpenJDK Runtime Environment (build 1.8.0_242-8u242-b08-0ubuntu3~18.04-b08)
OpenJDK 64-Bit Server VM (build 25.242-b08, mixed mode)`
* At least Apache Maven version `apache-maven-3.5.4`

**-- These settings are the ones used to provide the solution, other setup may not respond as expected --**

### Challenge Document Description
The source documentation about this challenge can be found at:
- `https://github.com/felipe-basina/similarity-btwn-products/tree/master/challenge-doc/JAVA Back-end Challenge.pdf`

### Sample JSON data
A sample of JSON for request purposes can be get from the following project path

**Products for getting tags vector**
- `https://github.com/felipe-basina/similarity-btwn-products/blob/master/src/test/resources/products.json`

**Products with tags vector for getting similar products**
- `https://github.com/felipe-basina/similarity-btwn-products/blob/master/src/test/resources/products-with-tags-vector.json`


#### Sample of execution using IDE
To run the application follow steps below:
```
1. Clone this repository
2. Import project cloned to an IDE of choice
3. Run application through main class 
```

### Running on Docker
It is possible to run a Docker image of the application. To do so type following commands at the root folder of the project to build and run application inside a Docker container:
```
1. To start running application
$ sh run.sh
 - With this command you will do:
 1.1 Compile and package base code
 1.2 Download and build an image
 1.3 Run docker image
 - So now the application has been started on PORT <9091> and ready for receiving requests
--------------------------------------------------------------------------------------------
2. To stop running application
$ sh stop.sh (should be executed from another console tab/window)
 - With this command the container will be stopped
```

### Testing
The project contains a set of unit/[narrow integration tests](https://martinfowler.com/bliki/IntegrationTest.html) and to run all of them just type when still at the root folder of the project:
```
$ mvn clean package
```
Needless to say that it is possible to run all of the tests from your favorite IDE! `:)`

### CI/CD
The code repository is linked to the [Travis CI](https://travis-ci.org/) to provide feedback when pushing new versions into master branch, moreover a new version of the application is deployed into the `Heroku` cloud environment

### Heroku
The current master branch version is deployed into [Heroku](https://dashboard.heroku.com/) cloud environment so it is possible to test execution from the following exposed endpoints:
 * **API Documentation :** [/swagger-ui.html](https://amaro-challenge.herokuapp.com/swagger-ui.html)
 * **Tags Vector :**  [/tagsVector](https://amaro-challenge.herokuapp.com/tagsVector)
 * **Similar Product Finder :** [/similarProductFinder/{productId}](https://amaro-challenge.herokuapp.com/similarProductFinder/)