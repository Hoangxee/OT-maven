package api.test;

import apiTest.endpoints.UserTestApiEndpoints;
import apiTest.payload.UserTest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTestAPI {
    Faker faker;
    UserTest userPayload;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new UserTest();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test
    public void TC01_testPostUser(){
        Response response = UserTestApiEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void TC02_testGetDetailUserByName(){
        Response response = UserTestApiEndpoints.getDetailUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void TC03_testUpdateUserByName(){
        //update user using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserTestApiEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        //Check data after update
        Response responseAfterUpdate = UserTestApiEndpoints.getDetailUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test
    public void TC04_testDeleteUserByName(){
        Response response = UserTestApiEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
