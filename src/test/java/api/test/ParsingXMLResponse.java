package api.test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {
    //https://youtu.be/IB3G7IbdD1k?si=XnqkIycORcFgYywl

    @Test(priority = 1)
    void testXMLResponse(){
        given()
        .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
        .then()
                .statusCode(200)
                .header("ContentType", "charset=utf-8")
                .body("TravelerinfomationResponse.page",equalTo("1"))
                .body("TravelerinfomationResponse.travelers.Travelerinfomation[0].name",equalTo("Vijay"));
    }

    @Test(priority = 2)
    void testXMLResponseBody(){
        Response res =
        given()
        .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlObject = new XmlPath(res.asString());

        //Verify total number of travellers
        List<String> travelers = xmlObject.getList("TravelerinfomationResponse.travelers.Travelerinfomation");
        Assert.assertEquals(travelers.size(), 10);

        //Verify traveler name is present in response
        List<String> traveler_names = xmlObject.getList("TravelerinfomationResponse.travelers.Travelerinfomation.name");

        boolean status = false;
        for (String travelerName:traveler_names) {
            if(travelerName.equals("Vijay")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true);
    }

}
