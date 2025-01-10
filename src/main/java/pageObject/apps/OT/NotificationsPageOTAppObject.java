package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.OT_NotificationsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.apps.OT.NotificationsPageOTAppUI;

import java.util.List;

import static commons.constant.OT_NotificationsPageConstants.*;

public class NotificationsPageOTAppObject extends BasePage {
    private WebDriver driver;

    public NotificationsPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Open Email tab")
    public void openEmailTab() {
        List<WebElement> closeVerifyEmailPopupIcon = getListWebElement(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
        if(!closeVerifyEmailPopupIcon.isEmpty()){
            waitForElementClickable(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
            clickToElement(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
        }

        waitForElementClickable(driver, NotificationsPageOTAppUI.TAB, "Email");
        clickToElement(driver, NotificationsPageOTAppUI.TAB, "Email");
    }

    @Step("Open SMS tab")
    public void openSMSTab() {
        List<WebElement> closeVerifyEmailPopupIcon = getListWebElement(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
        if(!closeVerifyEmailPopupIcon.isEmpty()){
            waitForElementClickable(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
            clickToElement(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
        }

        waitForElementClickable(driver, NotificationsPageOTAppUI.TAB, "SMS");
        clickToElement(driver, NotificationsPageOTAppUI.TAB, "SMS");
    }

    @Step("Open Email account tab")
    public void openEmailAccountTab() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SECOND_TAB, "email_account");
        clickToElement(driver, NotificationsPageOTAppUI.SECOND_TAB, "email_account");
    }

    @Step("Choose no-reply@omegatheme.com")
    public void chooseNoReply() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.NO_REPLY_OPTION);
        clickToElement(driver, NotificationsPageOTAppUI.NO_REPLY_OPTION);

        List<WebElement> changeEmailPopupTitle = getListWebElement(driver, NotificationsPageOTAppUI.CHANGE_EMAIL_POPUP_TITLE);
        if(!changeEmailPopupTitle.isEmpty()){
            Assert.assertEquals(getTextInElementAndTrim(driver, NotificationsPageOTAppUI.CHANGE_EMAIL_POPUP_TITLE), "Changing Email to no-reply@omegatheme.com");

            waitForElementClickable(driver, NotificationsPageOTAppUI.CONFIRM_BTN_CHANGE_EMAIL_POPUP_TITLE);
            clickToElement(driver, NotificationsPageOTAppUI.CONFIRM_BTN_CHANGE_EMAIL_POPUP_TITLE);

            Assert.assertTrue(isListElementDisplayed(driver, NotificationsPageOTAppUI.MESSAGE_TOAST, OT_NotificationsPageConstants.CHANGE_SENDER_EMAIL_SUCCESSFULLY_MESSAGE));
        }
    }

    @Step("Choose and connect email in Use your email as sender’s")
    public void chooseYourEmailSender(String emailInput, String senderNameInput) {
        waitForElementClickable(driver, NotificationsPageOTAppUI.YOUR_EMAIL_OPTION);
        clickToElement(driver, NotificationsPageOTAppUI.YOUR_EMAIL_OPTION);

        waitForElementVisible(driver, NotificationsPageOTAppUI.YOUR_EMAIL_INPUT);
        sendKeyToElement(driver, NotificationsPageOTAppUI.YOUR_EMAIL_INPUT, emailInput);

        waitForElementVisible(driver, NotificationsPageOTAppUI.SENDER_NAME_INPUT);
        sendKeyToElementAfterClearText(driver, NotificationsPageOTAppUI.SENDER_NAME_INPUT, senderNameInput);

        waitForElementAttributeChange(driver, NotificationsPageOTAppUI.CONNECT_BTN, "aria-disabled", "false");
        clickToElement(driver, NotificationsPageOTAppUI.CONNECT_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, NotificationsPageOTAppUI.MESSAGE_TOAST, OT_NotificationsPageConstants.VERIFY_EMAIL_MESSAGE));

        waitForElementVisible(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
        clickToElement(driver, NotificationsPageOTAppUI.CLOSE_VERIFY_EMAIL_POPUP_ICON);
    }

    @Step("Open Email template tab")
    public void openEmailTemplateTab() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SECOND_TAB, "email_templates");
        clickToElement(driver, NotificationsPageOTAppUI.SECOND_TAB, "email_templates");
    }

