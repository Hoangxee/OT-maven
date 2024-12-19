package endPoints.apps.OT;

import commons.constant.OT_SettingsPageConstants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import payload.apps.OT.CourierMappingPayload;
import payload.apps.OT.FrequentlyUsedCouriersPayload;
import payload.apps.OT.TrackingLinkSetupPayload;
import utilities.APIUtil;

import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        String getCourierMapping = baseUrl + getURL().getString("courierMapping");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
        .when()
                .get(getCourierMapping)
        .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    @Step("Verify Courier mapping data in getCourierMapping response")
    public static void verifyCourierMapping(Response response, List<String> actualCourier, List<String> shopifyCourier){
        for (int i = 0; i < actualCourier.size(); i++) {
            String actualCourier1 = actualCourier.get(i).toUpperCase();
            String shopifyCourier1 = shopifyCourier.get(i).toUpperCase();
            String from = getString(response,"data.couriersMapping["+i+"].from").toUpperCase();
            String to = getString(response,"data.couriersMapping["+i+"].to").toUpperCase();

            Assert.assertEquals(from, actualCourier1);
            Assert.assertEquals(to, shopifyCourier1);

        }
    }

    @Step("Create Courier mapping")
    public static Response createCourierMapping(CourierMappingPayload payload){
        String createCourierMappingLink = baseUrl + getURL().getString("courierMapping");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(createCourierMappingLink)
        .then()
                .statusCode(201)
                .extract().response();

        return response;
    }

    public static void createMultipleCourierMapping(CourierMappingPayload payload, List<String> actualCouriers, List<String> shopifyCouriers){
        if (actualCouriers.size() != shopifyCouriers.size()) {
            throw new IllegalArgumentException("The Shopify Courier and Actual Courier lists must be the same length.");
        }

        for (int i = 0; i < actualCouriers.size(); i++) {
            payload.setFrom(actualCouriers.get(i));
            payload.setTo(shopifyCouriers.get(i));
            Response response = createCourierMapping(payload);

//            System.out.println("Response: " + response.asString());
            verifyValueInResponse(response, "msg",
                    OT_SettingsPageConstants.CREATE_COURIER_MAPPING_SUCCESSFULLY_API);
        }
    }

    @Step("Delete Courier Mapping")
    public static Response deleteCourierMapping(CourierMappingPayload payload){
        String deleteCourierMapping = baseUrl + getURL().getString("courierMapping");

        Response response = given()
                .queryParam("shop",payload.getShop())
                .queryParam("urlParams",payload.getUrlParams())
                .queryParam("id",payload.getId())
        .when()
                .delete(deleteCourierMapping)
        .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    public static void deleteAllCourierMapping(CourierMappingPayload payload, List<Integer> courierMappingId){
        for (int i = 0; i < courierMappingId.size(); i++) {
            payload.setId(courierMappingId.get(i));
            Response response = deleteCourierMapping(payload);

//            System.out.println("Response: " + response.asString());
            verifyValueInResponse(response, "msg",
                    OT_SettingsPageConstants.DELETE_COURIER_MAPPING_SUCCESSFULLY_API);
        }
    }

    @Step("Update Courier Mapping")
    public static Response updateCourierMapping(CourierMappingPayload payload){
        String updateCourierMapping = baseUrl + getURL().getString("courierMapping");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .put(updateCourierMapping)
        .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    public static void updateAllCourierMapping(CourierMappingPayload payload, List<Integer> courierMappingId, List<String> actualCouriersUpdate, List<String> shopifyCouriersUpdate){
        for (int i = 0; i < courierMappingId.size(); i++) {
            payload.setId(courierMappingId.get(i));
            payload.setFrom(actualCouriersUpdate.get(i));
            payload.setTo(shopifyCouriersUpdate.get(i));
            Response response = updateCourierMapping(payload);

            verifyValueInResponse(response, "msg",
                    OT_SettingsPageConstants.UPDATE_COURIER_MAPPING_SUCCESSFULLY_API);
        }
    }

}
