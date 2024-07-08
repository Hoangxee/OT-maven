package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.adminShopify.LoginPageAdminUI;

import java.util.List;
import java.util.Set;

public class LoginPageAdminObject extends BasePage {
    private WebDriver driver;

    public LoginPageAdminObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    @Step("Login to Shopify admin")
    public HomePageAdminObject loginToShopifyAdmin(String email, String password) {
        switchNewTabToLoginToShopifyAdmin();
        waitForElementVisible(driver, LoginPageAdminUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageAdminUI.EMAIL_TEXTBOX, email);
        waitForElementClickableByIndex(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        clickToElement(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);

        waitForElementVisible(driver, LoginPageAdminUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageAdminUI.PASSWORD_TEXTBOX, password);
        waitForElementClickableByIndex(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        clickToElement(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        List<WebElement> listEmailAccount = getListWebElement(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
        if (listEmailAccount.size() != 0) {
            waitForElementClickableByIndex(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
            clickToElement(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
        }

        List<WebElement> remindNextTime = getListWebElement(driver, LoginPageAdminUI.REMIND_NEXT_TIME_IN_SECURITY_SETTINGS_FORM);
        if (remindNextTime.size() != 0) {
            waitForElementClickableByIndex(driver, LoginPageAdminUI.REMIND_NEXT_TIME_IN_SECURITY_SETTINGS_FORM);
            clickToElement(driver, LoginPageAdminUI.REMIND_NEXT_TIME_IN_SECURITY_SETTINGS_FORM);
        }

        return PageGeneratorManager.getHomePageAdmin(driver);
    }

    public void switchNewTabToLoginToShopifyAdmin() {
        Set<String> allWindowID = driver.getWindowHandles();

        for(String id:allWindowID){
            driver.switchTo().window(id);
            String currentPageTitle = driver.getTitle();
            if (currentPageTitle.equals("Log in — Shopify App Store")) {
                break;
            }
        }
    }
}
