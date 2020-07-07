# sample-login-geb-spock

## Overview
This is an example 
[Geb](http://www.gebish.org)-[Spock](http://spockframework.org)-[Groovy](http://groovy-lang.org)
implementation of Acceptance Test Driven Development (ATDD).
**However, it also provides an extensible framework that can be reused
by replacing the existing tests.**
  
These tests show how to use Geb-Spock to verify...
* that critical elements are on a page
* the ability to login as a user
  
It also demonstrates the basic features
of the Geb-Spock framework and how they can be extended.

### Run Locally or in Containers
This project can be run...
* Fully locally running the tests against a local browser
* Locally running the tests against a containerized browser
* Fully in 2 separate Docker containers, one containing the
tests the other the browser

### Contents of this Framework
This framework contains support for...
* Local thru fully containerized execution
* Using Selenium Standalone containers eliminating the need for locally installed browsers or drivers
* Multiple local browsers with automatic driver management
* Tagging system (including and excluding)
* Groovy metaprogramming

## To Run the Automated Tests in Docker
The tests in this project can be run be run fully in Docker
assuming that Docker is installed and running.  This will build
a docker image of this project and execute the tests against
a Selenium Standalone container.

### Prerequisites
You must have docker installed and running on your local machine.

### To Run Fully in Docker
1. Ensure Docker is running
2. Run the `project docker-compose.yml` file (this runs using the Chrome
standalone container)
```
docker-compose up
```

## To Run the Automated Tests Locally
This is a single module maven project which will execute the automated tests.

To run the automated tests, execute...
`mvn clean test` *command-line-arguments*

* To run using the "built in" htmlunit **subset** of tests, execute...  
`mvn clean test -Dgeb.env=htmlunit`

### To Run the Automated Tests Using Selenium Browser Containers
The easiest way to run these tests locally is to use Docker and the Selenium Browser Containers.
1. Start Docker
2. `mvn clean test -Dgeb.env=dockerChrome` or `mvn clean test -Dgeb.env=dockerFirefox`

#### To See the Tests Run Using the VNC Server
1. Connect to [vnc://localhost:5900](vnc://localhost:5900) (On Mac you can simply enter this address into a Browser)
2. When prompted for the (default) password, enter `secret`

### Command Line Arguments
#### Specify Browser
`-Dgeb.env=`...

**Example:**
`-Dgeb.env=chrome`

Currently the following browsers are supported in this project:
* `htmlunit` - built-in
* `dockerChrome` - managed Selenium Chrome Container
* `dockerFirefox` - managed Selenium Firefox Container
* `chrome` - Google Chrome (requires Chrome)
* `chromeHeadless` - Google Chrome run in headless mode (requires Chrome > 59)
* `firefox` - Mozilla Firefox (requires Firefox)
* `firefoxHeadless` - Mozilla Firefox run in headless mode (requires Firefox > 56)
* `phantomjs` - PhantomJS headless browser 
* `safari` - Apple Safari (requires Safari)

#### Specify Target Environment
`-Dspock.run.target=`...

**Example:**
`-Dspock.run.target=qa`

#### Specify Tests To Include (Only Run)
`-Dspock.run.includes=`...

**Example:**
`-Dspock.run.includes=allexcludedfromhtmlunit,ondemand`

#### Specify Tests To Exclude
`-Dspock.run.excludes=`...

**Example:**
`-Dspock.run.excludes=javascript`

#### Specify Override Of Default And Context Exclusion
`Dspock.run.excludes.context=ignore`


### Minimal Requirements
* By default, tests will use the headless HtmlUnit browser

To run with specific browser drivers, look in the GebConfig.groovy file for any
configured environments and pass the value to your maven command using the
 'geb.env' command line argument.


**Example:**
`mvn clean test -Dgeb.env=chrome`


## Requirements
* Maven 3.x
* JDK 1.8
