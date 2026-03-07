## Development

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

   ```sh
   docker build --no-cache --target devenv -t browsertests-dev .
   ```

2. Run your development environment image in the docker-compose
   environment either on its own or with the Selenium Chrome
   (or other browser containers) and specify your development
   environment image with `BROWSERTESTS_IMAGE`

   ```sh
   BROWSERTESTS_IMAGE=browsertests-dev ./script/dockercomposerun -do
   ```

#### Specifying the Source Code Location

To use another directory as the source code for the development
environment, set the `BROWSERTESTS_SRC` environment variable.
For example...

```sh
BROWSERTESTS_SRC=${PWD} BROWSERTESTS_IMAGE=browsertests-dev ./script/dockercomposerun -d
```

### Operating

Once inside the environment, whether native or in the container,
operating the project is the same.

#### Running the Tests

To run the **tests**, use `maven test`...

```sh
mvn clean tests
```

You can also use the `run` `script`...

```sh
./script/run tests
```

#### Running the Dependency Security Scanning

> This project uses the Open Worldwide Application Security Project ([OWASP](https://owasp.org/))
> [`dependency-check-maven`](https://dependency-check.github.io/DependencyCheck/)
> package to perform the dependency security scanning
>
> :key: You will need your `NVD_API_KEY`, see the [PREREQUISITES.md](PREREQUISITES.md)

To run the project's dependency security scanning you can use `maven`
or run `org.owasp:dependency-check-maven` directly.

To run the dependency **security scan**, use `maven verify`...

```sh
mvn clean verify -DskipTests=true
```

You can also use the `run` `script`...

```sh
./script/run secscan
```

##### Operating org.owasp:dependency-check-maven Directly

You can and will probably need to operate the
`org.owasp:dependency-check-maven` plugin directly
if updating the project and resolving dependencies.

Running the OWASP Checks Directly

To run the dependency **security scan**...

```sh
mvn org.owasp:dependency-check-maven:check
```

To clear the vulnerability database...

```sh
mvn org.owasp:dependency-check-maven:purge
```
