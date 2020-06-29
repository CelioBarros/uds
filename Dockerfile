FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD target/uds-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]