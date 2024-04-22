package pageObject.shopify.admin;

import commons.BasePage;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageUIs.adminShopify.CreateOrderPageAdminUI;

import java.time.Duration;
import java.util.List;

public class OrderPageAdminObject extends BasePage {
    private WebDriver driver;

    public OrderPageAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Description("Click Create order button")
    public void clickToCreateOrderBtn(){
        waitForElementClickable(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
    }

    @Description("Choose product {0}")
    public void chooseProduct(String productName){
        waitForElementClickable(driver, CreateOrderPageAdminUI.BROWSE_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.BROWSE_BTN);

        waitForElementVisible(driver, CreateOrderPageAdminUI.SEARCH_PRODUCT_INPUT_IN_SELECT_PRODUCTS_POPUP);
        sendKeyToElement(driver, CreateOrderPageAdminUI.SEARCH_PRODUCT_INPUT_IN_SELECT_PRODUCTS_POPUP,productName);

        waitForElementVisible(driver, CreateOrderPageAdminUI.PRODUCT_IN_SEARCH_PRODUCT_POPUP,productName);
        clickToElement(driver, CreateOrderPageAdminUI.PRODUCT_IN_SEARCH_PRODUCT_POPUP,productName);

        waitForElementClickable(driver, CreateOrderPageAdminUI.ADD_PRODUCT_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.ADD_PRODUCT_BTN);
    }

    @Description("Choose customer {0}")
    public void chooseCustomer(String email){
        waitForProcessBar();
        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);

        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
    }

    @Description("Click to Collect Payment Button")
    public void clickToCollectPaymentBtn(){
        waitForProcessBar();
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
    }

    @Description("Choose option Payment {0}")
    public void chooseOptionPayment(String optionName){
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
    }

    @Description("Click to Create order in Mark as paid popup")
    public void clickToCreateOrderBtnInMarkAsPaidPopup(){
        waitForElementVisible(driver, CreateOrderPageAdminUI.TIMELINE_LABEL);
        waitForElementClickable(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN_IN_MARK_AS_PAID);
        clickToElement(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN_IN_MARK_AS_PAID);
    }

    public void clickToFulfillItemsBtn() {
        waitForElementClickable(driver, CreateOrderPageAdminUI.FULFILL_ITEMS_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.FULFILL_ITEMS_BTN);
    }

    public void inputToTrackingNumber(String trackingNumber) {
        waitForTextPresent(driver,CreateOrderPageAdminUI.FULFILL_POLARIS_TITLE,"Fulfill items");
        waitForElementVisible(driver, CreateOrderPageAdminUI.TRACKING_NUMBER_INPUT);
        sendKeyToElement(driver, CreateOrderPageAdminUI.TRACKING_NUMBER_INPUT, trackingNumber);
    }

    public void fulfillOrder() {
        waitForElementVisible(driver, CreateOrderPageAdminUI.FULFILL_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.FULFILL_BTN);
        Assert.assertEquals(getWebElement(driver, CreateOrderPageAdminUI.MESSAGE_FULFILL_SUCCESS).getText(), "Items fulfilled");
    }


    public void waitForProcessBar(){
        List<WebElement> progressBarShopify = driver.findElements(By.xpath("//div[@class='Polaris-Frame-Loading']"));
        if(progressBarShopify.size() != 0){
            waitForElementInvisible(driver,"xpath=//div[@class='Polaris-Frame-Loading']");
        }
    }
}
