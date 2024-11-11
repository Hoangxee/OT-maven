package pageUIs.apps.ST;

public class SubscriptionPlansPageSTAppUI {
    public static final String CHOOSE_PLAN_BTN = "xpath=//div[starts-with(@class,'account-plan-details') and descendant::*[text()='%s']]//button";
    public static final String PROCESS_OLD_ORDERS_POPUP = "xpath=//div[@class='Polaris-Modal-Dialog']";
    public static final String ACTIVE_PLAN_BTN = "xpath=//div[@class='Polaris-InlineStack']/button[child::span[text()='%s']]";
    public static final String ACTIVE_PLAN_BTN_TEXT = "xpath=//div[@class='Polaris-InlineStack']/button[@aria-disabled='false']/span";
    public static final String PLAN_PRICE_MONTHLY_TEXT = "xpath=//h6[text()='%s']/following-sibling::div/h3/h3";
    public static final String QUOTA_MONTHLY_TEXT = "xpath=//h6[text()='%s']/following-sibling::div//span/b";
    public static final String PLAN_PRICE_ANNUAL_TEXT = "xpath=//h6[text()='%s']/following-sibling::div/h3//h3";
    public static final String CHOOSE_FREE_PLAN_BTN = "xpath=//div[starts-with(@class,'account-free-right')]//button";
    public static final String PLAN_TAB_BTN = "xpath=//span[@class='account-subscription-btn']/button[child::span[contains(text(),'%s')]]";

}
