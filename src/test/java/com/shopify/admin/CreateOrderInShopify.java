package com.shopify.admin;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.admin.*;

public class CreateOrderInShopify extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
    }

    @Description("Create order in shopify")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void createOrderShopify(){
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        orderPage = homePage.clickToOrdersTab();
        orderPage.clickToCreateOrderBtn();
        orderPage.chooseCustomer(GlobalConstants.SHOPIFY_ADMIN_EMAIL);
        orderPage.chooseProduct(GlobalConstants.SHOPIFY_PRODUCT_FOR_OT);
        orderPage.chooseProduct(GlobalConstants.SHOPIFY_PRODUCT_FOR_OT_2);
        orderPage.clickToCollectPaymentBtn();
        orderPage.chooseOptionPayment("Mark as paid");
        orderPage.clickToCreateOrderBtnInMarkAsPaidPopup();
        orderPage.clickToFulfillItemsBtn();
        orderPage.inputToTrackingNumber(GlobalConstants.TRACKING_NUMBER);
        orderPage.fulfillOrder();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    LoginPageAdminObject loginPage;
    HomePageAdminObject homePage;
    OrderPageAdminObject orderPage;
    HomePageAppStoreObject homePageAppStore;
    SearchResultPageAppStoreObject SearchResultPageAppStore;
    DetailAppPageAppStoreObject detailAppPageAppStore;
    DashboardPageOTAppObject dashboardPageOTApp;
}
