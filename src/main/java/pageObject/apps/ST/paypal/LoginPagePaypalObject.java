package pageObject.apps.ST.paypal;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.apps.ST.HomePageSTAppObject;
import pageUIs.apps.ST.LoginPagePaypalUI;

public class LoginPagePaypalObject extends BasePage {
    WebDriver driver;

    public LoginPagePaypalObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Login to Paypal")
    public HomePageSTAppObject loginToPaypal(String email, String password) {
        switchToWindowByTitle(driver, STConstants.LOG_IN_PAYPAL_TITLE_PAGE);

        waitForElementVisible(driver, LoginPagePaypalUI.EMAIL_INPUT);
        sendKeyToElement(driver, LoginPagePaypalUI.EMAIL_INPUT,email);

        waitForElementClickable(driver, LoginPagePaypalUI.NEXT_BTN);
        clickToElement(driver, LoginPagePaypalUI.NEXT_BTN);

        waitForElementVisible(driver, LoginPagePaypalUI.PASSWORD_INPUT);
        sendKeyToElement(driver, LoginPagePaypalUI.PASSWORD_INPUT,password);

        waitForElementClickable(driver, LoginPagePaypalUI.LOGIN_BTN);
        clickToElement(driver, LoginPagePaypalUI.LOGIN_BTN);

        return PageGeneratorManager.getHomePageSTApp(driver);
    }
}
