package pageObject.apps.ST;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

public class SettingsPageSTAppObject extends BasePage {
    private WebDriver driver;

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

        waitForElementClickable(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,"PayPal settings");
        clickToElement(driver, SettingsPageSTAppUI.TABS_IN_SETTINGS_PAGE,"PayPal settings");
    }

    @Step("Connect to Paypal")
    public LoginPagePaypalObject connectToPaypal() {
        waitForElementClickable(driver, SettingsPageSTAppUI.CONNECT_NOW_BTN);
        clickToElement(driver, SettingsPageSTAppUI.CONNECT_NOW_BTN);

        return PageGeneratorManager.getLoginPagePaypal(driver);
    }

    @Step("Verify Connected Paypal account")
    public boolean hadConnectedPaypalAccount(String email, String id) {
        switchToWindowByTitleContains(driver, STConstants.SYNCTRACK_ADMIN_TITLE_PAGE);
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        boolean verifyMessageConnectPapalSuccessful = isElementDisplayed(driver, SettingsPageSTAppUI.CONNECT_PAYPAL_ACCOUNT_SUCCESSFUL_MESSAGE,STConstants.CONNECT_PAYPAL_SUCCESSFULLY_MESSAGE);
        boolean accountLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,email);
        boolean idLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,id);

        return verifyMessageConnectPapalSuccessful&&accountLB&&idLB;
    }
}
