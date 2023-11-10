FROM openjdk:17-jdk-alpine

ARG JAR_PATH=target/*.jar

ARG PROP

ENV ENV_FILE=${PROP}

COPY ${JAR_PATH} amqp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/amqp-0.0.1-SNAPSHOT.jar","--spring.config.location=${ENV_FILE}"]