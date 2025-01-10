package pageObject.apps.ST;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.constant.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

import java.util.List;

public class HomePageSTAppObject extends BasePage {
    private WebDriver driver;

    public HomePageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Ship Onboard")
    public void skipOnBoard() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        List<WebElement> closePopupBtn = getListWebElement(driver, HomePageSTAppUI.CLOSE_POPUP_BTN);
        if(closePopupBtn.size() != 0){
            waitForElementClickable(driver, HomePageSTAppUI.CLOSE_POPUP_BTN);
            clickToElement(driver, HomePageSTAppUI.CLOSE_POPUP_BTN);
        }

        List<WebElement> skipBtn = getListWebElement(driver, HomePageSTAppUI.SKIP_BTN_IN_ONBOARD);
        if(skipBtn.size() != 0){
            waitForElementClickable(driver, HomePageSTAppUI.SKIP_BTN_IN_ONBOARD);
            clickToElement(driver, HomePageSTAppUI.SKIP_BTN_IN_ONBOARD);
        }

    }

    @Step("Verify plan is Basic")
    public boolean isPlanBasic(String plan, String quota, String account) {
        waitForElementVisible(driver, HomePageSTAppUI.INFOMATION_TEXT,"plan");
        boolean planLB = getTextInElement(driver, HomePageSTAppUI.INFOMATION_TEXT,"plan").equals(plan);

        waitForElementVisible(driver, HomePageSTAppUI.INFOMATION_TEXT,"have used");
        boolean quotaLB = getTextInElement(driver, HomePageSTAppUI.INFOMATION_TEXT,"have used").contains(quota);

        waitForElementVisible(driver, HomePageSTAppUI.INFOMATION_TEXT,"account");
        boolean accountLB = getTextInElement(driver, HomePageSTAppUI.INFOMATION_TEXT,"account").contains(account);

        return planLB&&quotaLB&&accountLB;
    }

    @Step("Click to Settings tab")
    public SettingsPageSTAppObject openSettingsPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE, STConstants.SETTINGS_PAGES_ST_APP_IN_NAVIGATION);
        clickToElement(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE,STConstants.SETTINGS_PAGES_ST_APP_IN_NAVIGATION);

        return PageGeneratorManager.getSettingsPageSTAppObject(driver);
    }

    @Step("Click to Dispute tab")
    public DisputePageSTAppObject openDisputePage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE, STConstants.DISPUTE_PAGES_ST_APP_IN_NAVIGATION);
        clickToElement(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE,STConstants.DISPUTE_PAGES_ST_APP_IN_NAVIGATION);

        return PageGeneratorManager.getDisputePageSTAppObject(driver);
    }

    @Step("Click to Orders tab")
    public OrdersPageSTAppObject openOrdersPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE, STConstants.ORDERS_PAGES_ST_APP_IN_NAVIGATION);
        clickToElement(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE,STConstants.ORDERS_PAGES_ST_APP_IN_NAVIGATION);

        return PageGeneratorManager.getOrdersPageSTAppObject(driver);
    }

    @Step("Click to Subscription plans tab")
    public SubscriptionPlansPageSTAppObject openSubscriptionPlansPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE, STConstants.SUBSCRIPTION_PANS_PAGES_ST_APP_IN_NAVIGATION);
        clickToElement(driver, HomePageSTAppUI.PAGE_IN_NAVIGATE,STConstants.SUBSCRIPTION_PANS_PAGES_ST_APP_IN_NAVIGATION);

        return PageGeneratorManager.getSubscriptionPlansPageSTAppObject(driver);
    }

    @Step("Verify Plan is {0}")
    public boolean isPlanActivated(String planName, String quota) {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);
        waitForProcessBar(driver, CreateOrderPageAdminUI.PROGRESS_BAR);

        waitForElementVisible(driver, DashboardPageOTAppUI.PLAN_NAME,"1");
        boolean planBl = getTextInElement(driver, DashboardPageOTAppUI.PLAN_NAME,"1").equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.PLAN_NAME,"2");
        boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.PLAN_NAME,"2").contains(quota);
        return planBl&&quotaBl;
    }
}
