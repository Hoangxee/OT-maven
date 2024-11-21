package endPoints.apps.ST;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import payload.apps.OT.SettingsPayload;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class SettingsEndpoints {
    static String baseUrl = getURL().getString("baseRouteUrl");

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }

    @Step("Get status of checkbox in Tracking link set-up page")
    public static Response getTrackingLink(SettingsPayload payload){
        String getTrackingLink = baseUrl + getURL().getString("trackingLinkUrl");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
                .when()
                .get(getTrackingLink);

        return response;
    }

    @Step("Update data in Tracking link set-up page")
    public static Response updateTrackingLink(SettingsPayload payload){
        String getTrackingLink = baseUrl + getURL().getString("trackingLinkUrl");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
                .when()
                .get(getTrackingLink);

        return response;
    }

}
