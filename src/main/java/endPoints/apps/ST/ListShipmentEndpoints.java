package endPoints.apps.ST;

import static io.restassured.RestAssured.given;

import apiTest.payload.UserTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.apps.OT.ListShipmentPayload;

import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class ListShipmentEndpoints {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }

//    public static Response createUser(ListShipmentPayload payload){
//        String addUserUrl = getURL().getString("addUserUrl");
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(payload)
//                .when()
//                .post(createUrl);
//
//        return response;
//    }

    public static Response getListShipment(String shop, String urlParams, int page, int perPage, String fromDate, String toDate){
        String getDetailUserUrl = getURL().getString("getListShipmentUrl");

        Response response = given()
                .queryParam("shop",shop)
                .queryParam("urlParams",urlParams)
                .queryParam("page",page)
                .queryParam("perPage",perPage)
                .queryParam("fromDate",fromDate)
                .queryParam("toDate",toDate)
        .when()
                .get(getDetailUserUrl);

        return response;
    }
}
