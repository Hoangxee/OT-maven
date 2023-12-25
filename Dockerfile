# Reference: https://github.com/sunim2022/Jenkins_Docker/blob/9b55a490d3d83590a3eed3b064d73397a42d9de1/selenium-in-docker/Dockerfile
# Reference: https://stackoverflow.com/questions/72148859/how-do-i-store-maven-dependencies-inside-docker-image-using-a-dockerfile

FROM maven:3.9.6-eclipse-temurin-11

ARG UID=1000
ARG GID=1000

# Install tools.
RUN apt-get update && apt-get install -y gnupg2
RUN apt-get update -y & apt-get install -y wget
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y tzdata

# Install Chrome.
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update
RUN apt-get install -y google-chrome-stable

RUN chown $UID /root

USER $UID:$GID

WORKDIR /apps/automation-test/

COPY pom.xml /apps/automation-test/
RUN mvn -B dependency:go-offline

COPY --chown=$UID:$GID . .

RUN mvn clean

CMD ["mvn", "test"]
