FROM amazoncorretto:11-alpine-jdk
MAINTAINER GMAVROMMATIS
COPY target/vaadin-demo-0.0.1-SNAPSHOT.jar vaadin-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/vaadin-demo-0.0.1-SNAPSHOT.jar"]