package com.shopify.apps.ST.SettingsPage;

import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import commons.constant.STConstants;
import commons.constant.ST_SettingsPageConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.apps.ST.SettingsPageSTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;

public class CourierMappingPage extends BaseTest {

    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);
        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
    }

    @Description("Add mapping rules")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_AddCourierMappingRules() {
        homePageST = homePage.openAppSynctrack();
        settingsPageST = homePageST.openSettingsPage();
        settingsPageST.openCourierMappingTab();
        settingsPageST.addCourierMappingRules(ST_SettingsPageConstants.COURIER_NAME_OPTION_IN_MAPPING_TYPE_DROPDOWN,courierName,paypalCourier);
        settingsPageST.addCourierMappingRules(ST_SettingsPageConstants.TRACKING_NUMBER_START_WITH_OPTION_IN_MAPPING_TYPE_DROPDOWN,courierName,paypalCourier);
        settingsPageST.addCourierMappingRules(ST_SettingsPageConstants.TRACKING_NUMBER_END_WITH_OPTION_IN_MAPPING_TYPE_DROPDOWN,courierName,paypalCourier);
    }

    @Description("Delete all mapping rules")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_DeleteAllCourierMappingRules() {
        settingsPageST.deleteAllCourierMappingRules();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    HomePageSTAppObject homePageST;
    SettingsPageSTAppObject settingsPageST;
    String courierName = "AliExpress";
    String paypalCourier = "4PX Express";
}
