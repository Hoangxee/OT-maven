# Test Selenium

Run test for shopify

## Run the app

```bash
$ mvn clean && mvn test
```

## Run with Docker

Build image, run:

```bash
#run docker from the beginning
$ docker build -t testtask .

#run docker with cache
#$ docker build -t testtask -f dockerFile .
```

Run the test:
```bash
$ docker run test
```

## Run with docker compose (Run in Ubuntu sub-system)
Allure report is using this image: [allure-docker-service](https://hub.docker.com/r/frankescobar/allure-docker-service).
```bash
USER_ID=`id -u` GROUP_ID=`id -g` docker compose up --build
```
View all running containers, run:
```bash
docker ps
```

To stop all containers, run:
```bash
docker compose down
```
