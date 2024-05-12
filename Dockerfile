FROM openjdk:17-jdk

COPY target/EspritHubTestBack.jar .

ENTRYPOINT ["java", "-jar", "EspritHubTestBack.jar"]