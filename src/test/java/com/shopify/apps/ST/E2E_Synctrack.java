package com.shopify.apps.ST;

import commons.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.apps.ST.OrdersPageSTAppObject;
import pageObject.apps.ST.SettingsPageSTAppObject;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;


public class E2E_Synctrack extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);
        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
    }

    @Description("End to end case")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void endToEnd() {
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        homePageST = homePage.openAppSynctrack();
        homePageST.skipOnBoard();
        Assert.assertTrue(homePageST.isPlanBasic(STConstants.BASIC_PLAN_TEXT_IN_HOME,
                STConstants.BASIC_QUOTA, STConstants.PAYPAL_ACCOUNT_NOT_CONNECT_LABEL));

        settingsPageST = homePageST.openSettingsPage();
        settingsPageST.openPaypalSettingsTab();
        loginPagePaypal = settingsPageST.connectToPaypal();
        loginPagePaypal.loginToPaypal(STConstants.PAYPAL_EMAIL, STConstants.PAYPAL_PASSWORD);
        settingsPageST.hadConnectedPaypalAccount(STConstants.PAYPAL_EMAIL, STConstants.PAYPAL_MERCHANT_ID);

        ordersPageST = settingsPageST.openOrdersPage();
        ordersPageST.processOldOrders();

    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    HomePageSTAppObject homePageST;
    LoginPagePaypalObject loginPagePaypal;
    OrdersPageSTAppObject ordersPageST;
    SettingsPageSTAppObject settingsPageST;
}
