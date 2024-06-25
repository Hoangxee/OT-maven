package api.endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RetunsDriveAdminEndPoints {

    public static Response create_policy_url(String storeId){
        Response response = (Response)
            given()
                .pathParam("storeId",storeId)
            .when()
                .post(Routes.create_policy_url)
                .body();
        return response;
    }
}
