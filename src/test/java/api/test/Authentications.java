package api.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {

//    @Test
    void testBasicAuthentication_Pass(){
        given()
                .auth().basic("postman", "password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

//    @Test
    void testBasicAuthentication_Fail(){
        given()

        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(401)
                .body(equalTo("Unauthorized"))
                .log().all();
    }

//    @Test
    void testDigestAuthentication_Pass(){
        given()
                .auth().digest("postman", "password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

//    @Test
    void testDigestAuthentication_Fail(){
        given()
                .auth().digest("postman", "password1")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(401)
                .body(equalTo("Unauthorized"))
                .log().all();
    }

//    @Test
    void testPreemptiveAuthentication_Pass(){
        given()
                .auth().preemptive().basic("postman", "password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

//    @Test
    void testPreemptiveAuthentication_Fail(){
        given()
                .auth().preemptive().basic("postman", "password1")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(401)
                .body(equalTo("Unauthorized"))
                .log().all();
    }

//    @Test
    void testBearerAuthentication(){
        String bearerToken = "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";

        given()
                .headers("Authorization","Bearer "+bearerToken)
        .when()
                .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200)
                .log().all();
    }

//    @Test
    void testOAuthVer1Authentication(){
        given()
                .auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrete") // this is for OAuth1.0 authentication
        .when()
                .get("url")
        .then()
                .statusCode(200)
                .log().all();
    }

//    @Test
    void testOAuthVer2Authentication(){
        given()
                .auth().oauth2("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")
        .when()
                .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testAPIKeyAuthentication(){
        //API key add to query param
        //API key add to header -> testBearerAuthentication()

        String urlFull = "https://openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7";

        String APIKey = "fe9c5cddb7e01d747b4611c3fc9eaf2c";
        String param_mypath_value = "data/2.5/forecast/daily";
        String param_q_value = "Delhi";
        String param_units_value = "metric";
        String param_cnt_value = "7";

//        //Method 1
//        given()
//                .queryParam("appid", APIKey)
//        .when()
//                .get(urlFull)
//        .then()
//                .statusCode(200)
//                .log().all();

        //Method 2
        given()
                .queryParam("appid", APIKey)
                .pathParams("mypath",param_mypath_value)
                .queryParam("q", param_q_value)
                .queryParam("units", param_units_value)
                .queryParam("cnt", param_cnt_value)
        .when()
                .get("https://openweathermap.org/{mypath}")
        .then()
                .statusCode(200)
                .log().all();
    }

}
