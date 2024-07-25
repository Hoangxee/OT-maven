package pageObject.apps.ST;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.SettingsPageSTAppUI;

public class SettingsPageSTAppObject extends BasePage {
    private WebDriver driver;

    public SettingsPageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click to Orders tab")
    public OrdersPageSTAppObject openOrdersPage() {
        switchToDefaultContent(driver);

        waitForElementClickable(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE, STConstants.ORDERS_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, SettingsPageSTAppUI.PAGE_OT_IN_NAVIGATE,STConstants.ORDERS_PAGES_OT_APP_IN_NAVIGATION);

        return PageGeneratorManager.getOrdersPageSTAppObject(driver);
    }

    @Step("Verify information Paypal account")
    public boolean isPaypalAccountCorrect(String email, String id) {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        boolean accountLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,email);
        boolean idLB = isElementDisplayed(driver, SettingsPageSTAppUI.PRIMARY_ACCOUNT_LABEL,id);

        return accountLB&&idLB;
    }
}
