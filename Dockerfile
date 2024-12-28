#--- Base Image ---
# Ruby version must match that in Gemfile.lock
ARG BASE_IMAGE=maven:3.8-adoptopenjdk-16
FROM ${BASE_IMAGE} AS maven-jdk

#--- Dev Env Stage ---
FROM maven-jdk AS devenv

# ASSUME project source is volume mounted into the container at path /app
WORKDIR /app

# Start devenv in (command line) shell
CMD ["bash"]

# --- Deploy ---
FROM maven-jdk AS deploy

# Add a non-root user to run the tests
# TODO: This works as this user but there is this noise...
#   Can not write to /root/.m2/copy_reference_file.log. Wrong volume permissions? Carrying on ...
ARG DEPLOY_USER=deployer
RUN adduser --disabled-password --gecos '' ${DEPLOY_USER}
USER ${DEPLOY_USER}

WORKDIR /app
# Copy pom and suppressed vulnerabilities
COPY --chown=deployer pom.xml suppress-jackson-core-2182.xml /app/
# Copy the source to /app
COPY --chown=deployer src /app/src

# Overridable: Run the tests
CMD ["mvn", "clean", "test"]
