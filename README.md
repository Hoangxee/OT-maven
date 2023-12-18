# Test Selenium

Run test for shopify

## Run the app

```bash
$ mvn clean && mvn test
```

## Run with Docker

Build image, run:

```bash
$ docker build -t testtask -f dockerFile .
```

Run the test:
```bash
$ docker run test
```

## Run with docker compose
Allure report is using this image: [allure-docker-service](https://hub.docker.com/r/frankescobar/allure-docker-service).
```bash
    docker compose up --build -d
```
View all running containers, run:
```bash
 docker ps
```

To stop all containers, run:
```bash
    docker compose down
```
