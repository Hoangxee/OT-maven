package com.shopify.apps.OT.Settings;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.OT_SettingsPageConstants;
import endPoints.apps.OT.SettingsEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.SettingsPageOTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import payload.apps.OT.TrackingLinkSetupPayload;
import utilities.Environment;


public class TrackingLinkSetupPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        trackingLinkSetupPayload = new TrackingLinkSetupPayload();
        trackingLinkSetupPayload.setShop(GlobalConstants.SHOP_API);
        trackingLinkSetupPayload.setUrlParams(GlobalConstants.URL_PARAM_API);
        trackingLinkSetupPayload.setReplaceCourierLink(replaceCourierLink);
        trackingLinkSetupPayload.setAddLinkToOrder(addLinkToOrder);
        trackingLinkSetupPayload.setLinkDescription(linkDescription);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
    }

    @Description("Tracking link set-up page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void trackingLinkSetup(){
        settingsOT = dashboardOT.openPageInSettings(OT_SettingsPageConstants.TRACKING_LINK_SETUP_IN_TRACKING_PAGE);
        settingsOT.checkedToReplaceCourierLinkCheckbox();
        settingsOT.sendKeyToLinkDescriptionInput(linkDescription);
        settingsOT.uncheckedToReplaceCourierLinkCheckbox();
        settingsOT.checkedToAddLinkToOrderCheckbox();
        settingsOT.uncheckedToAddLinkToOrderCheckbox();
    }

    @Description("Tracking link set-up page by API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void trackingLinkSetupByAPI(){
        Response updateTrackingLinkRP = SettingsEndpoints.updateTrackingLink(trackingLinkSetupPayload);
        SettingsEndpoints.verifyValueInResponse(updateTrackingLinkRP,"msg","Updated successfully!");

        Response getStatusRP = SettingsEndpoints.getTrackingLink(trackingLinkSetupPayload);
        SettingsEndpoints.verifyValueInResponse(getStatusRP,"msg","Get successfully!");
        SettingsEndpoints.verifyValueInResponse(getStatusRP,"data.replaceCourierLink",1);
        SettingsEndpoints.verifyValueInResponse(getStatusRP,"data.addLinkToOrder",1);
        SettingsEndpoints.verifyValueInResponse(getStatusRP,"data.linkDescription",linkDescription);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    TrackingLinkSetupPayload trackingLinkSetupPayload;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    SettingsPageOTAppObject settingsOT;
    int replaceCourierLink = 1;
    int addLinkToOrder = 1;
    String linkDescription = "Test Add a tracking link to Order Status page" + SettingsPageOTAppObject.random;

}
