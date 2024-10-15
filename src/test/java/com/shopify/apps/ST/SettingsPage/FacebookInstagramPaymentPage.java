package com.shopify.apps.ST.SettingsPage;

import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import commons.constant.STConstants;
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

public class FacebookInstagramPaymentPage extends BaseTest {
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

    @Description("Add account Facebook and Instagram settings")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_AddAccountFacebookInstagramPayment() {
        homePageST = homePage.openAppSynctrack();
        settingsPageST = homePageST.openSettingsPage();
        settingsPageST.openFacebookInstagramPaymentTab();
        settingsPageST.addPaymentAccount(STConstants.DISPUTE_PAYPAL_CLIENT_ID,STConstants.DISPUTE_PAYPAL_SECRET_KEY);
    }

    @Description("Delete account Facebook and Instagram settings")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_DeleteAccountFacebookInstagramPayment() {
        settingsPageST.deletePaymentAccount();
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
}
