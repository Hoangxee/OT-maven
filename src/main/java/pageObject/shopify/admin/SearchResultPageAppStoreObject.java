package pageObject.shopify.admin;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.adminShopify.SearchResultPageAppStoreUI;

public class SearchResultPageAppStoreObject extends BasePage {
    private WebDriver driver;

    public SearchResultPageAppStoreObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Search app {0}")
    public DetailAppPageAppStoreObject clickToApp(String appName) {
        waitForElementClickable(driver, SearchResultPageAppStoreUI.APP_NAME, appName);
        clickToElementByJS(driver,SearchResultPageAppStoreUI.APP_NAME, appName);
        return PageGeneratorManager.getDetailAppPageAppStore(driver);
    }
}
