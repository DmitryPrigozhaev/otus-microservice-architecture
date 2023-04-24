FROM openjdk:11
MAINTAINER Dmitry Prigozhaev

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /opt/application.jar
ENTRYPOINT ["java", "-jar", "/opt/application.jar"]
