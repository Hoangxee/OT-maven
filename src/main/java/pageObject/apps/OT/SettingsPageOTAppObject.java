package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.OT_SettingsPageConstants;
import commons.constant.ST_SettingsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.OT.SettingsPageOTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

public class SettingsPageOTAppObject extends BasePage {
    private WebDriver driver;

    public SettingsPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Enable Replace Shopify’s native tracking link with your tracking page link")
    public void checkedToReplaceCourierLinkCheckbox() {
        waitForElementVisible(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_STATUS_IN_TRACKING_LINK_SETUP);
        if(getAttribute(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_STATUS_IN_TRACKING_LINK_SETUP, "aria-checked").equals("false")) {
            waitForElementVisible(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_CHECKBOX_IN_TRACKING_LINK_SETUP);
            clickToElement(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_CHECKBOX_IN_TRACKING_LINK_SETUP);

            waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
            clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
            Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));
        }
    }

    @Step("Enable Add a tracking link to Order Status page")
    public void sendKeyToLinkDescriptionInput(String textInput) {
        waitForElementVisible(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP);
        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,textInput);
        waitForElementAttributeChange(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,"value",textInput);

        waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
        clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));

    }

    @Step("Enable Add a tracking link to Order Status page")
    public void checkedToAddLinkToOrderCheckbox() {
        waitForElementVisible(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_STATUS_IN_TRACKING_LINK_SETUP);
        if(getAttribute(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_STATUS_IN_TRACKING_LINK_SETUP, "aria-checked").equals("false")) {
            waitForElementVisible(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_CHECKBOX_IN_TRACKING_LINK_SETUP);
            clickToElement(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_CHECKBOX_IN_TRACKING_LINK_SETUP);

            waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
            clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
            Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));
        }
    }

}
