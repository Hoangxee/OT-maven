package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.adminShopify.LoginPageAdminUI;

import java.util.List;

public class LoginPageAdminObject extends BasePage {
    private WebDriver driver;

    public LoginPageAdminObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    @Step("Login to Shopify admin")
    public HomePageAdminObject loginToShopifyAdmin(String email, String password) {
        waitForElementVisible(driver, LoginPageAdminUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageAdminUI.EMAIL_TEXTBOX, email);
        waitForElementClickable(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        clickToElement(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);

        waitForElementVisible(driver, LoginPageAdminUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageAdminUI.PASSWORD_TEXTBOX, password);
        waitForElementClickable(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        clickToElement(driver, LoginPageAdminUI.COMMIT_LOGIN_BUTTON);
        List<WebElement> listEmailAccount = getListWebElement(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
        if (listEmailAccount.size() != 0) {
            waitForElementClickable(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
            clickToElement(driver, LoginPageAdminUI.EMAIL_ACCOUNT);
        }
        return PageGeneratorManager.getHomePageAdmin(driver);
    }
}
