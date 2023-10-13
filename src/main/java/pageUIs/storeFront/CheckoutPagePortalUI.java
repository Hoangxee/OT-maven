package pageUIs.storeFront;

public class CheckoutPagePortalUI {
    public static final String CONTACT_INPUT = "xpath=//input[@id='email']";
    public static final String FIRST_NAME_INPUT = "xpath=//input[@name='firstName']";
    public static final String LAST_NAME_INPUT = "xpath=//input[@name='lastName']";
    public static final String ADDRESS_INPUT = "xpath=//input[@name='address1']";
    public static final String CITY_INPUT = "xpath=//input[@name='city']";
    public static final String POSTAL_CODE_INPUT = "xpath=//input[@name='postalCode']";
    public static final String CONTINUE_TO_SHIPPING_BUTTON = "xpath=//button[@type='submit']";
    public static final String CONTINUE_TO_PAYMENT_BUTTON = "xpath=//span[contains(text(),'Continue to payment')]/parent::button";
    public static final String CREDIT_CARD_IFRAME = "xpath=//iframe[contains(@title,'%s')]";
    public static final String CREDIT_CARD_INPUT = "xpath=//input[@placeholder='%s']";
    public static final String EXPIRATION_DATE_IN_CREDIT_CARD = "xpath=//label[text()='Expiration date (MM / YY)']/following-sibling::input[1]";
    public static final String PAY_NOW_BUTTON = "xpath=//span[contains(text(),'Pay now')]/parent::span/parent::button";
    public static final String THANK_YOU_TEXT = "xpath=//h2[contains(text(),'Thank you')]";
    public static final String CONTINUE_SHOPPING_BUTTON = "xpath=//span[text()='Continue shopping']/parent::a";
}
