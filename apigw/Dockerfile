FROM openjdk:17-jdk-alpine

ARG JAR_PATH=target/*.jar

COPY ${JAR_PATH} apigw-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/apigw-0.0.1-SNAPSHOT.jar"]