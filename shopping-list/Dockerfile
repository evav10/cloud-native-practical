FROM maven:3.5.2-jdk-8-alpine AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean install package -Dspring.profiles.active=hsqldb

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=hsqldb","-jar","/app.jar"]
