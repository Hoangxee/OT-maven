package endPoints.apps.ST;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.aeonbits.owner.Config;
import payload.apps.OT.ListShipmentPayload;
import payload.apps.OT.SettingsPayload;

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

    public static Response getListShipment(ListShipmentPayload payload){
        String getListUserUrl = baseUrl + getURL().getString("getListShipmentUrl");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
                .queryParam("page",payload.getPage())
                .queryParam("perPage",payload.getPerPage())
                .queryParam("fromDate",payload.getFromDate())
                .queryParam("toDate",payload.getToDate())
        .when()
                .get(getListUserUrl);

        return response;
    }

    static String baseUrl = getURL().getString("baseRouteUrl");
}
