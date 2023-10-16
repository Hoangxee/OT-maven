package pageObject.shopify.admin;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.adminShopify.SearchResultPageAppStoreUI;

public class SearchResultPageAppStoreObject extends BaseTest {
    private WebDriver driver;

    public SearchResultPageAppStoreObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    public DetailAppPageAppStoreObject clickToApp() {
        waitForElementClickable(driver, SearchResultPageAppStoreUI.APP_NAME);
        clickToElementByJS(driver,SearchResultPageAppStoreUI.APP_NAME);
        return PageGeneratorManager.getDetailAppPageAppStore(driver);
    }
}
