package com.shopify.apps.OT;

import com.shopify.common.CreateOrderInShopify;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.OTConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.ShipmentPageOTAppObject;
import pageObject.apps.OT.SubscriptionPlansOTAppObject;
import pageObject.shopify.admin.*;
import pageObject.shopify.storeFront.TrackingResultPageObject;

import java.io.IOException;

public class E2E_OrderTracking extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);

    }

    @Description("Get old order")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getOldOrder(){
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
        Assert.assertTrue(dashboardOT.isPlanStarter(OTConstants.STARTER_SUBSCRIPTION_PLAN_NAME,OTConstants.STARTER_QUOTA));
        dashboardOT.getOldOrder();
        shipmentOT = dashboardOT.openShipmentPage();
        Assert.assertTrue(shipmentOT.hadOldOrderBeenGetToShipment(CreateOrderInShopify.orderName,CreateOrderInShopify.orderID,OTConstants.TRACKING_NUMBER));
        orderPage = shipmentOT.clickToOrderID(CreateOrderInShopify.orderName);
        shipmentOT = orderPage.verifyOrderInShopifyAdmin(CreateOrderInShopify.orderName,CreateOrderInShopify.orderID,GlobalConstants.SHOPIFY_ADMIN_EMAIL,OTConstants.TRACKING_NUMBER);
        trackingResultInStoreFront = shipmentOT.clickToTrackingNo(CreateOrderInShopify.orderName);
        Assert.assertTrue(trackingResultInStoreFront.isTrackingResultCorrect(CreateOrderInShopify.orderName,OTConstants.TRACKING_NUMBER));

        plans = trackingResultInStoreFront.openSubscriptionPlans();
        approvePlan = plans.choosePlan(OTConstants.PROFESSIONAL_SUBSCRIPTION_PLAN_NAME);
        approvePlan.approvePlan();
        Assert.assertTrue(approvePlan.isPlanProfessional(OTConstants.PROFESSIONAL_SUBSCRIPTION_PLAN_NAME,OTConstants.PROFESSIONAL_QUOTA));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    OrderPageAdminObject orderPage;
    TrackingResultPageObject trackingResultInStoreFront;
    ShipmentPageOTAppObject shipmentOT;
    SubscriptionPlansOTAppObject plans;
    ApproveSubscriptionAdminObject approvePlan;
}
