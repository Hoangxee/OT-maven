package pageObject.shopify.admin;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageUIs.adminShopify.HomePageAppStoreUI;

public class HomePageAppStoreObject extends BaseTest {
    private WebDriver driver;

    public HomePageAppStoreObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    public SearchResultPageAppStoreObject searchApp(String value) {
        waitForElementVisible(driver, HomePageAppStoreUI.SEARCHBAR_INPUT);
        sendKeyToElement(driver, HomePageAppStoreUI.SEARCHBAR_INPUT, value);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        return PageGeneratorManager.getSearchResultPageAppStore(driver);
    }
}
