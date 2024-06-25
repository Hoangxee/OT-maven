package api.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponse {
    /*
- given(): content tye, set cookies, add auth, add param, set header info...
- when(): get, post, put, delete
- then(): validate status, extract response, extract headers cookies & response body...
 */

    //https://youtu.be/5fWDqLFbJnA?si=47pqYkBBAGiNY3Or
    @Test(priority = 1)
    void testJsonResponse(){
        //Appoach 1
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .header("Content-Type", "application")
                .body("book[3].title",equalTo("Test"));

        /*
        //Appoach 2 - https://youtu.be/5fWDqLFbJnA?si=bpM0hb7Ac3NJ74-e
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("https://reqres.in/api/users?page=2");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.header("Content-Type"),"application");
        Assert.assertEquals(response.jsonPath().get("book[3].title").toString(),"Test");
         */
    }

    @Test (priority = 2)
    void testJsonResponseBodyData(){

        Response res =
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .get("https://reqres.in/api/users?page=2");

        //JSONObject class
        JSONObject jo = new JSONObject(res.toString()); // converting response to json object type

        //validate title of book
        boolean status = false;
        for(int i = 0;i<jo.getJSONArray("book").length();i++){
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();

            if (bookTitle.equals("The lord of the Rings")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true);

        //validate total price of book
        double totalprice = 0;
        for(int i = 0;i<jo.getJSONArray("book").length();i++){
            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();

            totalprice = totalprice + Double.parseDouble(price);
        }

        System.out.println();

//[
//        {
//            "id":1,
//                "name":"John",
//                "location":"india",
//                "phone":"1234567890",
//                "courses":[
//            "Java",
//                    "Selenium"
//		]
//        },
//        {
//            "id":2,
//                "name":"Shin",
//                "location":"china",
//                "phone":"1234567890",
//                "courses":[
//            "Python",
//                    "Selenium"
//		]
//        }
//]
//        JSONArray jrr = new JSONArray(res.asString());
//        jrr.getJSONObject(0).getJSONArray("courses").get(1);
    }


}
