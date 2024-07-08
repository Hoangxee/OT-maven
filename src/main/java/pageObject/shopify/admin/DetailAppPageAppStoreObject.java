package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.adminShopify.DetailAppPageAppStoreUI;

public class DetailAppPageAppStoreObject extends BasePage {
    private WebDriver driver;

    public DetailAppPageAppStoreObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click Install app in Shopify store")
    public LoginPageAdminObject clickToAddAppButton() {
        waitForElementClickableByIndex(driver, DetailAppPageAppStoreUI.ADD_APP_BUTTON);
        clickToElement(driver, DetailAppPageAppStoreUI.ADD_APP_BUTTON);
        return PageGeneratorManager.getLoginPageAdmin(driver);
    }
}
