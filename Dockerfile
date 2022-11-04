FROM openjdk:11-jdk-alpine
MAINTAINER GMAVROMMATIS
COPY target/vaadin-dev-server-settings.json vaadin-dev-server-settings.json
ENTRYPOINT ["java","-jar","/vaadin-dev-server-settings.json"]