    @Step("Click to 'To customer' in Email template")
    public void clickToToCustomer() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.TO_CUSTOMER_TAB_IN_EMAIL_TEMPLATE);
        clickToElement(driver, NotificationsPageOTAppUI.TO_CUSTOMER_TAB_IN_EMAIL_TEMPLATE);
    }

    @Step("Click to 'To yourself' in Email template")
    public void clickToToYourself() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.TO_YOURSELF_TAB_IN_EMAIL_TEMPLATE);
        clickToElement(driver, NotificationsPageOTAppUI.TO_YOURSELF_TAB_IN_EMAIL_TEMPLATE);
    }

    @Step("Click to edit email template of status {0}")
    public void openEditEmailTemplateByOrderStatus(String status) {
        switch (status.toUpperCase()){
            case PENDING_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("pending",PENDING_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case INFORMATION_RECEIVED_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("information_received",INFORMATION_RECEIVED_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case IN_TRANSIT_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("in_transit",IN_TRANSIT_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case DELIVERED_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("delivered",DELIVERED_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case OUT_FOR_DELIVERY_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("out_for_delivery",OUT_FOR_DELIVERY_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case EXCEPTION_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("exception",EXCEPTION_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case EXPIRED_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("expired",EXPIRED_STATUS_IN_EMAIL_TEMPLATE);
                break;
            case FAILED_ATTEMPT_STATUS_IN_EMAIL_TEMPLATE:
                clickToEditEmailTemplate("failed_attempt",FAILED_ATTEMPT_STATUS_IN_EMAIL_TEMPLATE);
                break;
            default:
                System.out.println("Order status invalid");
        }
    }
    private void clickToEditEmailTemplate(String orderStatus, String orderStatusInEditEmailTemplatePage) {
        waitForElementClickable(driver, NotificationsPageOTAppUI.EDIT_EMAIL_TEMPLATE_BTN,orderStatus);
        clickToElement(driver, NotificationsPageOTAppUI.EDIT_EMAIL_TEMPLATE_BTN,orderStatus);

        waitForElementVisible(driver, NotificationsPageOTAppUI.EDIT_EMAIL_TEMPLATE_TITLE);
        Assert.assertEquals(getTextInElement(driver, NotificationsPageOTAppUI.EDIT_EMAIL_TEMPLATE_TITLE), OT_NotificationsPageConstants.EDIT_EMAIL_TEMPLATE_TITLE);
        waitForElementPresence(driver, NotificationsPageOTAppUI.ORDER_STATUS);
        Assert.assertEquals(getSelectedItemInDropdown(driver, NotificationsPageOTAppUI.ORDER_STATUS).toUpperCase(), orderStatusInEditEmailTemplatePage);
    }

    @Step("Enable email")
    public void enableEmail() {
        String status = getAttribute(driver, NotificationsPageOTAppUI.EMAIL_STATUS, "class");
        if (status.equals("toggle-btn")){
            waitForElementClickable(driver, NotificationsPageOTAppUI.EMAIL_STATUS_TOGGLE);
            clickToElement(driver, NotificationsPageOTAppUI.EMAIL_STATUS_TOGGLE);
        }
    }

    @Step("Disable email")
    public void disableEmail() {
        String status = getAttribute(driver, NotificationsPageOTAppUI.EMAIL_STATUS, "class");
        if (!status.equals("toggle-btn")){
            waitForElementClickable(driver, NotificationsPageOTAppUI.EMAIL_STATUS_TOGGLE);
            clickToElement(driver, NotificationsPageOTAppUI.EMAIL_STATUS_TOGGLE);
        }
    }

    @Step("Send text '{0}' to Subject input")
    public void sendTextToSubjectInput(String dataInput) {
        waitForElementVisible(driver, NotificationsPageOTAppUI.SUBJECT_INPUT);
        sendKeyToElementAfterClearText(driver, NotificationsPageOTAppUI.SUBJECT_INPUT, dataInput);
    }

    @Step("Send text '{0}' to Subject input")
    public void saveEmailTemplate() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SAVE_EMAIL_TEMPLATE_BTN);
        clickToElement(driver, NotificationsPageOTAppUI.SAVE_EMAIL_TEMPLATE_BTN);

        Assert.assertTrue(isListElementDisplayed(driver, NotificationsPageOTAppUI.MESSAGE_TOAST, OT_NotificationsPageConstants.SAVE_EMAIL_TEMPLATE_SUCCESSFULLY));
    }

    @Step("Click to Back button and redirect to Email template page")
    public void backToEmailTemplate() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.BACK_BTN);
        clickToElement(driver, NotificationsPageOTAppUI.BACK_BTN);
    }

    @Step("Open SMS credit tab")
    public void openSMSCreditTab() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SECOND_TAB, "smsCredit");
        clickToElement(driver, NotificationsPageOTAppUI.SECOND_TAB, "smsCredit");
    }

    @Step("Open SMS template tab")
    public void openSMSTemplateTab() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SECOND_TAB, "orderStatus");
        clickToElement(driver, NotificationsPageOTAppUI.SECOND_TAB, "orderStatus");
    }

    @Step("Enable SMS templates by order status")
    public void enableSendSMSByOrderStatus(String status) {
        handleSMSTemplate(status, true);
    }

    @Step("Disable SMS templates by order status")
    public void disableSendSMSByOrderStatus(String status) {
        handleSMSTemplate(status, false);
    }

    private void handleSMSTemplate(String status, boolean isEnable) {
        String orderStatus = getOrderStatusText(status);
        System.out.println("1: "+orderStatus);
        if (orderStatus == null) {
            System.out.println("Order status invalid");
            return;
        }

        String smsTemplateStatus = getAttribute(driver, NotificationsPageOTAppUI.SMS_TEMPLATES_STATUS, "class", orderStatus);
        boolean isCurrentlyEnabled = smsTemplateStatus.equals("toggle-btn");
        System.out.println("2: "+isCurrentlyEnabled);

        if ((isEnable==false && !isCurrentlyEnabled) || (isEnable==true && isCurrentlyEnabled)) {
            String currentAttributeValue = smsTemplateStatus;
            System.out.println("3: "+currentAttributeValue);

            waitForElementClickable(driver, NotificationsPageOTAppUI.SMS_TEMPLATES_STATUS_TOGGLE, orderStatus);
            clickToElement(driver, NotificationsPageOTAppUI.SMS_TEMPLATES_STATUS_TOGGLE, orderStatus);

            Assert.assertTrue(isListElementDisplayed(driver, NotificationsPageOTAppUI.MESSAGE_TOAST, OT_NotificationsPageConstants.SAVE_SMS_TEMPLATE_SUCCESSFULLY));

            waitForElementAttributeChangeToInitialValue(driver, NotificationsPageOTAppUI.SMS_TEMPLATES_STATUS, "class", currentAttributeValue, orderStatus);
        } else
            System.out.println("fail");
    }

    private String getOrderStatusText(String status) {
        switch (status.toUpperCase()) {
            case PENDING_STATUS_IN_EMAIL_TEMPLATE:
                return "Pending";
            case INFORMATION_RECEIVED_STATUS_IN_EMAIL_TEMPLATE:
                return "Information received";
            case IN_TRANSIT_STATUS_IN_EMAIL_TEMPLATE:
                return "In transit";
            case DELIVERED_STATUS_IN_EMAIL_TEMPLATE:
                return "Delivered";
            case OUT_FOR_DELIVERY_STATUS_IN_EMAIL_TEMPLATE:
                return "Out for delivery";
            case EXCEPTION_STATUS_IN_EMAIL_TEMPLATE:
                return "Exception";
            case EXPIRED_STATUS_IN_EMAIL_TEMPLATE:
                return "Expired";
            case FAILED_ATTEMPT_STATUS_IN_EMAIL_TEMPLATE:
                return "Failed attempt";
            default:
                return null;
        }
    }

    @Step("Open SMS history tab")
    public void openSMSHistoryTab() {
        waitForElementClickable(driver, NotificationsPageOTAppUI.SECOND_TAB, "smsHistory");
        clickToElement(driver, NotificationsPageOTAppUI.SECOND_TAB, "smsHistory");
    }
}
