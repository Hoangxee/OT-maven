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
import pageUIs.apps.ST.DisputePageSTAppUI;
import pageUIs.storeFront.TrackingResultPageUI;

import java.util.Arrays;
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

        clickToSaveBtn(OT_TrackingPageConstants.SAVE_SUCCESSFULLY_MESSAGE);
    }

    @Step("Click to Save button")
    public void clickToSaveBtn(String message){
        List<WebElement> saveBtn = getListWebElement(driver, TrackingPageOTAppUI.SAVE_BTN);
        if(!saveBtn.isEmpty()){
            waitForElementClickable(driver, TrackingPageOTAppUI.SAVE_BTN);
            clickToElement(driver, TrackingPageOTAppUI.SAVE_BTN);

            Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, message));
        }
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

    @Step("Open Language tab")
    public void openLanguagesTab() {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.LANGUAGES_TAB_IN_TRACKING_PAGE);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.LANGUAGES_TAB_IN_TRACKING_PAGE);
    }

    @Step("Open Estimated Delivery Date tab")
    public void openEstimatedDeliveryDateTab() {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.ESTIMATED_DELIVERY_DATE_TAB_IN_TRACKING_PAGE);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_PAGE_TAB, OTConstants.ESTIMATED_DELIVERY_DATE_TAB_IN_TRACKING_PAGE);
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
    }
    private void handleComplexTrackingMethod(String trackingMethodOption) {
        waitForElementClickable(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);
        clickToElement(driver, TrackingPageOTAppUI.TRACKING_METHOD_OPTION_CHECKBOX, trackingMethodOption);

        if (getAttribute(driver, TrackingPageOTAppUI.REQUIRE_EMAIL_CHECKBOX, "aria-invalid").equals("true")) {
            clickToElement(driver, TrackingPageOTAppUI.REQUIRE_EMAIL_CHECKBOX);
        }
    }

    @Step("Choose Shipping info is '{0}'")
    public void chooseShippingInfo(String shippingInfoName) {
        if(shippingInfoName.equals("Progress bar")||
                shippingInfoName.equals("Tracking logs")||
                shippingInfoName.equals("Tracking Company")||
                shippingInfoName.equals("Order Detail")||
                shippingInfoName.equals("Map")){
            if(getAttribute(driver, TrackingPageOTAppUI.SHIPPING_INFO_CHECKBOX_STATUS, "aria-checked", shippingInfoName).equals("false")){
                waitForElementClickable(driver, TrackingPageOTAppUI.SHIPPING_INFO_CHECKBOX, shippingInfoName);
                clickToElement(driver, TrackingPageOTAppUI.SHIPPING_INFO_CHECKBOX, shippingInfoName);
            } else
                System.out.println(shippingInfoName + " is checked");
        } else
            System.out.println(shippingInfoName + " is invalid");
    }

    @Step("Choose Shipping info is '{0}'")
    public void chooseShippingInfo(String... shippingInfoNames) {
        List<String> validShippingInfo = Arrays.asList("Progress bar", "Tracking logs", "Tracking Company", "Order Detail", "Map");

        for (String shippingInfoName : shippingInfoNames) {
            if (validShippingInfo.contains(shippingInfoName)) {
                if (getAttribute(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX_STATUS, "aria-checked", shippingInfoName).equals("false")) {
                    waitForElementClickable(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, shippingInfoName);
                    clickToElement(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, shippingInfoName);
                } else {
                    System.out.println(shippingInfoName + " is already checked");
                }
            } else {
                System.out.println(shippingInfoName + " is invalid");
            }
        }
    }

    @Step("Select Date and time format are '{0}' and '{1}'")
    public void selectDateTimeFormat(String dateFormat, String timeFormat) {
        selectItemInDropdown(driver, TrackingPageOTAppUI.DATE_TIME_DROPDOWN, dateFormat, "MM");
        Assert.assertEquals(getSelectedItemInDropdown(driver, TrackingPageOTAppUI.DATE_TIME_DROPDOWN, "MM"), dateFormat);

        selectItemInDropdown(driver, TrackingPageOTAppUI.DATE_TIME_DROPDOWN, timeFormat, "mm");
        Assert.assertEquals(getSelectedItemInDropdown(driver, TrackingPageOTAppUI.DATE_TIME_DROPDOWN, "mm"), timeFormat);

    }

    @Step("Choose Progress bar is '{0}'")
    public void selectProgressBar(String progressBar) {
        if(progressBar.equals("Ordered")||
                progressBar.equals("In transit")||
                progressBar.equals("Delivered")||
                progressBar.equals("Order Ready")||
                progressBar.equals("Out for delivery")){
            if(getAttribute(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX_STATUS, "aria-checked", progressBar).equals("false")){
                waitForElementClickable(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, progressBar);
                clickToElement(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, progressBar);
            } else
                System.out.println(progressBar + " is checked");
        } else
            System.out.println(progressBar + " is invalid");
    }

    @Step("Choose Progress bar is '{0}'")
    public void selectProgressBar(String... progressBars) {
        List<String> validProgressBars = Arrays.asList("Ordered", "In transit", "Delivered", "Order Ready", "Out for delivery");

        for (String progressBar : progressBars) {
            if (validProgressBars.contains(progressBar)) {
                if (getAttribute(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX_STATUS, "aria-checked", progressBar).equals("false")) {
                    waitForElementClickable(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, progressBar);
                    clickToElement(driver, TrackingPageOTAppUI.PROGRESS_BAR_CHECKBOX, progressBar);
                } else {
                    System.out.println(progressBar + " is already checked");
                }
            } else {
                System.out.println(progressBar + " is invalid");
            }
        }
    }

    @Step("Select Language and position are '{0}' and '{1}'")
    public void selectGoogleLanguage(String language, String position) {
        String languageUpperCaseFirstWord = upperCaseFirstWord(language);
        String positionUpperCaseFirstWord = upperCaseFirstWord(position);

        selectItemInDropdown(driver, TrackingPageOTAppUI.GOOGLE_LANGUAGE_DROPDOWN, languageUpperCaseFirstWord);
        Assert.assertEquals(getSelectedItemInDropdown(driver, TrackingPageOTAppUI.GOOGLE_LANGUAGE_DROPDOWN), languageUpperCaseFirstWord);

        selectItemInDropdown(driver, TrackingPageOTAppUI.GOOGLE_LANGUAGE_POSITION_DROPDOWN, positionUpperCaseFirstWord);
        Assert.assertEquals(getSelectedItemInDropdown(driver, TrackingPageOTAppUI.GOOGLE_LANGUAGE_POSITION_DROPDOWN), positionUpperCaseFirstWord);
    }

    @Step("Click to Use Google translate extension checkbox")
    public void checkedGoogleTranslateCheckbox() {
        String status = getAttribute(driver, TrackingPageOTAppUI.GOOGLE_TRANSLATE_CHECKBOX_STATUS, "aria-checked");
        if(status.equals("false")){
            waitForElementClickable(driver, TrackingPageOTAppUI.GOOGLE_TRANSLATE_CHECKBOX);
            clickToElement(driver, TrackingPageOTAppUI.GOOGLE_TRANSLATE_CHECKBOX);
        } else
            System.out.println("Google translate extension was checked");
    }

    @Step("Send key '{1}' to field {0}")
    public void sendKeyToField(String field, String dataInput) {
        List<WebElement> foldingStatus = getListWebElement(driver, TrackingPageOTAppUI.ADVANCED_TRANSLATION_TITLE);

        for(int i = 0; i < foldingStatus.size(); i++) {
            String status = foldingStatus.get(i).getAttribute("aria-expanded");
            if (status.equals("false")){
                foldingStatus.get(i).click();
                Assert.assertEquals(foldingStatus.get(i).getAttribute("aria-expanded"), "true");
                sleepInSecond(1);
            } else if(status.equals("true")){
                break;
            }
        }

        waitForElementVisible(driver, TrackingPageOTAppUI.TRACKING_STATUS_INPUT, field);
        sendKeyToElementAfterClearText(driver, TrackingPageOTAppUI.TRACKING_STATUS_INPUT, dataInput, field);
    }

    @Step("{0} Estimated Delivery Date")
    public void toggleEstimatedDeliveryDate(String action) {
        waitForTextChange(driver, TrackingPageOTAppUI.ESTIMATED_DELIVERY_DATE_STATUS_BTN_TEXT, "Loading...");

        String status = getTextInElement(driver, TrackingPageOTAppUI.ESTIMATED_DELIVERY_DATE_STATUS_BTN_TEXT);
        String actionUpperCase = upperCaseFirstWord(action);

        if(status.equals(actionUpperCase)){
            waitForElementClickable(driver, TrackingPageOTAppUI.ESTIMATED_DELIVERY_DATE_STATUS_BTN);
            clickToElement(driver, TrackingPageOTAppUI.ESTIMATED_DELIVERY_DATE_STATUS_BTN);

            Assert.assertTrue(isListElementDisplayed(driver, TrackingPageOTAppUI.MESSAGE_TOAST, OT_TrackingPageConstants.UPDATE_ESTIMATED_DELIVERY_DATE_SUCCESSFULLY_MESSAGE));
        } else
            System.out.println("Action invalid");
    }













}
