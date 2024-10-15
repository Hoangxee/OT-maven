package com.shopify.apps.ST;

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
import pageObject.apps.ST.DisputePageSTAppObject;
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;

public class DisputePage extends BaseTest {
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

    @Description("Connect dispute management")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_ConnectDisputeManagement() {
        homePageST = homePage.openAppSynctrack();
        disputePageST = homePageST.openDisputePage();
        disputePageST.turnOnDisputeManagement();
        disputePageST.connectPaypalAccountInDisputePage(STConstants.PAYPAL_EMAIL,
                STConstants.DISPUTE_PAYPAL_CLIENT_ID,STConstants.DISPUTE_PAYPAL_SECRET_KEY);
        disputePageST.verifyPaypalAccountConnectedInDisputePage(STConstants.PAYPAL_EMAIL);
    }

    @Description("Disconnect dispute management")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_DisconnectDisputeManagement() {
        disputePageST.disconnectPaypalAccountInDisputePage();
        disputePageST.turnOffDisputeManagement();
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
    DisputePageSTAppObject disputePageST;
}
