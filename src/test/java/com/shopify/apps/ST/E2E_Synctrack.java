package com.shopify.apps.ST;

import commons.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;


public class E2E_Synctrack extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);

    }

    @Description("Get started")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getStarted(){
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        homePageST = homePage.openAppSynctrack();
        homePageST.skipOnBoard();
        Assert.assertTrue(homePageST.isPlanBasic(STConstants.BASIC_PLAN_TEXT_IN_HOME,STConstants.BASIC_QUOTA));
        loginPagePaypal = homePageST.connectToPaypal();
        loginPagePaypal.loginToPaypal(STConstants.PAYPAL_EMAIL, STConstants.PAYPAL_PASSWORD);
        Assert.assertTrue(homePageST.hadConnectedPaypalAccount());

    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;

    HomePageSTAppObject homePageST;
    LoginPagePaypalObject loginPagePaypal;

}
