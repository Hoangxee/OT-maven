package pageObject.apps.OT;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.shopify.admin.ApproveSubscriptionAdminObject;
import pageUIs.apps.OT.SubscriptionPlansOTAppUI;

public class SubscriptionPlansOTAppObject extends BasePage {
    private WebDriver driver;

    public SubscriptionPlansOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Choose plan {0}")
    public ApproveSubscriptionAdminObject choosePlan(String planName) {
        switchToFrameIframe(driver, SubscriptionPlansOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, SubscriptionPlansOTAppUI.PLAN_PRICE_MONTHLY, planName.toUpperCase());
        planPriceMonthly = getTextInElement(driver, SubscriptionPlansOTAppUI.PLAN_PRICE_MONTHLY, planName.toUpperCase());

        waitForElementClickable(driver, SubscriptionPlansOTAppUI.CHOOSE_PLAN_BTN,planName.toUpperCase());
        clickToElement(driver, SubscriptionPlansOTAppUI.CHOOSE_PLAN_BTN,planName.toUpperCase());

        return PageGeneratorManager.getApproveSubscriptionAdmin(driver);
    }

    public static String planPriceMonthly;
}
