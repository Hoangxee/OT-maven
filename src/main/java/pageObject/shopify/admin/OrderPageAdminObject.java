package pageObject.shopify.admin;

import commons.BasePage;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.apps.OT.ShipmentPageOTAppObject;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.adminShopify.DetailOrderPageAdminUI;
import pageUIs.storeFront.TrackingResultPageUI;

public class OrderPageAdminObject extends BasePage {
    private WebDriver driver;

    public OrderPageAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click Create order button")
    public void clickToCreateOrderBtn(){
        waitForElementClickable(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
    }

    @Step("Choose product {0}")
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

    @Step("Choose customer {0}")
    public void chooseCustomer(String email){
        waitForProcessBar(driver, CreateOrderPageAdminUI.PROGRESS_BAR);
        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);

        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
    }

    @Step("Click to Collect Payment Button")
    public void clickToCollectPaymentBtn(){
        waitForProcessBar(driver, CreateOrderPageAdminUI.PROGRESS_BAR);
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
    }

    @Step("Choose option Payment {0}")
    public void chooseOptionPayment(String optionName){
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
    }

    @Step("Click to Create order in Mark as paid popup")
    public void clickToCreateOrderBtnInMarkAsPaidPopup(){
        waitForElementVisible(driver, CreateOrderPageAdminUI.TIMELINE_LABEL);
        waitForElementClickable(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN_IN_MARK_AS_PAID);
        clickToElement(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN_IN_MARK_AS_PAID);
    }

    @Step("Click to Fulfill items button")
    public void clickToFulfillItemsBtn() {
        waitForElementClickable(driver, CreateOrderPageAdminUI.FULFILL_ITEMS_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.FULFILL_ITEMS_BTN);
    }

    @Step("Send key {0} to Tracking number")
    public void inputToTrackingNumber(String trackingNumber) {
        waitForTextPresent(driver,CreateOrderPageAdminUI.FULFILL_POLARIS_TITLE,"Fulfill items");
        waitForElementVisible(driver, CreateOrderPageAdminUI.TRACKING_NUMBER_INPUT);
        sendKeyToElement(driver, CreateOrderPageAdminUI.TRACKING_NUMBER_INPUT, trackingNumber);
    }

    @Step("Fulfill order")
    public String fulfillOrder() {
        waitForElementVisible(driver, CreateOrderPageAdminUI.FULFILL_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.FULFILL_BTN);
        Assert.assertTrue(isElementDisplayed(driver, CreateOrderPageAdminUI.MESSAGE_LOG_TIMELINE));

        return getTextInElement(driver, CreateOrderPageAdminUI.ORDER_ID);
    }

    private void openAppStoreFront(String url, String password){
        openURL(driver,url);

        waitForElementVisible(driver, TrackingResultPageUI.PASSWORD_INPUT);
        sendKeyToElement(driver, TrackingResultPageUI.PASSWORD_INPUT,password);

        waitForElementClickable(driver, TrackingResultPageUI.ENTER_BTN);
        clickToElement(driver, TrackingResultPageUI.ENTER_BTN);
    }

    @Step("Verify order ")
    public ShipmentPageOTAppObject verifyOrderInShopifyAdmin(String orderName, String orderID, String email, String trackingNumber) {
        switchToWindowByTitleContains(driver, "Orders · "+ orderName);
        Assert.assertTrue(getPageURL(driver).contains(orderID));

        waitForElementVisible(driver, DetailOrderPageAdminUI.ORDER_NAME_TEXT);
        Assert.assertEquals(getTextInElement(driver, DetailOrderPageAdminUI.ORDER_NAME_TEXT),orderName);

        waitForElementVisible(driver, DetailOrderPageAdminUI.EMAIL_TEXT);
        Assert.assertEquals(getTextInElement(driver, DetailOrderPageAdminUI.EMAIL_TEXT),email);

        waitForElementVisible(driver, DetailOrderPageAdminUI.TRACKING_NUMBER_TEXT);
        Assert.assertEquals(getTextInElement(driver, DetailOrderPageAdminUI.TRACKING_NUMBER_TEXT),trackingNumber);

        openAppStoreFront(GlobalConstants.SHOPIFY_STORE_FRONT_URL,GlobalConstants.SHOPIFY_STORE_FRONT_PASSWORD);

        closeWindow(driver);

        return PageGeneratorManager.getShipmentPageOTApp(driver);
    }

    @Step("Get OrderID")
    public String getOrderID(){
        return getTextInPageURL(driver,getPageURL(driver));
    }
}
