FROM openjdk:17-jdk-alpine

ARG JAR_PATH=target/*.jar

ARG PROP

ENV ENV_FILE=${PROP}

COPY ${JAR_PATH} fraud-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/fraud-0.0.1-SNAPSHOT.jar","--spring.config.location=${ENV_FILE}"]