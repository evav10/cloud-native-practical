FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=hsqldb","-jar","/app.jar"]