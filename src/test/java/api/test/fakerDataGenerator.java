package api.test;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class fakerDataGenerator {

    @Test
    void testGenerateDummyData(){
        Faker faker = new Faker();

        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String userName = faker.name().username();
        String password = faker.internet().password();

        String phone = faker.phoneNumber().cellPhone();

        String email = faker.internet().safeEmailAddress();

        System.out.println("Full name: "+fullName);
        System.out.println("First name: "+firstName);
        System.out.println("Last name: "+lastName);
        System.out.println("User name: "+userName);
        System.out.println("Password: "+password);
        System.out.println("Phone: "+phone);
        System.out.println("Email: "+email);
    }

}
