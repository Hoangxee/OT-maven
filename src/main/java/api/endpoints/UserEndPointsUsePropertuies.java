package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsUsePropertuies {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
        return routes;
    }

    public static Response createUser(User payload){
        String addUserUrl = getURL().getString("addUserUrl");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(addUserUrl);

        return response;
    }

    public static Response getDetailUser(String username){
        String getDetailUserUrl = getURL().getString("getDetailUserUrl");

        Response response = given()
                .pathParam("username", username)
        .when()
                .get(getDetailUserUrl);

        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateUserUrl = getURL().getString("updateUserUrl");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
                .put(updateUserUrl);

        return response;
    }

    public static Response deleteUser(String username){
        String deleteUserUrl = getURL().getString("deleteUserUrl");

        Response response = given()
                .pathParam("username", username)
        .when()
                .get(deleteUserUrl);

        return response;
    }

}
