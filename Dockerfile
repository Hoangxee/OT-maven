#
# BUILD STAGE
#
FROM maven:3.9.5-eclipse-temurin-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

#
# PACKAGE STAGE
#
FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/demo-0.0.1-SNAPSHOT.jar /usr/app/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/usr/app/demo-0.0.1-SNAPSHOT.jar"]