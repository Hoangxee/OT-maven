package api.test;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class XMLSchemaValidation {

    //https://youtu.be/CyPsHcvl0vE?si=cqFr9Qa-2QxWYNjv
    @Test
    void xmlSchemaValidation(){
        given()
        .when()
                .get("https://petstore.swagger.io/v2/pet/1")
        .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("chatXMLSchema.xsd"));
    }

}
