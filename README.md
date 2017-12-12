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
This example contains...
* support for multiple browsers
* tagging (including and excluding)
* Groovy metaprogramming

This is a single module maven project which will execute the automated tests.

## To Run the Automated Tests:
To run the automated tests, execute...
`mvn clean test` *command-line-arguments*

* To run using the default "built in" htmlunit **subset** of tests, execute...  
`mvn clean test`


### Command Line Arguments
#### Specify Browser
`-Dgeb.env=`...

**Example:**
`-Dgeb.env=chrome`

Currently the following browsers are supported in this project:
* `htmlunit` - the default
* `chrome` - Google Chrome (requires Chrome and chromedriver)
* `chromeHeadless` - Google Chrome run in headless mode (requires Chrome > 59 and chromedriver)
* `firefox` - Mozilla Firefox (requires Firefox and geckodriver)
* `phantomjs` - PhantomJS headless browser (requires PhantomJS)
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
