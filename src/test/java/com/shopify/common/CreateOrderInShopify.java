package com.shopify.common;

import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.constant.OTConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.shopify.admin.*;
import utilities.Environment;

public class CreateOrderInShopify extends BaseTest {
    @Parameters({"browser","environment"})
    @Description("Create order in shopify")
    @BeforeTest
    public void createOrderShopify(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);
        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);

        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        orderPage = homePage.clickToOrdersTab();
        orderPage.clickToCreateOrderBtn();
        orderPage.chooseCustomer(GlobalConstants.SHOPIFY_ADMIN_EMAIL);
        orderPage.chooseProduct(OTConstants.SHOPIFY_PRODUCT_FOR_OT);
        orderPage.chooseProduct(OTConstants.SHOPIFY_PRODUCT_FOR_OT_2);
        orderPage.clickToCollectPaymentBtn();
        orderPage.chooseOptionPayment("Mark as paid");
        orderPage.clickToCreateOrderBtnInMarkAsPaidPopup();
        orderPage.clickToFulfillItemsBtn();
        orderPage.inputToTrackingNumber(OTConstants.TRACKING_NUMBER);
        orderName = orderPage.fulfillOrder();
        orderID = orderPage.getOrderID();
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    private WebDriver driver;
    LoginPageAdminObject loginPage;
    HomePageAdminObject homePage;
    OrderPageAdminObject orderPage;
    public static String orderName;
    public static String orderID;
}
