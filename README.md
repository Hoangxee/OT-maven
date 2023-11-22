# Test Selenium 222

Run test for shopify

## Run the app

```bash
$ mvn clean && mvn test
```

## Run with Docker

Build image, run:

```bash
$ docker build -t test .
```

Run the test:
```bash
$ docker run test
```