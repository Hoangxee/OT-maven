package com.shopify.apps.OT.Settings;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.OT_SettingsPageConstants;
import endPoints.apps.ST.SettingsEndpoints;
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
import payload.apps.OT.SettingsPayload;
import utilities.Environment;

import java.util.Arrays;
import java.util.List;


public class CourierMappingPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        settingsPayload = new SettingsPayload();
        settingsPayload.setShop(shop);
        settingsPayload.setUrlParams(urlParams);
        settingsPayload.setReplaceCourierLink(replaceCourierLink);
        settingsPayload.setAddLinkToOrder(addLinkToOrder);
        settingsPayload.setLinkDescription(linkDescription);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
    }

    @Description("Frequently used couriers in Courier mapping page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_frequentlyUsedCouriers(){
        settingsOT = dashboardOT.openPageInSettings(OT_SettingsPageConstants.COURIER_MAPPING_IN_TRACKING_PAGE);
        settingsOT.deleteAllCourierInFrequentlyUsedCouriers();
        settingsOT.chooseCourierInFrequentlyUsedCouriers(actualCourier);
    }

    @Description("Courier Mapping in Courier mapping page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_courierMapping(){
        settingsOT.deleteAllCourierMapping();
        settingsOT.addCourierMapping(actualCourier,shopifyCourier);
    }

    @Description("Frequently used couriers in Courier mapping page by API")
    @Severity(SeverityLevel.NORMAL)
//    @Test
    public void frequentlyUsedCouriersByAPI(){
        Response updateTrackingLinkRP = SettingsEndpoints.updateTrackingLink(settingsPayload);
        SettingsEndpoints.verifyMessageValueInInUpdateTrackingLink(updateTrackingLinkRP,"msg","Updated successfully!");

        Response getStatusRP = SettingsEndpoints.getTrackingLink(settingsPayload);
        SettingsEndpoints.verifyMessageValueInGetTrackingLink(getStatusRP,"msg","Get successfully!");
        SettingsEndpoints.verifyReplaceCourierLinkValueInGetTrackingLink(getStatusRP,"data.replaceCourierLink",1);
        SettingsEndpoints.verifyAddLinkToOrderValueInGetTrackingLink(getStatusRP,"data.addLinkToOrder",1);
        SettingsEndpoints.verifyLinkDescriptionValueInGetTrackingLink(getStatusRP,"data.linkDescription",linkDescription);
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
    String shop = "returns-drive.myshopify.com";
    String urlParams = "by-passs";
    int replaceCourierLink = 1;
    int addLinkToOrder = 1;
    String linkDescription = "Test Add a tracking link to Order Status page" + SettingsPageOTAppObject.random;
    List<String> actualCourier = Arrays.asList(
            OT_SettingsPageConstants.ACTUAL_COURIER_USPS,
            OT_SettingsPageConstants.ACTUAL_COURIER_DHL,
            OT_SettingsPageConstants.ACTUAL_COURIER_TNT,
            OT_SettingsPageConstants.ACTUAL_COURIER_OSM,
            OT_SettingsPageConstants.ACTUAL_COURIER_CANADA_POST);
    List<String> shopifyCourier = Arrays.asList(
            OT_SettingsPageConstants.SHOPIFY_COURIER_4PX,
            OT_SettingsPageConstants.SHOPIFY_COURIER_FEDEX,
            OT_SettingsPageConstants.SHOPIFY_COURIER_EAGLE,
            OT_SettingsPageConstants.SHOPIFY_COURIER_GLS,
            OT_SettingsPageConstants.SHOPIFY_COURIER_DELHIVERY);
}
