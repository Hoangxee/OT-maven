package com.shopify.apps.ST;

import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import commons.constant.STConstants;
import commons.constant.ST_HomePageConstants;
import commons.constant.ST_SubscriptionPlansPageConstants;
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
import pageObject.apps.ST.SubscriptionPlansPageSTAppObject;
import pageObject.shopify.admin.ApproveSubscriptionAdminObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;

public class SubscriptionPlansPage extends BaseTest {
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

    @Description("Change plan")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void ChangePlan() {
        homePageST = homePage.openAppSynctrack();
        subscriptionPlansPageST = homePageST.openSubscriptionPlansPage();
        approveSubscriptionAmin = subscriptionPlansPageST.chooseMonthlyPlan(ST_SubscriptionPlansPageConstants.UNLIMITED_PLAN_TEXT);
        homePageST = approveSubscriptionAmin.approvePlan(SubscriptionPlansPageSTAppObject.planPriceMonthly);
        Assert.assertTrue(homePageST.isPlanActivated(ST_HomePageConstants.UNLIMITED_PLAN_TEXT_IN_HOME, ST_HomePageConstants.UNLIMITED_QUOTA));
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
    SubscriptionPlansPageSTAppObject subscriptionPlansPageST;
    ApproveSubscriptionAdminObject approveSubscriptionAmin;
}
