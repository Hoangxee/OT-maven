package pageUIs.apps.OT;

public class NotificationsPageOTAppUI {
    public static final String TAB = "xpath=//button[descendant::b[text()='%s']]";
    public static final String SECOND_TAB = "xpath=//button[@id='%s']";
    public static final String MESSAGE_TOAST = "xpath=//span[text()='%s']";
    public static final String CLOSE_VERIFY_EMAIL_POPUP_ICON = "xpath=//img/preceding-sibling::div[@class='cancel-icon']/span";
    public static final String NO_REPLY_OPTION = "xpath=//span[child::input[@id='no_reply']]";
    public static final String YOUR_EMAIL_OPTION = "xpath=//span[child::input[@id='your_email']]";
    public static final String CHANGE_EMAIL_POPUP_TITLE = "xpath=//div[@class='Polaris-InlineGrid']//h2";
    public static final String CONFIRM_BTN_CHANGE_EMAIL_POPUP_TITLE = "xpath=//div[@class='Polaris-InlineStack']/button[not(@class='Polaris-Button')]";
    public static final String YOUR_EMAIL_INPUT = "xpath=//input[@id='account']";
    public static final String SENDER_NAME_INPUT = "xpath=//input[@id='name']";
    public static final String CONNECT_BTN = "xpath=//div[@class='submit-btn']/button";
    public static final String TO_CUSTOMER_TAB_IN_EMAIL_TEMPLATE = "xpath=//button[@id='customer']";
    public static final String TO_YOURSELF_TAB_IN_EMAIL_TEMPLATE = "xpath=//button[@id='merchant']";
    public static final String EDIT_EMAIL_TEMPLATE_BTN = "xpath=//tr[@id='%s']//button[not(@aria-disabled)]";
    public static final String EDIT_EMAIL_TEMPLATE_TITLE = "xpath=//h1[@class='Polaris-Header-Title']";
    public static final String ORDER_STATUS = "xpath=//div[@class='Polaris-Select']/select";
    public static final String EMAIL_STATUS_TOGGLE = "xpath=//div[@class='content']//button";
    public static final String EMAIL_STATUS = "xpath=//div[@class='content']//button//span/div";
    public static final String SUBJECT_INPUT = "xpath=//input[@id='subject']";
    public static final String SAVE_EMAIL_TEMPLATE_BTN = "xpath=//div[@class='save-btn']/button";
    public static final String BACK_BTN = "xpath=//nav[@role='navigation']";
    public static final String SMS_TEMPLATES_STATUS = "xpath=//tr[child::td[text()='%s']]//div[@class='circle']/parent::div";
    public static final String SMS_TEMPLATES_STATUS_TOGGLE = "xpath=//tr[child::td[text()='%s']]//button[descendant::div]";

}
