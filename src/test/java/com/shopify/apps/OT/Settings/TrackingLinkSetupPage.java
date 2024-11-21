package com.shopify.apps.OT.Settings;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import endPoints.apps.ST.ListShipmentEndpoints;
import endPoints.apps.ST.SettingsEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.SettingsPageOTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import payload.apps.OT.SettingsPayload;
import utilities.Environment;

public class TrackingLinkSetupPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        settingsPayload = new SettingsPayload();
        settingsPayload.setShop(shop);
        settingsPayload.setUrlParams(urlParams);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
    }

    @Description("Tracking link set-up page")
    @Severity(SeverityLevel.NORMAL)
//    @Test
    public void trackingLinkSetup(){
        settingsOT = dashboardOT.openTrackingLinkSetupPage();
        settingsOT.checkedToReplaceCourierLinkCheckbox();
        settingsOT.sendKeyToLinkDescriptionInput("Test Add a tracking link to Order Status page");
        settingsOT.checkedToAddLinkToOrderCheckbox();
        settingsOT.uncheckedToReplaceCourierLinkCheckbox();
        settingsOT.uncheckedToAddLinkToOrderCheckbox();
    }

    @Description("Tracking link set-up page by API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void trackingLinkSetupByAPI(){
        Response response = SettingsEndpoints.getTrackingLink(settingsPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    Environment environment;
    SettingsPayload settingsPayload;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    SettingsPageOTAppObject settingsOT;
    String shop = GlobalConstants.SHOPIFY_STORE_FRONT_URL;
    String urlParams = "by-passs";
}
