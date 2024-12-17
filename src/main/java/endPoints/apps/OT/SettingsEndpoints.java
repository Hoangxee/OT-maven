package endPoints.apps.OT;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import payload.apps.OT.CourierMappingPayload;
import payload.apps.OT.FrequentlyUsedCouriersPayload;
import payload.apps.OT.TrackingLinkSetupPayload;
import utilities.APIUtil;

import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class SettingsEndpoints extends APIUtil {
    static String baseUrl = getURL().getString("baseRouteUrl");

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routesOT"); //load properties file
        return routes;
    }


    @Step("Get status of checkbox in Tracking link set-up page")
    public static Response getTrackingLink(TrackingLinkSetupPayload payload){
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

    @Step("verify {1}  value in response")
    public static void verifyValueInResponse(Response response, String jsonPath, String expectedValue){
        Assert.assertEquals(getString(response,jsonPath),expectedValue);
    }

    @Step("verify {1}  value in response")
    public static void verifyValueInResponse(Response response, String jsonPath, int expectedValue){
        Assert.assertEquals(getInt(response,jsonPath),expectedValue);
    }

    @Step("verify {1}  value in response")
    public static void verifyValueInResponse(Response response, String jsonPath, List<String> expectedValue){
        Assert.assertEquals(getList(response,jsonPath),expectedValue);
    }

    @Step("Update data in Tracking link set-up page")
    public static Response updateTrackingLink(TrackingLinkSetupPayload payload){
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

    @Step("Add Frequently used couriers")
    public static Response addFrequentlyUsedCouriers(FrequentlyUsedCouriersPayload payload){
        String courierSetting = baseUrl + getURL().getString("courierSetting");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(courierSetting)
        .then()
                .statusCode(201)
                .extract().response();

        return response;
    }

    @Step("Get Frequently used couriers and Courier Mapping")
    public static Response getCourierMapping(CourierMappingPayload payload){
        String getCourierMapping = baseUrl + getURL().getString("getCourierMapping");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
        .when()
                .get(getCourierMapping)
        .then()
                .statusCode(200).log().body()
                .extract().response();

        return response;
    }

}
