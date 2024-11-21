package endPoints.apps.ST;

import io.restassured.response.Response;
import payload.apps.OT.DetailShipmentPayload;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class DetailShipmentEndpoints {
    static String baseUrl = getURL().getString("baseRouteUrl");

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }

    public static Response getDetailShipment(DetailShipmentPayload payload){
        String getDetailUserUrl = baseUrl + getURL().getString("getDetailShipmentUrl");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
                .queryParam("id",payload.getId())
                .when()
                .get(getDetailUserUrl);

        return response;
    }


}
