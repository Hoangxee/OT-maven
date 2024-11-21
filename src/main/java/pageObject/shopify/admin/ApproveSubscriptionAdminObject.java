package pageObject.shopify.admin;

import commons.BasePage;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.apps.ST.HomePageSTAppObject;
import pageUIs.adminShopify.ApproveSubscriptionAdminUI;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.apps.OT.DashboardPageOTAppUI;

public class ApproveSubscriptionAdminObject extends BasePage {
    private WebDriver driver;

    public ApproveSubscriptionAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Approve plan in Approve subscription page")
    public HomePageSTAppObject approvePlan(String pricePlan) {
        waitForProcessBar(driver, CreateOrderPageAdminUI.PROGRESS_BAR);
        switchToWindowByTitleContains(driver, GlobalConstants.APPROVE_SUBSCRIPTION_TITLE_PAGE);

        if(getTextInElement(driver, ApproveSubscriptionAdminUI.TOTAL_PRICE_TEXT).contains(pricePlan)){
            waitForElementClickable(driver, ApproveSubscriptionAdminUI.APPROVE_BTN);
            clickToElement(driver, ApproveSubscriptionAdminUI.APPROVE_BTN);
        }
        else {
            log.info("Total price in Shopify not equal to Subscription plans page");
            closeWindowTab(driver);
        }
        return PageGeneratorManager.getHomePageSTAppObject(driver);
    }

    @Step("Verify Plan is {0}")
    public boolean isPlanActivated(String planName, String quota) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1");
        boolean planBl = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1").equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2");
       boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2").contains(quota);
       return planBl&&quotaBl;
    }
}
