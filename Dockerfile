# Dockerfile
FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} EspritHubBack.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


