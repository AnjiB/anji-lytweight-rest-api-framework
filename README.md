# Anji's - API Testing Framework Synopsis


Thanks you providing me an opportunity to work on this assignment. I built a RestApi Framework to test these apis.

* [Set Up Application Under Test](#setup_AUT)
* [Tech Stack Used in Framework](#tech-stack-used-in-framework)
* [Overview On Framework](#Overview-On-Framework)
* [How to run the tests](#how-to-run-the-tests)
* [Continuous Integration](#Continuous-Integration)
* [Reporting](#reporting)
* [Next Stpes](#next_steps)

## Setup AUT
Refer this [ReadME](https://github.com/AnjiB/anji-kot-lin/blob/master/README.md) to setup the application to test the APIs.

## Tech Stack Used in Framework

Framework is built on Java and below are the requirements to run the tests

* Java 1.8
* Maven 3.5.0 or more
* RestAssured 4.4.0
* TestNG 6.14.3
* AssertJ 3.11.1
* Java Faker 1.0.2
* Lombok 1.18.6
* Jackson 2.9.8
* Snakeyaml 1.26

## Overview On Framework

* Api Testing Framework is built on RestAssured
* Frameworks provides flexibility to invoke any kind of API(POST, PUT, PATCH, GET, DELETE etc) inside test using RestBuilder.
* Framework creates the log file puts in folder /logs under the root of the project which can be used for debugging.
* Framework supports parallel test execution as we use TestNG as testing framework
* Supports for Data Driven Testing (TestNG Supports Data Driven Testing Using dataProviders)
* Uses Snakeyaml to load the configuration
* Uses AssertJ as assertion library which provides powerful features and allows us to construct fluent assertions
* In future, if underlying rest framework will be changed, it will have no impact on tests


## How to run the tests
* Go to root folder of the project
* From the command line execute command `mvn test -Pe2e -DautEnvironment=DEV -DenableLogs=true` [enableLogs flag is to enable rest assured logs for API request and response]
* If you want to run multiple tests in parallel, please change thread count in [testng.xml](https://github.com/AnjiB/anji-lytweight-rest-api-framework/blob/master/pom.xml) file.

## Continuous integration
* [Jenkins](https://github.com/AnjiB/anji-lytweight-rest-api-framework/blob/master/Jekinsfile)
* GitLab (TODO)

## Reporting
* Once the you run the tests, under root folder, you will see a folder with name `target/surefire-reports` will be generated.
In the folder `surefire-reports` you will see emailable-report.html and index.html which tells you about the overall test run status.

## Next Steps
* Github workflow
