FROM maven:3.6.3-jdk-8

ARG MAVEN_VERSION=3.6.3

COPY src /home/app/src
COPY pom.xml /home/app
COPY groovy-ant-18-suppression.xml /home/app

WORKDIR /home/app
RUN cd /home/app

# To Run the tests - altho this is orchestrated by the docker-compose.yml file
# RUN mvn -f /home/app/pom.xml clean test
