package pageObject.apps.ST;

import commons.BasePage;
import commons.OTConstants;
import commons.PageGeneratorManager;
import commons.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.Assert;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.ST.HomePageSTAppUI;

import java.util.List;

public class HomePageSTAppObject extends BasePage {
    private WebDriver driver;

    public HomePageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Ship Onboard")
    public void skipOnBoard() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

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

    @Step("Connect to Paypal")
    public LoginPagePaypalObject connectToPaypal() {
        waitForElementClickable(driver, HomePageSTAppUI.CONNECT_NOW_BTN);
        clickToElement(driver, HomePageSTAppUI.CONNECT_NOW_BTN);

        return PageGeneratorManager.getLoginPagePaypal(driver);
    }

    @Step("Verify Connected Paypal account")
    public boolean hadConnectedPaypalAccount(String account) {
//        switchToWindowByTitleContains(driver, STConstants.SYNCTRACK_ADMIN_TITLE_PAGE);
//        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

//        boolean verifyMessageConnectPapalSuccessful = isElementDisplayed(driver, HomePageSTAppUI.CONNECT_PAYPAL_ACCOUNT_SUCCESSFUL_MESSAGE);

//        boolean verifyCountStep = isElementDisplayed(driver, HomePageSTAppUI.PROGRESS_COUNT_STEP,"1");
        boolean accountLB = getTextInElement(driver, HomePageSTAppUI.INFOMATION_TEXT,"account").contains(account);

//        return verifyMessageConnectPapalSuccessful&&verifyCountStep&&accountLB;
        return accountLB;
    }

    @Step("Click to Settings tab")
    public SettingsPageSTAppObject openSettingsPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE, STConstants.SETTINGS_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,STConstants.SETTINGS_PAGES_OT_APP_IN_NAVIGATION);

        return PageGeneratorManager.getSettingsPageSTAppObject(driver);
    }

}
