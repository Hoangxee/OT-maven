package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.GlobalConstants;
import commons.constant.OT_SettingsPageConstants;
import commons.constant.ST_SettingsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.OT.SettingsPageOTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

import java.time.Duration;

public class SettingsPageOTAppObject extends BasePage {
    private WebDriver driver;

    public SettingsPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Enable replacing Shopify’s native tracking link with the custom tracking page link")
    public void checkedToReplaceCourierLinkCheckbox() {
        toggleReplaceCourierLinkCheckbox("false");
    }

    @Step("Disable replacing Shopify’s native tracking link with the custom tracking page link")
    public void uncheckedToReplaceCourierLinkCheckbox() {
        toggleReplaceCourierLinkCheckbox("true");
    }
    private void toggleReplaceCourierLinkCheckbox(String expectedStatus) {
        waitForElementVisible(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_STATUS_IN_TRACKING_LINK_SETUP);
        String currentStatus = getAttribute(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_STATUS_IN_TRACKING_LINK_SETUP, "aria-checked");

        if (currentStatus.equals(expectedStatus)) {
            toggleCheckboxReplaceCourierLinkCheckbox();
        }
    }
    private void toggleCheckboxReplaceCourierLinkCheckbox(){
        waitForElementVisible(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_CHECKBOX_IN_TRACKING_LINK_SETUP);
        clickToElement(driver, SettingsPageOTAppUI.REPLACE_COURIER_LINK_CHECKBOX_IN_TRACKING_LINK_SETUP);

        waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
        clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));

    }

    @Step("Send key '{0}' to Link description")
    public void sendKeyToLinkDescriptionInput(String textInput) {
        waitForElementVisible(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP);

        getWebElement(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP).clear();

        int randomTime = getRandomTime();
        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,textInput+randomTime);
        waitForElementAttributeChange(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,"value",textInput+randomTime);

        waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
        clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));

    }

    @Step("Enable Add a tracking link to Order Status page")
    public void checkedToAddLinkToOrderCheckbox() {
        toggleAddLinkToOrderCheckbox("false");
    }

    @Step("Disable Add a tracking link to Order Status page")
    public void uncheckedToAddLinkToOrderCheckbox() {
        toggleAddLinkToOrderCheckbox("true");
    }
    private void toggleAddLinkToOrderCheckbox(String expectedStatus) {
        waitForElementVisible(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_STATUS_IN_TRACKING_LINK_SETUP);
        String currentStatus = getAttribute(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_STATUS_IN_TRACKING_LINK_SETUP, "aria-checked");

        if (currentStatus.equals(expectedStatus)) {
            toggleCheckboxAddLinkToOrderCheckbox();
        }
    }
    private void toggleCheckboxAddLinkToOrderCheckbox(){
        waitForElementVisible(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_CHECKBOX_IN_TRACKING_LINK_SETUP);
        clickToElement(driver, SettingsPageOTAppUI.ADD_LINK_TO_ORDER_CHECKBOX_IN_TRACKING_LINK_SETUP);

        waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
        clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);
        Assert.assertTrue(isDynamicElementDisplayed(driver, SettingsPageSTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE));
    }

}
