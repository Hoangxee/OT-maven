package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.addUserUrl);

        return response;
    }

    public static Response getDetailUser(String username){
        Response response = given()
                .pathParam("username", username)
        .when()
                .get(Routes.getDetailUserUrl);

        return response;
    }

    public static Response updateUser(String username, User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
                .put(Routes.updateUserUrl);

        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)
        .when()
                .get(Routes.deleteUserUrl);

        return response;
    }

}
