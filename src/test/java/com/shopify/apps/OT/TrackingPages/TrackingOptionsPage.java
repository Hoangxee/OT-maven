package com.shopify.apps.OT.TrackingPages;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.OT_TrackingPageConstants;
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
        trackingPageOT.chooseTrackingMethod(OT_TrackingPageConstants.ORDER_ID_OPTION_IN_TRACKING_METHOD);
        trackingPageOT.chooseShippingInfo(
                OT_TrackingPageConstants.TRACKING_LOGS_OPTION_IN_SHIPPING_INFO,
                OT_TrackingPageConstants.ORDER_DETAIL_OPTION_IN_SHIPPING_INFO);
        trackingPageOT.selectDateTimeFormat(
                OT_TrackingPageConstants.DATE_OPTION_IN_DATE_TIME_FORMAT,
                OT_TrackingPageConstants.TIME_OPTION_IN_DATE_TIME_FORMAT);
        trackingPageOT.selectProgressBar(
                OT_TrackingPageConstants.IN_TRANSIT_OPTION_IN_PROGRESS_BAR,
                OT_TrackingPageConstants.ORDER_READY_OPTION_IN_PROGRESS_BAR);
        trackingPageOT.clickToSaveBtn(OT_TrackingPageConstants.SAVE_SUCCESSFULLY_MESSAGE);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    TrackingPageOTAppObject trackingPageOT;

}
