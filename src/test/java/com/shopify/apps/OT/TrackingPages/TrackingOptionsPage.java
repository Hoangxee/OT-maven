package com.shopify.apps.OT.TrackingPages;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.TrackingPageOTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;


public class TrackingOptionsPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
        trackingPageOT = dashboardOT.openTrackingPage();
    }

    @Description("Look and feel in Tracking page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_LookAndFeel(){
        trackingPageOT.openTrackingOptionsTab();
        trackingPageOT.chooseTrackingMethod("order id");



    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    TrackingPageOTAppObject trackingPageOT;

    // Image name
    String me = "me.jpg";
    String car = "car.jpg";
    String campfire = "campfire.jpg";
    String cafe1 = "cafe-1.jpg";
    String cafe2 = "cafe-2.jpg";
    // Image path
    String meFilePath = GlobalConstants.UPLOAD_FILE + me;
    String carFilePath = GlobalConstants.UPLOAD_FILE + car;
    String campfireFilePath = GlobalConstants.UPLOAD_FILE + campfire;
    String cafe1FilePath = GlobalConstants.UPLOAD_FILE + cafe1;
    String cafe2FilePath = GlobalConstants.UPLOAD_FILE + cafe2;

    String destinationUrl = "https://www.google.com/search?q=" + trackingPageOT.random;

}
