package pageObject.shopify.admin;

import commons.BasePage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.apps.OT.SubscriptionPlansOTAppObject;
import pageUIs.adminShopify.ApproveSubscriptionAdminUI;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.apps.OT.DashboardPageOTAppUI;

public class ApproveSubscriptionAdminObject extends BasePage {
    private WebDriver driver;

    public ApproveSubscriptionAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Approve plan in Approve subscription page")
    public void approvePlan() {
        waitForProcessBar(driver, CreateOrderPageAdminUI.PROGRESS_BAR);
        switchToWindowByTitleContains(driver, GlobalConstants.APPROVE_SUBSCRIPTION_TITLE_PAGE);

        if(getTextInElement(driver, ApproveSubscriptionAdminUI.TOTAL_PRICE_TEXT).contains(SubscriptionPlansOTAppObject.professionalPriceMonthly)){
            waitForElementClickable(driver, ApproveSubscriptionAdminUI.APPROVE_BTN);
            clickToElement(driver, ApproveSubscriptionAdminUI.APPROVE_BTN);
        }
        else {
            log.info("Total price in Shopify not equal to Subscription plans page");
            closeWindow(driver);
        }
    }

    @Step("Verify Plan is Professional")
    public boolean isPlanProfessional(String planName, String quota) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT);
        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT), planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.QUOTA_TEXT);
        return getTextInElement(driver, DashboardPageOTAppUI.QUOTA_TEXT).contains(quota);
    }
}
