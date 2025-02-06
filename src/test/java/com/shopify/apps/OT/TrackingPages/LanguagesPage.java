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


public class LanguagesPage extends BaseTest {
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

    @Description("Language in Tracking page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_Languages(){
        trackingPageOT.openLanguagesTab();
        trackingPageOT.selectGoogleLanguage("Japanese", "Right top");
        trackingPageOT.checkedGoogleTranslateCheckbox();
        trackingPageOT.sendKeyToField("In Transit","In Transit automation test");
        trackingPageOT.clickToSaveBtn(OT_TrackingPageConstants.UPDATE_LANGUAGES_SUCCESSFULLY_MESSAGE);
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
