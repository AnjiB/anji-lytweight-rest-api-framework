# Anji's - API Testing Framework Synopsis


Thanks you providing me an opportunity to work on this assignment. I built a RestApi Framework to test these apis.

* [Set Up Application Under Test](#setup_aut)
* [Tech Stack Used in Framework](#tech-stack-used-in-framework)
* [Overview On Framework](#Overview-On-Framework)
* [How to run the tests](#how-to-run-the-tests)
* [Reporting](#reporting)

## Setup Aut
Refer this [ReadME](https://github.com/AnjiB/anji-kot-lin/blob/master/README.md) to setup the application to test the APIs.

## Tech Stack Used in Framework

Framework is built on Java and below are the requirements to run the tests

* Java 1.8
* Maven 3.5.0 or more
* Apache HttpClient 4.4.1
* TestNG 6.14.3
* AssertJ 3.11.1

## Overview On Framework

* Api Testing Framework is built on Apache HttpClient4
* Frameworks provides flexibility to invoke any kind of API(POST, PUT, PATCH, GET, DELETE etc) inside test using RestBuilder.
* Framework creates the log file puts in folder /logs under the root of the project which can be used for debugging.
* Framework supports parallel test execution as we use TestNG as testing framework
* Supports for Data Driven Testing (TestNG Supports Data Driven Testing Using dataProviders)


## How to run the tests
* Go to root folder of the project
* From the command line execute command `mvn test -Pe2e -DautEnvironment=DEV`
* If you want to run multiple tests in parallel, please change thread count in [testng.xml](https://github.com/AnjiB/anji-rest-api-framework/blob/main/anji-rest-api-framework/testng.xml) file.

## Reporting
* Once the you run the tests, under root folder, you will see a folder with name `target/surefire-reports` will be generated.
In the folder `surefire-reports` you will see emailable-report.html and index.html which tells you about the overall test run status.
