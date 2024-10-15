package pageObject.apps.ST;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.constant.STConstants;
import commons.constant.ST_SubscriptionPlansPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.shopify.admin.ApproveSubscriptionAdminObject;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.SubscriptionPlansPageSTAppUI;

import java.util.List;

public class SubscriptionPlansPageSTAppObject extends BasePage {
    private WebDriver driver;
    public SubscriptionPlansPageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click to Choose plan button in {0}")
    public ApproveSubscriptionAdminObject chooseMonthlyPlan(String planName){
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementVisible(driver, SubscriptionPlansPageSTAppUI.PLAN_PRICE_MONTHLY, planName.toUpperCase());
        planPriceMonthly = getTextInElement(driver, SubscriptionPlansPageSTAppUI.PLAN_PRICE_MONTHLY, planName.toUpperCase());

        List<WebElement> choosePlanBtn = getListWebElement(driver, SubscriptionPlansPageSTAppUI.CHOOSE_PLAN_BTN,planName);
        if (choosePlanBtn.size() != 0) {
            clickToChoosePlanBtn(planName);
        } else {
            waitForElementClickable(driver, SubscriptionPlansPageSTAppUI.CHOOSE_FREE_PLAN_BTN);
            clickToElement(driver, SubscriptionPlansPageSTAppUI.CHOOSE_FREE_PLAN_BTN);

            waitForElementClickable(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.CONFIRM_PLAN_BTN_TEXT);
            clickToElement(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.CONFIRM_PLAN_BTN_TEXT);

            clickToChoosePlanBtn(planName);
        }
        return PageGeneratorManager.getApproveSubscriptionAdminObject(driver);
    }

    private void clickToChoosePlanBtn(String planName){
        waitForElementClickable(driver, SubscriptionPlansPageSTAppUI.CHOOSE_PLAN_BTN, planName);
        clickToElement(driver, SubscriptionPlansPageSTAppUI.CHOOSE_PLAN_BTN, planName);

        Assert.assertTrue(isElementDisplayed(driver, SubscriptionPlansPageSTAppUI.PROCESS_OLD_ORDERS_POPUP));

        if (getTextInElement(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN_TEXT).equals(ST_SubscriptionPlansPageConstants.ACTIVE_PLAN_BTN_TEXT)) {
            waitForElementClickable(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.ACTIVE_PLAN_BTN_TEXT);
            clickToElement(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.ACTIVE_PLAN_BTN_TEXT);
        } else if (getTextInElement(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN_TEXT).equals(ST_SubscriptionPlansPageConstants.CONFIRM_PLAN_BTN_TEXT)) {
            waitForElementClickable(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.CONFIRM_PLAN_BTN_TEXT);
            clickToElement(driver, SubscriptionPlansPageSTAppUI.ACTIVE_PLAN_BTN, ST_SubscriptionPlansPageConstants.CONFIRM_PLAN_BTN_TEXT);
        }
    }

    public static String planPriceMonthly;
}
