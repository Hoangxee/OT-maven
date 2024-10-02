package pageUIs.apps.OT;

public class SubscriptionPlansOTAppUI {
    public static final String APP_IFRAME = "xpath=//iframe[@name='app-iframe']";
    public static final String CHOOSE_PLAN_BTN = "xpath=//div[@class='plan-title' and child::h6[text()='%s']]/parent::div/following-sibling::div/button";
    public static final String PLAN_PRICE_MONTHLY = "xpath=//div[@class='plan-title' and child::h6[text()='%s']]/following-sibling::div/h3";
}
