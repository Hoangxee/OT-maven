# Reference: https://github.com/sunim2022/Jenkins_Docker/blob/9b55a490d3d83590a3eed3b064d73397a42d9de1/selenium-in-docker/Dockerfile
# Reference: https://stackoverflow.com/questions/72148859/how-do-i-store-maven-dependencies-inside-docker-image-using-a-dockerfile
FROM maven:3.8.6-jdk-11

WORKDIR /apps/qa
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn package

RUN chmod -R 777 /apps/qa

# Install tools.
RUN apt update -y & apt install -y wget unzip
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y tzdata

# Install Chrome.
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update
RUN apt-get install -y google-chrome-stable 

ENV env_browser_param Chrome

#Copy source code and pom file.
# COPY src /apps/qa/src
# COPY pom.xml /apps/qa
# COPY browserDrivers /apps/qa/browserDrivers

# RUN chmod -R 777 /apps/qa/browserDrivers

ENTRYPOINT mvn test -Dbrowser_param=${env_browser_param}