package com.shopify.apps.OT;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.DatabaseUtil;
import utilities.Environment;

import java.sql.Connection;

public class TestConnect extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, "https://www.google.com/");

        connection = DatabaseUtil.getConnection(environment.dbDriver(),environment.dbHostname(),environment.dbUsername(),environment.dbPassword());
    }

    @Description("Test connect DB")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testConnectDB(){
        String sql = "SELECT * FROM `returns_drive_page_translation` WHERE store_id = 'hoangxe-test-9.myshopify.com'";
        String[] fields = {"id", "store_id", "login_page","stepper"};
        DatabaseUtil.executeQuery(connection,sql,fields);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    Connection connection;
    Environment environment;
}
