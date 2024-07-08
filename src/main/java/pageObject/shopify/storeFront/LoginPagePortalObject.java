package pageObject.shopify.storeFront;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.storeFront.LoginPagePortalUI;

public class LoginPagePortalObject extends BasePage{
    private WebDriver driver;

    public LoginPagePortalObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Login to Shopify store")
    public HomePagePortalObject loginToShopifyStore(String password) {
        if(getWebElement(driver, LoginPagePortalUI.PASSWORD_TEXTBOX).isDisplayed()){
            waitForElementVisible(driver, LoginPagePortalUI.PASSWORD_TEXTBOX);
            sendKeyToElement(driver, LoginPagePortalUI.PASSWORD_TEXTBOX, password);
            waitForElementClickableByIndex(driver, LoginPagePortalUI.ENTER_BUTTON);
            clickToElement(driver, LoginPagePortalUI.ENTER_BUTTON);
        }
        return PageGeneratorManager.getHomePageStoreFront(driver);
    }
}
