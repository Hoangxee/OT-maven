package pageObject.apps.ST;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.STConstants;
import commons.constant.ST_SettingsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

import java.util.List;

public class SettingsPageSTAppObject extends BasePage {
    private WebDriver driver;
    String connectionKeyOld;

    public SettingsPageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click to Orders tab")
    public OrdersPageSTAppObject openOrdersPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE, STConstants.ORDERS_PAGES_ST_APP_IN_NAVIGATION);
        clickToElement(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE,STConstants.ORDERS_PAGES_ST_APP_IN_NAVIGATION);

        return PageGeneratorManager.getOrdersPageSTAppObject(driver);
    }

    @Step("Click to Paypal settings tab in Settings page")
    public void openPaypalSettingsTab() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.PAYPAL_SETTINGS_IN_SETTINGS_PAGE);
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.PAYPAL_SETTINGS_IN_SETTINGS_PAGE);
    }

    @Step("Connect to Paypal")
    public LoginPagePaypalObject connectToPaypal() {
        waitForElementClickable(driver, SettingsPageSTAppUI.CONNECT_NOW_BTN);
        clickToElement(driver, SettingsPageSTAppUI.CONNECT_NOW_BTN);

        return PageGeneratorManager.getLoginPagePaypal(driver);
    }

    @Step("Verify Connected Paypal account")
    public boolean hadConnectedPaypalAccount(String email, String id) {
        switchToWindowByTitleContains(driver, ST_SettingsPageConstants.SYNCTRACK_ADMIN_TITLE_PAGE);
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        boolean verifyMessageConnectPapalSuccessful = isElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST,ST_SettingsPageConstants.CONNECT_PAYPAL_SUCCESSFULLY_MESSAGE);
        boolean accountLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,email);
        boolean idLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,id);

        return verifyMessageConnectPapalSuccessful&&accountLB&&idLB;
    }

    @Step("Click to Facebook & Instagram payment tab in Settings page")
    public void openFacebookInstagramPaymentTab() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.FACEBOOK_INSTAGRAM_PAYMENT_IN_SETTINGS_PAGE);
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.FACEBOOK_INSTAGRAM_PAYMENT_IN_SETTINGS_PAGE);
    }

    @Step("Add payment account")
    public void addPaymentAccount(String clientID, String secretKey) {
        waitForElementClickable(driver, SettingsPageSTAppUI.ADD_ACCOUNT_BTN_IN_FACEBOOK_INSTAGRAM_PAYMENT_TAB);
        clickToElement(driver, SettingsPageSTAppUI.ADD_ACCOUNT_BTN_IN_FACEBOOK_INSTAGRAM_PAYMENT_TAB);

        Assert.assertEquals(getTextInElement(driver, SettingsPageSTAppUI.ADD_PAYMENT_ACCOUNT_POPUP_TITLE_IN_FACEBOOK_INSTAGRAM_PAYMENT_TAB),
                ST_SettingsPageConstants.ADD_PAYMENT_ACCOUNT_POPUP_TITLE_IN_FACEBOOK_INSTAGRAM_PAYMENT_TAB);

        waitForElementVisible(driver, SettingsPageSTAppUI.INPUT_IN_ADD_PAYMENT_ACCOUNT_POPUP,"Client ID:");
        sendKeyToElement(driver, SettingsPageSTAppUI.INPUT_IN_ADD_PAYMENT_ACCOUNT_POPUP,clientID,"Client ID:");

        waitForElementVisible(driver, SettingsPageSTAppUI.INPUT_IN_ADD_PAYMENT_ACCOUNT_POPUP,"Secret key:");
        sendKeyToElement(driver, SettingsPageSTAppUI.INPUT_IN_ADD_PAYMENT_ACCOUNT_POPUP,secretKey,"Secret key:");

        waitForElementAttributeChange(driver, SettingsPageSTAppUI.BTN_IN_ADD_PAYMENT_ACCOUNT_POPUP, "aria-disabled", "false","Save");
        clickToElement(driver, SettingsPageSTAppUI.BTN_IN_ADD_PAYMENT_ACCOUNT_POPUP,"Save");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.ADD_PAYMENT_ACCOUNT_SUCCESSFULLY_MESSAGE));
        Assert.assertEquals(getTextInElement(driver, SettingsPageSTAppUI.ACCOUNT_STATUS_TEXT),"Active");
    }

    @Step("Verify payment account")
    public void deletePaymentAccount() {
        moveToElement(driver, SettingsPageSTAppUI.ACCOUNT_STATUS_TEXT);

        waitForElementClickable(driver, SettingsPageSTAppUI.REMOVE_PAYMENT_ACCOUNT_BTN);
        clickToElement(driver, SettingsPageSTAppUI.REMOVE_PAYMENT_ACCOUNT_BTN);

        waitForElementClickable(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);
        clickToElement(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.REMOVE_PAYMENT_ACCOUNT_SUCCESSFULLY_MESSAGE));
    }

    @Step("Click to Courier mapping tab in Settings page")
    public void openCourierMappingTab() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.COURIER_MAPPING_IN_SETTINGS_PAGE);
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.COURIER_MAPPING_IN_SETTINGS_PAGE);
    }

    @Step("Add Courier mapping rules")
    public void addCourierMappingRules(String mappingType, String courierName, String paypalCourier) {
        waitForElementClickable(driver, SettingsPageSTAppUI.ADD_MAPPING_RULES_BTN);
        clickToElement(driver, SettingsPageSTAppUI.ADD_MAPPING_RULES_BTN);

        Assert.assertEquals(getTextInElement(driver, SettingsPageSTAppUI.COURIER_MAPPING_POPUP_TITLE_IN_COURIER_MAPPING_TAB),
                ST_SettingsPageConstants.COURIER_MAPPING_POPUP_TITLE_IN_COURIER_MAPPING_TAB);

        selectItemInDropdown(driver, SettingsPageSTAppUI.MAPPING_TYPE_DROPDOWN_IN_COURIER_MAPPING_POPUP, mappingType);
        Assert.assertEquals(getSelectedItemInDropdown(driver, SettingsPageSTAppUI.MAPPING_TYPE_DROPDOWN_IN_COURIER_MAPPING_POPUP), mappingType);

        waitForElementVisible(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,"2");
        sendKeyToElement(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,paypalCourier,"2");
        waitForElementAttributeChange(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,"value", paypalCourier, "2");

        waitForElementVisible(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,"1");
        sendKeyToElement(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,courierName,"1");
        waitForElementAttributeChange(driver, SettingsPageSTAppUI.COURIER_INPUT_IN_COURIER_MAPPING_POPUP,"value", courierName, "1");

        waitForElementAttributeChange(driver, SettingsPageSTAppUI.BTN_IN_COURIER_MAPPING_POPUP, "aria-disabled", "false","Save");
        clickToElement(driver, SettingsPageSTAppUI.BTN_IN_COURIER_MAPPING_POPUP,"Save");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.ADD_COURIER_MAPPING_RULE_SUCCESSFULLY_MESSAGE));
    }

    @Step("Delete all Courier mapping rules")
    public void deleteAllCourierMappingRules() {
        List<WebElement> rules = getListWebElement(driver, SettingsPageSTAppUI.COURIER_MAPPING_RULES);
        while (!rules.isEmpty()) {
            moveToListElementsByIndex(driver, SettingsPageSTAppUI.COURIER_MAPPING_RULES, 0);
            clickToListElementByIndex(driver, SettingsPageSTAppUI.COURIER_MAPPING_RULE_DELETE_BTN, 0);

            waitForElementClickable(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);
            clickToElement(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);

            Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.REMOVE_COURIER_MAPPING_RULE_SUCCESSFULLY_MESSAGE));
            sleepInSecond(1);
            rules = getListWebElement(driver, SettingsPageSTAppUI.COURIER_MAPPING_RULES);
        }
    }

    @Step("Click to Ignore sync tab in Settings page")
    public void openIgnoreSyncTab() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.IGNORE_SYNC_IN_SETTINGS_PAGE);
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.IGNORE_SYNC_IN_SETTINGS_PAGE);
    }

    @Step("Add Ignore sync rules")
    public void addIgnoreSyncRules(String ignoreRuleType, String courierName) {
        waitForElementUnDisplay(driver, SettingsPageSTAppUI.SKELETON_ELEMENT); // đợi element load in page

        waitForElementClickable(driver, SettingsPageSTAppUI.ADD_IGNORE_RULES_BTN);
        clickToElement(driver, SettingsPageSTAppUI.ADD_IGNORE_RULES_BTN);

        Assert.assertEquals(getTextInElement(driver, SettingsPageSTAppUI.ADD_IGNORE_RULES_POPUP_TITLE), ST_SettingsPageConstants.ADD_IGNORE_RULES_POPUP_TITLE);

        selectItemInDropdown(driver, SettingsPageSTAppUI.IGNORE_RULE_TYPE_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP, ignoreRuleType);
        Assert.assertEquals(getSelectedItemInDropdown(driver, SettingsPageSTAppUI.IGNORE_RULE_TYPE_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP), ignoreRuleType);

        if(ignoreRuleType.equals(ST_SettingsPageConstants.COURIER_OPTION_IN_IGNORE_RULE_TYPE_DROPDOWN)){
            selectItemInCustomDropDown(driver,SettingsPageSTAppUI.COURIER_NAME_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP,SettingsPageSTAppUI.COURIER_NAME_DROPDOWN_OPTIONS_IN_ADD_IGNORE_RULES_POPUP,courierName);
            waitForElementAttributeChange(driver, SettingsPageSTAppUI.COURIER_NAME_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP,"value", courierName);
        } else {
            waitForElementVisible(driver, SettingsPageSTAppUI.COURIER_NAME_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP);
            sendKeyToElement(driver, SettingsPageSTAppUI.COURIER_NAME_DROPDOWN_IN_ADD_IGNORE_RULES_POPUP,courierName);
        }

        waitForElementAttributeChange(driver, SettingsPageSTAppUI.BTN_IN_ADD_IGNORE_RULES_POPUP, "aria-disabled", "false","Save");
        clickToElement(driver, SettingsPageSTAppUI.BTN_IN_ADD_IGNORE_RULES_POPUP,"Save");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.ADD_IGNORE_RULE_SUCCESSFULLY_MESSAGE));
    }

    @Step("Delete all Ignore Sync rules")
    public void deleteAllIgnoreSyncRules() {
        List<WebElement> rules = getListWebElement(driver, SettingsPageSTAppUI.IGNORE_SYNC_RULES);
        while (!rules.isEmpty()) {
            moveToListElementsByIndex(driver, SettingsPageSTAppUI.IGNORE_SYNC_RULES, 0);
            clickToListElementByIndex(driver, SettingsPageSTAppUI.IGNORE_SYNC_RULE_DELETE_BTN, 0);

            waitForElementClickable(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);
            clickToElement(driver, SettingsPageSTAppUI.DELETE_BTN_IN_CONFIRM_DELETE_POPUP);

            Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.DELETE_IGNORE_RULE_SUCCESSFULLY_MESSAGE));
            sleepInSecond(1);
            rules = getListWebElement(driver, SettingsPageSTAppUI.IGNORE_SYNC_RULES);
        }
    }

    @Step("Click to Multi stores tab in Settings page")
    public void openMultiStoresTab() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.MULTI_STORES_IN_SETTINGS_PAGE);
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,ST_SettingsPageConstants.MULTI_STORES_IN_SETTINGS_PAGE);
    }

    @Step("Click to Generate a connection key button")
    public String generateConnectionKey() {
        waitForElementClickable(driver, SettingsPageSTAppUI.GENERATE_CONNECTION_KEY_BTN);
        clickToElement(driver, SettingsPageSTAppUI.GENERATE_CONNECTION_KEY_BTN);

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.GENERATE_CONNECTION_KEY_SUCCESSFULLY_MESSAGE));

        waitForElementVisible(driver, SettingsPageSTAppUI.CONNECTION_KEY_TEXT);
        return getTextInElement(driver, SettingsPageSTAppUI.CONNECTION_KEY_TEXT);
    }

    @Step("Click to Generate a connection key button")
    public boolean isConnectionKeyExclusively(String connectionKey) {
        if(connectionKeyOld != null){
            if (!connectionKey.equals(connectionKeyOld)) {
                connectionKeyOld = connectionKey;
                return true;
            }
        } else{
            connectionKeyOld = connectionKey;
            return true;
        }
        return false;
    }

    @Step("Click to Copy Connection key button")
    public void copyConnectionKey() {
        waitForElementClickable(driver, SettingsPageSTAppUI.CONNECTION_KEY_BTN,"1");
        clickToElement(driver,  SettingsPageSTAppUI.CONNECTION_KEY_BTN,"1");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.COPY_CONNECTION_KEY_SUCCESSFULLY_MESSAGE));
    }

    @Step("Click to Change Connection key button")
    public void changeConnectionKey() {
        waitForElementClickable(driver, SettingsPageSTAppUI.CONNECTION_KEY_BTN,"2");
        clickToElement(driver,  SettingsPageSTAppUI.CONNECTION_KEY_BTN,"2");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.GENERATE_CONNECTION_KEY_SUCCESSFULLY_MESSAGE));

        waitForElementVisible(driver, SettingsPageSTAppUI.CONNECTION_KEY_TEXT);
        Assert.assertTrue(isConnectionKeyExclusively(getTextInElement(driver, SettingsPageSTAppUI.CONNECTION_KEY_TEXT)));
    }

    @Step("Click to Delete Connection key button")
    public void deleteConnectionKey() {
        waitForElementClickable(driver, SettingsPageSTAppUI.CONNECTION_KEY_BTN,"3");
        clickToElement(driver,  SettingsPageSTAppUI.CONNECTION_KEY_BTN,"3");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.REMOVE_CONNECTION_KEY_SUCCESSFULLY_MESSAGE));
        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.GENERATE_CONNECTION_KEY_BTN));
    }

    @Step("Click to Add connection button in Primary store")
    public HomePageSTAppObject clickToAddConnectionBtnInPrimaryStore() {
        waitForElementClickable(driver, SettingsPageSTAppUI.ADD_CONNECTION_BTN);
        clickToElement(driver, SettingsPageSTAppUI.ADD_CONNECTION_BTN);

        waitForElementVisible(driver, SettingsPageSTAppUI.INPUT_IN_ADD_CONNECTION_POPUP);
        sendKeyToElement(driver, SettingsPageSTAppUI.INPUT_IN_ADD_CONNECTION_POPUP, GlobalConstants.SHOPIFY_STORE_FRONT_2_URL);

        waitForElementVisible(driver, SettingsPageSTAppUI.SUBMIT_BTN_IN_ADD_CONNECTION_POPUP,"Add store");
        clickToElement(driver, SettingsPageSTAppUI.SUBMIT_BTN_IN_ADD_CONNECTION_POPUP,"Add store");

        switchToWindowByTitleContains(driver, "My Store");

        return PageGeneratorManager.getHomePageSTApp(driver);
    }

    @Step("Add existing key")
    public void addExistingKey(String connectionKey) {
        waitForElementClickable(driver, SettingsPageSTAppUI.ADD_EXISTING_KEY_BTN);
        clickToElement(driver, SettingsPageSTAppUI.ADD_EXISTING_KEY_BTN);

        waitForElementVisible(driver, SettingsPageSTAppUI.INPUT_IN_ADD_CONNECTION_POPUP);
        sendKeyToElement(driver, SettingsPageSTAppUI.INPUT_IN_ADD_CONNECTION_POPUP, connectionKey);

        waitForElementVisible(driver, SettingsPageSTAppUI.SUBMIT_BTN_IN_ADD_CONNECTION_POPUP,"Submit");
        clickToElement(driver, SettingsPageSTAppUI.SUBMIT_BTN_IN_ADD_CONNECTION_POPUP,"Submit");

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.INTEGRATE_STORE_SUCCESSFULLY_MESSAGE));

        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.INTEGRATED_STORES_LINK, GlobalConstants.SHOPIFY_STORE_FRONT_URL));
        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.INTEGRATED_STORES_LINK, GlobalConstants.SHOPIFY_STORE_FRONT_2_URL));
    }

    @Step("Verify integrated store in Primary store")
    public void verifyIntegratedStoresInPrimaryStore() {
        switchToWindowByTitleContains(driver, ST_SettingsPageConstants.SYNCTRACK_ADMIN_TITLE_PAGE);
        refreshCurrentPage(driver);

        openMultiStoresTab();

        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.INTEGRATED_STORES_LINK, GlobalConstants.SHOPIFY_STORE_FRONT_URL));
        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.INTEGRATED_STORES_LINK, GlobalConstants.SHOPIFY_STORE_FRONT_2_URL));
    }

    @Step("Disconnect Integrated stores in Primary store")
    public void disconnectIntegratedStores() {
        waitForElementClickable(driver, SettingsPageSTAppUI.DISCONNECT_INTEGRATED_STORES_BTN);
        clickToElement(driver, SettingsPageSTAppUI.DISCONNECT_INTEGRATED_STORES_BTN);

        waitForElementClickable(driver, SettingsPageSTAppUI.DISCONNECT_INTEGRATED_STORES_BTN_IN_POPUP_CONFIRM);
        clickToElement(driver, SettingsPageSTAppUI.DISCONNECT_INTEGRATED_STORES_BTN_IN_POPUP_CONFIRM);

        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, ST_SettingsPageConstants.DISCONNECT_INTEGRATE_STORE_SUCCESSFULLY_MESSAGE));
    }

    @Step("Verify disconnect Integrated stores in Integrated store")
    public void verifyDisconnectInIntegratedStore() {
        switchToWindowByTitleContains(driver, "My Store");
        refreshCurrentPage(driver);

        openMultiStoresTab();

        Assert.assertTrue(isElementDisplayed(driver, SettingsPageSTAppUI.ADD_EXISTING_KEY_BTN));
    }
}
