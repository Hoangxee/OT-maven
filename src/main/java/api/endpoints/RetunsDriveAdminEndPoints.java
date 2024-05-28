package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.openqa.selenium.remote.Response;

public class RetunsDriveAdminEndPoints {
    public static Response create_policy_url(String storeId){
        Response response = (Response)
            given()
                .pathParam("storeId",storeId)
            .when()
                .get(Routes.create_policy_url);
        return response;
    }
}
