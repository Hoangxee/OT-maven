package pageObject.apps.ST;

import commons.BasePage;
import commons.constant.ST_DisputePageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.ST.DisputePageSTAppUI;
import pageUIs.apps.ST.HomePageSTAppUI;

public class DisputePageSTAppObject extends BasePage {
    private WebDriver driver;

    public DisputePageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click to Turn on button in Dispute management")
    public void turnOnDisputeManagement() {
        waitForPageUrlContains(driver, "dispute");

        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DisputePageSTAppUI.DISPUTE_PAGE_TITLE);
        Assert.assertEquals(getTextInElement(driver, DisputePageSTAppUI.DISPUTE_PAGE_TITLE), "Disputes");

        waitForElementVisible(driver, DisputePageSTAppUI.DISPUTE_MANAGEMENT_STATUS);
        if (getTextInElement(driver, DisputePageSTAppUI.DISPUTE_MANAGEMENT_STATUS).equals("Off")){
            waitForElementClickable(driver, DisputePageSTAppUI.TURN_ON_BTN_DISPUTE_MANAGEMENT);
            clickToElement(driver, DisputePageSTAppUI.TURN_ON_BTN_DISPUTE_MANAGEMENT);

            Assert.assertTrue(isListElementDisplayed(driver, DisputePageSTAppUI.MESSAGE_TOAST, ST_DisputePageConstants.UPDATE_SUCCESSFULLY_MESSAGE));
            Assert.assertEquals(getTextInElement(driver, DisputePageSTAppUI.DISPUTE_MANAGEMENT_STATUS), "On");
        }
    }

    @Step("Connect Paypal account in Dispute page")
    public void connectPaypalAccountInDisputePage(String email, String clientID, String secretKey) {
        waitForElementInvisible(driver, DisputePageSTAppUI.SKELETON_ELEMENT); // đợi element load in page

        waitForElementClickable(driver, DisputePageSTAppUI.CONNECT_PAYPAL_ACCOUNT_BTN);
        clickToElement(driver, DisputePageSTAppUI.CONNECT_PAYPAL_ACCOUNT_BTN);

        waitForElementVisible(driver, DisputePageSTAppUI.ADD_NEW_PAYPAL_ACCOUNT_POPUP_TITLE);

        Assert.assertEquals(getTextInElement(driver, DisputePageSTAppUI.ADD_NEW_PAYPAL_ACCOUNT_POPUP_TITLE), ST_DisputePageConstants.ADD_NEW_PAYPAL_ACCOUNT_POPUP_TITLE_IN_DISPUTE_PAGE);

        waitForElementVisible(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,"email");
        sendKeyToElement(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,email,"email");

        waitForElementVisible(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,"text");
        sendKeyToElement(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,clientID,"text");

        waitForElementVisible(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,"password");
        sendKeyToElement(driver, DisputePageSTAppUI.INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,secretKey,"password");

        waitForElementClickable(driver, DisputePageSTAppUI.BTN_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,"Save");
        clickToElement(driver, DisputePageSTAppUI.BTN_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP,"Save");

        Assert.assertTrue(isListElementDisplayed(driver, DisputePageSTAppUI.MESSAGE_TOAST, ST_DisputePageConstants.ACCOUNT_CONNECTED_MESSAGE));
    }

    @Step("Verify connected PayPal account")
    public void verifyPaypalAccountConnectedInDisputePage(String email) {
        waitForElementVisible(driver, DisputePageSTAppUI.EMAIL_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK);
        Assert.assertEquals(getTextInElement(driver, DisputePageSTAppUI.EMAIL_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK), email);

        Assert.assertEquals(getTextInElement(driver, DisputePageSTAppUI.STATUS_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK), "Confirmed");

    }

    @Step("Disconnect Paypal account")
    public void disconnectPaypalAccountInDisputePage() {
        moveToElement(driver,DisputePageSTAppUI.EMAIL_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK);

        waitForElementClickable(driver, DisputePageSTAppUI.DELETE_BTN_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK);
        clickToElement(driver, DisputePageSTAppUI.DELETE_BTN_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK);

        Assert.assertTrue(isListElementDisplayed(driver, DisputePageSTAppUI.MESSAGE_TOAST, ST_DisputePageConstants.ACCOUNT_REMOVED_MESSAGE));
    }

    @Step("Turn off Dispute management")
    public void turnOffDisputeManagement() {
        waitForElementClickable(driver, DisputePageSTAppUI.TURN_OFF_BTN_DISPUTE_MANAGEMENT);
        clickToElement(driver, DisputePageSTAppUI.TURN_OFF_BTN_DISPUTE_MANAGEMENT);

        Assert.assertTrue(isListElementDisplayed(driver, DisputePageSTAppUI.MESSAGE_TOAST, ST_DisputePageConstants.UPDATE_SUCCESSFULLY_MESSAGE));
    }
}
