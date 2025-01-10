package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.GlobalConstants;
import commons.constant.OTConstants;
import commons.constant.OT_TrackingPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.OT.TrackingPageOTAppUI;
import pageUIs.storeFront.TrackingResultPageUI;

import java.util.List;

public class TrackingPageOTAppObject extends BasePage {
    private WebDriver driver;
    public static int random = getRandomTime();

    public TrackingPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Open Look and feel tab")
    public void openLookAndFeelTab() {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OT_TrackingPageConstants.LOOK_AND_FEEL_TAB_IN_TRACKING_PAGE);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OT_TrackingPageConstants.LOOK_AND_FEEL_TAB_IN_TRACKING_PAGE);
    }

    @Step("Open Tracking page in store front")
    public void openTrackingPageInStoreFront() {
        waitForElementClickable(driver, TrackingPageOTAppUI.PREVIEW_BTN);
        clickToElement(driver, TrackingPageOTAppUI.PREVIEW_BTN);

        switchToWindowByTitle(driver, OTConstants.STORE_FRONT_APP_NAME);

        List<WebElement> passwordInput = getListWebElement(driver, TrackingResultPageUI.PASSWORD_INPUT);
        if(!passwordInput.isEmpty()){
            waitForElementVisible(driver, TrackingResultPageUI.PASSWORD_INPUT);
            sendKeyToElement(driver, TrackingResultPageUI.PASSWORD_INPUT,GlobalConstants.SHOPIFY_STORE_FRONT_PASSWORD);
            waitForElementClickable(driver, TrackingResultPageUI.ENTER_BTN);
            clickToElement(driver, TrackingResultPageUI.ENTER_BTN);

            switchToWindowByTitleContains(driver,OTConstants.SHOPIFY_ADMIN_APP_NAME);
            closeAllWindowIgnoreCurrent(driver);
            switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

            openTrackingPageInStoreFront();
        }
    }

    @Step("Choose '{0}' theme")
    public void chooseTheme(String theme) {
        switchToWindowByTitleContains(driver,OTConstants.SHOPIFY_ADMIN_APP_NAME);
        closeAllWindowIgnoreCurrent(driver);
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        String themeOption;
        switch (theme.toUpperCase()) {
            case "BASIC":
                themeOption = "theme_1";
                break;
            case "ADVANCE":
                themeOption = "theme_2";
                break;
            default:
                System.out.println("Theme invalid");
                return;
        }
        selectThemeAndSave(themeOption);
    }
    private void selectThemeAndSave(String themeOption){
        waitForElementClickable(driver, TrackingPageOTAppUI.THEME_OPTION, themeOption);
        clickToElement(driver, TrackingPageOTAppUI.THEME_OPTION, themeOption);

        waitForElementClickable(driver, TrackingPageOTAppUI.SAVE_BTN);
        clickToElement(driver, TrackingPageOTAppUI.SAVE_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, OT_TrackingPageConstants.SAVE_SUCCESSFULLY_MESSAGE));
    }

    @Step("Verify Store front is Basic theme")
    public boolean isBasicTheme() {
        return isListElementDisplayed(driver, TrackingPageOTAppUI.ORDER_ID_INPUT_OF_BASIC_THEME);
    }

    @Step("Verify Store front is Advance theme")
    public boolean isAdvanceTheme() {
        return isListElementDisplayed(driver, TrackingPageOTAppUI.ORDER_ID_INPUT_OF_ADVANCE_THEME);
    }

    @Step("Upload image in Advance theme")
    public void uploadImageInAdvanceTheme(String imagePath, String fieldName) {
        switchToWindowByTitleContains(driver,OTConstants.SHOPIFY_ADMIN_APP_NAME);
        closeAllWindowIgnoreCurrent(driver);
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        List<WebElement> trackingForm = getListWebElement(driver, TrackingPageOTAppUI.TRACKING_FORM);
        if(!trackingForm.isEmpty()){
            waitForElementVisible(driver, TrackingPageOTAppUI.UPLOAD_IMAGE_BTN_IN_TRACKING_FORM, fieldName);
            sendKeyToElement(driver, TrackingPageOTAppUI.UPLOAD_IMAGE_BTN_IN_TRACKING_FORM, imagePath, fieldName);
        }
    }

    @Step("Send key '{0}' to Destination url in Advance theme")
    public void inputDestinationUrlInAdvanceTheme(String destinationUrl, String fieldName) {
        List<WebElement> trackingForm = getListWebElement(driver, TrackingPageOTAppUI.TRACKING_FORM);
        if(!trackingForm.isEmpty()){
            waitForElementVisible(driver, TrackingPageOTAppUI.INPUT_IN_TRACKING_FORM, fieldName);
            sendKeyToElementAfterClearText(driver, TrackingPageOTAppUI.INPUT_IN_TRACKING_FORM, destinationUrl, fieldName);
        }

    }

    @Step("Click to Publish button")
    public void clickToPublishBtn(String fieldName) {
        waitForElementAttributeChange(driver, TrackingPageOTAppUI.PUBLISH_BTN_IN_TRACKING_FORM, "aria-disabled", "false", fieldName);
        waitForElementClickable(driver, TrackingPageOTAppUI.PUBLISH_BTN_IN_TRACKING_FORM, fieldName);
        clickToElement(driver, TrackingPageOTAppUI.PUBLISH_BTN_IN_TRACKING_FORM, fieldName);

        Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, OT_TrackingPageConstants.UPDATE_LOOK_AND_FEEL_SUCCESSFULLY_MESSAGE));
    }

    public void uploadImageAndSendKeyToUrl(String fieldName, String imagePath, String destinationUrl){
        uploadImageInAdvanceTheme(imagePath, fieldName);
        inputDestinationUrlInAdvanceTheme(destinationUrl, fieldName);
        clickToPublishBtn(fieldName);
    }

    @Step("Open Tracking options tab")
    public void openTrackingOptionsTab() {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.TRACKING_OPTIONS_TAB_IN_TRACKING_PAGE);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.TRACKING_OPTIONS_TAB_IN_TRACKING_PAGE);
    }

    @Step("Choose Tracking method is '{0}'")
    public void chooseTrackingMethod(String methodName) {
        String upperMethodName = methodName.toUpperCase();
        String methodUpperCaseFirstWord = upperCaseFirstWord(methodName);

        if (upperMethodName.equals("TRACKING NUMBER") || upperMethodName.equals("EMAIL") || upperMethodName.equals("PHONE NUMBER")) {
            handleSimpleTrackingMethod(methodUpperCaseFirstWord);
        } else if (upperMethodName.equals("ORDER ID") || upperMethodName.equals("ORDER ID & TRACKING NUMBER")) {
            handleComplexTrackingMethod(methodUpperCaseFirstWord);
        } else {
            System.out.println("Invalid tracking method: " + methodUpperCaseFirstWord);
        }
    }
    private void handleSimpleTrackingMethod(String trackingMethodOption) {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);

        waitForElementClickable(driver, TrackingPageOTAppUI.SAVE_BTN);
        clickToElement(driver, TrackingPageOTAppUI.SAVE_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, OT_TrackingPageConstants.SAVE_SUCCESSFULLY_MESSAGE));
    }
    private void handleComplexTrackingMethod(String trackingMethodOption) {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);

        if (getAttribute(driver, TrackingPageOTAppUI.REQUIRE_EMAIL_CHECKBOX, "aria-invalid").equals("true")) {
            clickToElement(driver, TrackingPageOTAppUI.REQUIRE_EMAIL_CHECKBOX);
        }

        waitForElementClickable(driver, TrackingPageOTAppUI.SAVE_BTN);
        clickToElement(driver, TrackingPageOTAppUI.SAVE_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, OT_TrackingPageConstants.SAVE_SUCCESSFULLY_MESSAGE));
    }
}
