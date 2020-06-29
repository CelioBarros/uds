FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/uds-1.0.0.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]