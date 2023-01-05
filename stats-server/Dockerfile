FROM amazoncorretto:11-alpine-jdk as build
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]