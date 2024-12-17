package com.shopify.apps.OT.Settings;

import commons.BaseTest;
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
import payload.apps.OT.CourierMappingPayload;
import payload.apps.OT.FrequentlyUsedCouriersPayload;
import utilities.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CourierMappingPage extends BaseTest {
    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env", environmentName);
        environment = ConfigFactory.create(Environment.class);

        courierSetting = new FrequentlyUsedCouriersPayload();
        courierSetting.setShop(GlobalConstants.SHOP_API);
        courierSetting.setUrlParams(GlobalConstants.URL_PARAM_API);
        courierSetting.setPayload(actualCourierLowerKey);

        courierMapping = new CourierMappingPayload();
        courierMapping.setShop(GlobalConstants.SHOP_API);
        courierMapping.setUrlParams(GlobalConstants.URL_PARAM_API);


        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

//        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
//        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
//                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
//        dashboardOT = homePage.openAppOrderTracking();
    }

    @Description("Frequently used couriers in Courier mapping page")
    @Severity(SeverityLevel.NORMAL)
//    @Test
    public void TC01_frequentlyUsedCouriers() {
        settingsOT = dashboardOT.openPageInSettings(OT_SettingsPageConstants.COURIER_MAPPING_IN_TRACKING_PAGE);
        settingsOT.deleteAllCourierInFrequentlyUsedCouriers();
        settingsOT.chooseCourierInFrequentlyUsedCouriers(actualCourier);
    }

    @Description("Courier Mapping in Courier mapping page")
    @Severity(SeverityLevel.NORMAL)
//    @Test
    public void TC02_courierMapping() {
        settingsOT.deleteAllCourierMapping();
        settingsOT.addCourierMapping(actualCourier, shopifyCourier);
    }

    @Description("Frequently used couriers in Courier mapping page by API")
    @Severity(SeverityLevel.NORMAL)
//    @Test
    public void TC03_frequentlyUsedCouriersByAPI() {
        Response addFrequentlyUsedCouriers = SettingsEndpoints.addFrequentlyUsedCouriers(courierSetting);
        SettingsEndpoints.verifyValueInResponse(addFrequentlyUsedCouriers,"msg","Success");
    }

    @Description("Frequently used couriers in Courier mapping page by API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC04_courierMappingByAPI() {
        Response getCourierMapping = SettingsEndpoints.getCourierMapping(courierMapping);
        SettingsEndpoints.verifyValueInResponse(getCourierMapping,"data.courierSettings",actualCourierLowerKey);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    FrequentlyUsedCouriersPayload courierSetting;
    CourierMappingPayload courierMapping;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    SettingsPageOTAppObject settingsOT;
    List<String> actualCourier = Arrays.asList(
            OT_SettingsPageConstants.ACTUAL_COURIER_USPS,
            OT_SettingsPageConstants.ACTUAL_COURIER_DHL,
            OT_SettingsPageConstants.ACTUAL_COURIER_TNT,
            OT_SettingsPageConstants.ACTUAL_COURIER_OSM,
            OT_SettingsPageConstants.ACTUAL_COURIER_SENDLE);
    List<String> shopifyCourier = Arrays.asList(
            OT_SettingsPageConstants.SHOPIFY_COURIER_4PX,
            OT_SettingsPageConstants.SHOPIFY_COURIER_FEDEX,
            OT_SettingsPageConstants.SHOPIFY_COURIER_EAGLE,
            OT_SettingsPageConstants.SHOPIFY_COURIER_GLS,
            OT_SettingsPageConstants.SHOPIFY_COURIER_DELHIVERY);
    List<String> actualCourierLowerKey = actualCourier.stream().map(String::toLowerCase).collect(Collectors.toList());
}
