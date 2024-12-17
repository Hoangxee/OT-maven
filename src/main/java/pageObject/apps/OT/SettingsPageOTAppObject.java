package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.OT_SettingsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.apps.OT.SettingsPageOTAppUI;

import java.util.List;

public class SettingsPageOTAppObject extends BasePage {
    private WebDriver driver;
    public static int random = getRandomTime();

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

        saveAndVerifyMessage(OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE);

    }

    @Step("Send key '{0}' to Link description")
    public void sendKeyToLinkDescriptionInput(String textInput) {
        waitForElementVisible(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP);

        getWebElement(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP).clear();

        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,textInput);
        waitForElementAttributeChange(driver, SettingsPageOTAppUI.LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP,"value",textInput);

        saveAndVerifyMessage(OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE);

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

        saveAndVerifyMessage(OT_SettingsPageConstants.TRACKING_LINK_SETUP_SUCCESSFULLY_MESSAGE);
    }

    @Step("Choose courier '{0}' in Frequently Used Couriers")
    public void chooseCourierInFrequentlyUsedCouriers(String courier) {
        waitForElementVisible(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, OT_SettingsPageConstants.COURIER_LIST_LABEL);
        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, courier, OT_SettingsPageConstants.COURIER_LIST_LABEL);

        waitForElementClickable(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS,courier);
        clickToElement(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS,courier);

        saveAndVerifyMessage(OT_SettingsPageConstants.FREQUENTLY_USED_COURIERS_SUCCESSFULLY_MESSAGE);
        waitForElementUnDisplay(driver, SettingsPageOTAppUI.MESSAGE_TOAST, OT_SettingsPageConstants.FREQUENTLY_USED_COURIERS_SUCCESSFULLY_MESSAGE);

        Assert.assertEquals(getTextInElement(driver, SettingsPageOTAppUI.COURIER_CHOSE_IN_FREQUENTLY_USED_COURIERS,courier), courier);
    }

    @Step("Choose multiple couriers in Frequently Used Couriers: {0}")
    public void chooseCourierInFrequentlyUsedCouriers(List<String> couriers) {
        for (String courier : couriers) {
            chooseCourierInFrequentlyUsedCouriers(courier);
        }
    }

    @Step("Delete all courier in Frequently Used Couriers")
    public void deleteAllCourierInFrequentlyUsedCouriers() {
        List<WebElement> deleteCouriersBtn = getListWebElement(driver, SettingsPageOTAppUI.DELETE_BTN_IN_FREQUENTLY_USED_COURIERS);

        if (!deleteCouriersBtn.isEmpty()){
            for (int i = 1; i <= deleteCouriersBtn.size(); i++){
                waitForElementClickable(driver, SettingsPageOTAppUI.DELETE_BTN_IN_FREQUENTLY_USED_COURIERS);
                clickToElement(driver, SettingsPageOTAppUI.DELETE_BTN_IN_FREQUENTLY_USED_COURIERS);
            }
            saveAndVerifyMessage(OT_SettingsPageConstants.FREQUENTLY_USED_COURIERS_SUCCESSFULLY_MESSAGE);
        }
    }

    public void saveAndVerifyMessage(String message){
        waitForElementAttributeChange(driver, SettingsPageOTAppUI.SAVE_BTN, "aria-disabled", "false");
        waitForElementClickable(driver, SettingsPageOTAppUI.SAVE_BTN);
        clickToElement(driver, SettingsPageOTAppUI.SAVE_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, SettingsPageOTAppUI.MESSAGE_TOAST, message));
    }

    @Step("Add Courier mapping with {0} and {1}")
    private void addCourierMapping(String actualCourier, String shopifyCourier) {
        List<WebElement> listCourierMapping = getListWebElement(driver, SettingsPageOTAppUI.LIST_COURIER_MAPPING);
        waitForElementVisible(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, OT_SettingsPageConstants.ACTUAL_COURIER_LABEL);
        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, actualCourier, OT_SettingsPageConstants.ACTUAL_COURIER_LABEL);
        waitForElementClickable(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS, actualCourier);
        clickToElement(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS, actualCourier);

        waitForElementVisible(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, OT_SettingsPageConstants.SHOPIFY_COURIER_LABEL);
        sendKeyToElementAfterClearText(driver, SettingsPageOTAppUI.COURIER_LIST_DROPDOWN, shopifyCourier, OT_SettingsPageConstants.SHOPIFY_COURIER_LABEL);
        waitForElementClickable(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS, shopifyCourier);
        clickToElement(driver, SettingsPageOTAppUI.COURIER_LIST_OPTIONS, shopifyCourier);

        waitForElementAttributeChange(driver, SettingsPageOTAppUI.ADD_BTN_IN_COURIER_MAPPING, "aria-disabled", "false");
        clickToElement(driver, SettingsPageOTAppUI.ADD_BTN_IN_COURIER_MAPPING);

        waitForListElementSizeChange(driver,SettingsPageOTAppUI.LIST_COURIER_MAPPING,listCourierMapping.size());
    }

    @Step("Add Courier mapping with Actual couriers: {0} and Shopify couriers: {1}")
    public void addCourierMapping(List<String> actualCouriers, List<String> shopifyCouriers) {
        if (shopifyCouriers.size() != actualCouriers.size()) {
            throw new IllegalArgumentException("The Shopify Courier and Actual Courier lists must be the same length.");
        }

        for (int i = 0; i < shopifyCouriers.size(); i++) {
            String actualCourier = actualCouriers.get(i);
            String shopifyCourier = shopifyCouriers.get(i);

            addCourierMapping(actualCourier, shopifyCourier);
        }
    }

    @Step("Delete all Courier mapping")
    public void deleteAllCourierMapping() {
        List<WebElement> deleteCouriersBtn = getListWebElement(driver, SettingsPageOTAppUI.CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING, "1");

        if (deleteCouriersBtn.isEmpty()) {
            return;
        }
        while (!deleteCouriersBtn.isEmpty()){
            waitForElementClickable(driver, SettingsPageOTAppUI.CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING, "1");
            clickToElement(driver, SettingsPageOTAppUI.CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING, "1");

            waitForElementClickable(driver, SettingsPageOTAppUI.CONFIRM_BTN_IN_DELETE_COURIER_MAPPING_POPUP, "Delete");
            clickToElement(driver, SettingsPageOTAppUI.CONFIRM_BTN_IN_DELETE_COURIER_MAPPING_POPUP, "Delete");

            waitForListElementSizeChange(driver, SettingsPageOTAppUI.CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING, deleteCouriersBtn.size(), "1");

            deleteCouriersBtn = getListWebElement(driver, SettingsPageOTAppUI.CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING, "1");
        }
    }
}


