package endPoints.apps.ST;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import payload.apps.OT.SettingsPayload;
import utilities.APIUtil;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class SettingsEndpoints extends APIUtil {
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
                .get(getTrackingLink)
       .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    @Step("verify {0}  value in response after run GetTrackingLink api")
    public static void verifyMessageValueInGetTrackingLink(Response response, String jsonPath, String expectedValue){
        Assert.assertEquals(getString(response,jsonPath),expectedValue);
    }

    @Step("verify {0}  value in response after run GetTrackingLink api")
    public static void verifyReplaceCourierLinkValueInGetTrackingLink(Response response, String jsonPath, int expectedValue){
        Assert.assertEquals(getInt(response,jsonPath),expectedValue);
    }

    @Step("verify {0}  value in response after run GetTrackingLink api")
    public static void verifyAddLinkToOrderValueInGetTrackingLink(Response response, String jsonPath, int expectedValue){
        Assert.assertEquals(getInt(response,jsonPath),expectedValue);
    }

    @Step("verify {0}  value in response after run GetTrackingLink api")
    public static void verifyLinkDescriptionValueInGetTrackingLink(Response response, String jsonPath, String expectedValue){
        Assert.assertEquals(getString(response,jsonPath),expectedValue);
    }

    @Step("Update data in Tracking link set-up page")
    public static Response updateTrackingLink(SettingsPayload payload){
        String putTrackingLink = baseUrl + getURL().getString("trackingLinkUrl");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .put(putTrackingLink)
        .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    @Step("verify {0}  value in response after run GetTrackingLink api")
    public static void verifyMessageValueInInUpdateTrackingLink(Response response, String jsonPath, String expectedValue){
        Assert.assertEquals(getString(response,jsonPath),expectedValue);
    }

}
