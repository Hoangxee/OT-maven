package endPoints.apps.ST;

import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class SettingsEndpoints {
    static String baseUrl = getURL().getString("baseRouteUrl");

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }

    public static Response getTrackingLink(String shop, String urlParams){
        String getTrackingLink = baseUrl + getURL().getString("getTrackingLinkUrl");

        Response response = given()
                .queryParam("shop",shop)
                .queryParam("urlParams",urlParams)
        .when()
                .get(getTrackingLink);

        return response;
    }

}
