package apiTest.endpoints;

import apiTest.payload.UserTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints_old {

    public static Response createUser(UserTest payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes_old.addUserUrl);

        return response;
    }

    public static Response getDetailUser(String username){
        Response response = given()
                .pathParam("username", username)
        .when()
                .get(Routes_old.getDetailUserUrl);

        return response;
    }

    public static Response updateUser(String username, UserTest payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
                .put(Routes_old.updateUserUrl);

        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)
        .when()
                .get(Routes_old.deleteUserUrl);

        return response;
    }

}
