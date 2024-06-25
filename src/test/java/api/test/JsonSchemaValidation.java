package api.test;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidation {

    @Test
    void jsonSchemaValidation(){
//        InputStream chatJsonSchema = getClass().getClassLoader()
//                .getResourceAsStream("chatJsonSchema.json");
        given()
        .when()
                .get("https://admin.returnsdrive.com/services/setting/detail/hoangxe-test-3.myshopify.com")
        .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("chatJsonSchema.json")); //file already in /test/resource
//        .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(chatJsonSchema));
    }

}
