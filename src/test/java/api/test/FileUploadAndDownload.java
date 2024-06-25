package api.test;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileUploadAndDownload {
    String projectPath = System.getProperty("user.dir");
    String campfire = "fire.jpg";
    String me = "me.jpg";
    String car = "car.jpg";
    String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
    String campfireFilePath = uploadFileFolderPath + campfire;
    String meFilePath = uploadFileFolderPath + me;

    @Test
    void singleFileUpload(){
        File myFile = new File(campfireFilePath);

        given()
                .multiPart("file",myFile)
                .contentType("multipart/form-data")
        .when()
                .post("https://admin.returnsdrive.com/services/file/images")
        .then()
                .statusCode(200)
                .body("fileName", equalTo("campfire.jpg"))
                .log().all();
    }

    @Test
    void multipleFileUpload(){
        File myFile = new File(campfireFilePath);
        File myFile2 = new File(meFilePath);
        File fileArr[] = {myFile,myFile2};

        given()
                .multiPart("file",fileArr)
                .contentType("multipart/form-data")
        .when()
                .post("https://admin.returnsdrive.com/services/file/images")
        .then()
                .statusCode(200)
                .body("fileName", equalTo("campfire.jpg"))
                .body("fileName", equalTo("me.jpg"))
                .log().all();
    }

    @Test
    void fileDownload(){
        given()
        .when()
                .get("https://admin.returnsdrive.com/services/file/download/orders")
        .then()
                .statusCode(200)
                .log().all();
    }
    
}
