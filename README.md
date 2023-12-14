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