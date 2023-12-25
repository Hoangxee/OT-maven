package com.shopify.store;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.storeFront.CheckoutPagePortalObject;
import pageObject.shopify.storeFront.HomePagePortalObject;
import pageObject.shopify.storeFront.LoginPagePortalObject;

public class CreateOrders extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_STORE_FRONT_URL);

        loginPage = PageGeneratorManager.getLoginPageStoreFront(driver);
    }

    @Description("Create order")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_CreateOrder(){
        homePage = loginPage.loginToShopifyStore(GlobalConstants.SHOPIFY_STORE_FRONT_PASSWORD);
        homePage.clickToSearchIcon();
        homePage.inputToSearchbar(GlobalConstants.SHOPIFY_PRODUCT);
        homePage.openToProductDetail();

//        checkoutPage = homePage.clickToBuyItNowButton();
//        checkoutPage.inputToContactTextbox(GlobalConstants.CONTACT);
//        checkoutPage.inputToFirstNameTextbox(GlobalConstants.FIRST_NAME);
//        checkoutPage.inputToLastNameTextbox(GlobalConstants.LAST_NAME);
//        checkoutPage.inputToAddressTextbox(GlobalConstants.ADDRESS);
//        checkoutPage.inputToCityTextbox(GlobalConstants.CITY);
//        checkoutPage.inputToPostalTextbox(GlobalConstants.POSTAL_CODE);
////        checkoutPage.clickToContinueToShippingButton();
////        checkoutPage.clickToContinueToPaymentButton();
//        checkoutPage.inputToCreditCardField("Card number", GlobalConstants.CARD_NUMBER);
////        checkoutPage.inputToCreditCardField("Name on card", GlobalConstants.NAME_ON_CARD);
//        checkoutPage.inputToExpirationDateField("Expiration date", Keys.NUMPAD1);
//        checkoutPage.inputToExpirationDateField("Expiration date", Keys.NUMPAD1);
//        checkoutPage.inputToExpirationDateField("Expiration date", Keys.NUMPAD3);
//        checkoutPage.sleepInSecond(1);
//        checkoutPage.inputToExpirationDateField("Expiration date", Keys.NUMPAD4);
//        checkoutPage.inputToCreditCardField("Security code", GlobalConstants.SECURITY_CODE);
//        checkoutPage.clickToPayNowButton();
//        Assert.assertTrue(checkoutPage.getCheckoutPageText().contains("Thank you"));

        homePage = checkoutPage.clickToContinueShoppingButton();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    HomePagePortalObject homePage;
    CheckoutPagePortalObject checkoutPage;
    LoginPagePortalObject loginPage;
}
