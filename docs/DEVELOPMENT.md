## Development of sample-login-capybara-rspec
This project can be developed using the supplied container-based
development environment which includes Java and Maven.

The development environment container volume mounts your local source
code to recognize and persist any changes.

By default the development environment container executes the debian
`/bin/bash` shell providing a command line interface.

### Prerequisites
Before being able to run this project, you must follow the requirements
in the [PREREQUISITES.md](PREREQUISITES.md)

### To Develop Using the Container-Based Development Environment
The easiest way to run the containerized development environment is with
the docker-compose framework using the `dockercomposerun` script with the
`-d` (development environment) option...
```
./script/dockercomposerun -d
```

This will pull and run the latest development environment image of this
project along with the Chrome [Selenium Standalone](https://github.com/SeleniumHQ/docker-selenium)
container.

#### Running Just the Development Environment
To run the development environment on its own in the docker-compose
environment **without a Selenium browser**, use the `-o` option for
browsertests only and the `-d` option for the development environment...
```
./script/dockercomposerun -do
```

#### Building Your Own Development Environment Image
You can also build and run your own development environment image.

1. Build your development environment image specifying the `devenv` build
   stage as the target and supplying a name (tag) for the image.
   ```
   docker build --no-cache --target devenv -t browsertests-dev .
   ```

2. Run your development environment image in the docker-compose
   environment either on its own or with the Selenium Chrome
   (or other browser containers) and specify your development
   environment image with `BROWSERTESTS_IMAGE`
   ```
   BROWSERTESTS_IMAGE=browsertests-dev ./script/dockercomposerun -do
   ```

#### Specifying the Source Code Location
To use another directory as the source code for the development
environment, set the `BROWSERTESTS_SRC` environment variable.
For example...
```
BROWSERTESTS_SRC=${PWD} BROWSERTESTS_IMAGE=browsertests-dev ./script/dockercomposerun -d
```

#### Running the Tests and Security Scanning
To run the tests and security scanning in the development
environment like CI, use `maven`.

If you are running interactively (command line) in the development
environment...

* To run the **tests**...
  ```
  mvn clean tests
  ```

* To run just the dependency **security scan**...
  ```
  mvn clean verify -DskipTests=true
  ```
