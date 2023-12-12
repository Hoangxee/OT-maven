# Reference: https://github.com/sunim2022/Jenkins_Docker/blob/9b55a490d3d83590a3eed3b064d73397a42d9de1/selenium-in-docker/Dockerfile
# Reference: https://stackoverflow.com/questions/72148859/how-do-i-store-maven-dependencies-inside-docker-image-using-a-dockerfile
FROM maven:3.9.5-eclipse-temurin-11

WORKDIR /apps/automation-test

COPY pom.xml .

RUN mvn clean

RUN mvn -B dependency:go-offline

COPY . .

RUN chmod -R 777 /apps/automation-test

# Install tools.
RUN apt update && apt-get install -y gnupg2
RUN apt update -y & apt-get install -y wget
# RUN apt update -y & apt install -y wget unzip
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y tzdata

# Install Chrome.
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update
RUN apt-get install -y google-chrome-stable 

RUN mvn package

CMD ["java", "-jar", "./target/Order-tracking-1.0-SNAPSHOT.jar"]

# #Copy source code and pom file.
# # COPY src /apps/automation-test/src
# # COPY pom.xml /apps/automation-test
# # COPY browserDrivers /apps/automation-test/browserDrivers

# # RUN chmod -R 777 /apps/automation-test/browserDrivers

# ENV env_browser_param Chrome
# ENTRYPOINT mvn test -Dbrowser_param=${env_browser_param}
CMD ["mvn", "test"]



# FROM openjdk:11-jre-slim as builder
# WORKDIR application
# ADD maven/kbe-rest-brewery-0.0.1-SNAPSHOT.jar ./
# RUN java -Djarmode=layertools -jar order-tracking-SNAPSHOT.jar extract

# FROM openjdk:11-jre-slim

# WORKDIR application
# COPY --from=builder application/dependencies/ ./
# COPY --from=builder application/spring-boot-loader/ ./
# COPY --from=builder application/snapshot-dependencies/ ./
# COPY --from=builder application/application/ ./
# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]