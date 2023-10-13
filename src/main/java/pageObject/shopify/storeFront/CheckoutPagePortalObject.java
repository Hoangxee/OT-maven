package pageObject.shopify.storeFront;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageUIs.storeFront.CheckoutPagePortalUI;

public class CheckoutPagePortalObject extends BasePage {
    private WebDriver driver;
    Actions action;

    public CheckoutPagePortalObject(org.openqa.selenium.WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Enter to Contact textbox with value is {0}")
    public void inputToContactTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.CONTACT_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.CONTACT_INPUT, value);
    }

    @Step("Enter to FirstName textbox with value is {0}")
    public void inputToFirstNameTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.FIRST_NAME_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.FIRST_NAME_INPUT, value);
    }

    @Step("Enter to LastName textbox with value is {0}")
    public void inputToLastNameTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.LAST_NAME_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.LAST_NAME_INPUT, value);
    }

    @Step("Enter to Address textbox with value is {0}")
    public void inputToAddressTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.ADDRESS_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.ADDRESS_INPUT, value);
    }

    @Step("Enter to City textbox with value is {0}")
    public void inputToCityTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.CITY_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.CITY_INPUT, value);
    }

    @Step("Enter to Postal textbox with value is {0}")
    public void inputToPostalTextbox(String value) {
        waitForElementVisible(driver, CheckoutPagePortalUI.POSTAL_CODE_INPUT);
        sendKeyToElement(driver, CheckoutPagePortalUI.POSTAL_CODE_INPUT, value);
    }

    @Step("Click to Continue to shipping button and next step")
    public void clickToContinueToShippingButton() {
        waitForElementClickable(driver, CheckoutPagePortalUI.CONTINUE_TO_SHIPPING_BUTTON);
        clickToElement(driver, CheckoutPagePortalUI.CONTINUE_TO_SHIPPING_BUTTON);
    }

    @Step("Open to Credit card block")
    public void clickToContinueToPaymentButton() {
        waitForElementClickable(driver, CheckoutPagePortalUI.CONTINUE_TO_PAYMENT_BUTTON);
        clickToElement(driver, CheckoutPagePortalUI.CONTINUE_TO_PAYMENT_BUTTON);
    }

    @Step("Enter to {0} textbox with value {1}")
    public void inputToCreditCardField(String fieldName, String value) {
        switchToFrameIframe(driver, CheckoutPagePortalUI.CREDIT_CARD_IFRAME,fieldName);
        waitForElementVisible(driver, CheckoutPagePortalUI.CREDIT_CARD_INPUT, fieldName);
        sendKeyToElement(driver, CheckoutPagePortalUI.CREDIT_CARD_INPUT, value,fieldName);
        switchToDefaultContent(driver);
    }

    @Step("Enter to {0} textbox with value {1}")
    public void inputToExpirationDateField(String fieldName,Keys value) {
        action = new Actions(driver);
        switchToFrameIframe(driver, CheckoutPagePortalUI.CREDIT_CARD_IFRAME,fieldName);
        waitForElementVisible(driver, CheckoutPagePortalUI.EXPIRATION_DATE_IN_CREDIT_CARD);
        sendKeyBoardToElement(driver, CheckoutPagePortalUI.EXPIRATION_DATE_IN_CREDIT_CARD, value);
        switchToDefaultContent(driver);
    }

    @Step("Click to Pay Now button")
    public void clickToPayNowButton() {
        waitForElementClickable(driver, CheckoutPagePortalUI.PAY_NOW_BUTTON);
        clickToElement(driver, CheckoutPagePortalUI.PAY_NOW_BUTTON);
    }

    @Step("Verify Checkout success message")
    public String getCheckoutPageText() {
        waitForElementVisible(driver, CheckoutPagePortalUI.THANK_YOU_TEXT);
        return getTextInElement(driver, CheckoutPagePortalUI.THANK_YOU_TEXT);
    }

    @Step("Click to Continue Shopping button and open Homepage")
    public HomePagePortalObject clickToContinueShoppingButton() {
        waitForElementClickable(driver, CheckoutPagePortalUI.CONTINUE_SHOPPING_BUTTON);
        clickToElement(driver, CheckoutPagePortalUI.CONTINUE_SHOPPING_BUTTON);
        return PageGeneratorManager.getHomePageStoreFront(driver);
    }
}
