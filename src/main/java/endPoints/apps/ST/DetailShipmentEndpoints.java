package endPoints.apps.ST;

import io.restassured.response.Response;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class DetailShipmentEndpoints {
    static String baseUrl = getURL().getString("baseRouteUrl");

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }

    public static Response getDetailShipment(String shop, String urlParams, int id){
        String getDetailUserUrl = baseUrl + getURL().getString("getDetailShipmentUrl");

        Response response = given()
                .queryParam("shop",shop)
                .queryParam("urlParams",urlParams)
                .queryParam("id",id)
                .when()
                .get(getDetailUserUrl);

        return response;
    }


}
