package pageUIs.apps.ST;

public class DisputePageSTAppUI {
    public static final String DISPUTE_PAGE_TITLE = "xpath=//*[@class='Polaris-Header-Title']/span";
    public static final String DISPUTE_MANAGEMENT_STATUS = "xpath=(//div[@class='toggle-settings-title']//span)[3]";
    public static final String TURN_ON_BTN_DISPUTE_MANAGEMENT = "xpath=//div[@class='toggle-btn']/button";
    public static final String TURN_OFF_BTN_DISPUTE_MANAGEMENT = "xpath=//h5[text()='Dispute management']/ancestor::div[@class='Polaris-Box']//button";
    public static final String CONNECT_PAYPAL_ACCOUNT_BTN = "xpath=//div[@class='custom-card-content']//button";
    public static final String ADD_NEW_PAYPAL_ACCOUNT_POPUP_TITLE = "xpath=//div[@class='Polaris-InlineGrid']/div[@class='Polaris-InlineStack']/h2";
    public static final String INPUT_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP = "xpath=//div[@class='Polaris-Modal-Section']//input[@type='%s']";
    public static final String BTN_IN_ADD_NEW_PAYPAL_ACCOUNT_POPUP = "xpath=//div[@class='Polaris-InlineStack']/button[child::span[text()='%s']]";
    public static final String SKELETON_ELEMENT = "xpath=//div[@class='Polaris-SkeletonBodyText']";
    public static final String EMAIL_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK = "xpath=//div[@class='dispute-account-content']//h6/following-sibling::span";
    public static final String STATUS_TEXT_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK = "xpath=//div[@class='dispute-account-content']//span[text()='Success']/following-sibling::span";
    public static final String DELETE_BTN_IN_CONNECTED_PAYPAL_ACCOUNT_BLOCK = "xpath=//div[@class='btn-group']/div//button";
    public static final String MESSAGE_TOAST = "xpath=//span[text()='%s']";
}
