package pageObject.shopify.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class SettingsPageRDAppObject extends BasePage {
    private WebDriver driver;

    public SettingsPageRDAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }


    public void clickToGeneralSettingsMenu() {
        waitForElementClickableByIndex(driver, "xpath=//b[text()='General settings']/ancestor::button");
        clickToElement(driver, "xpath=//b[text()='General settings']/ancestor::button");
    }

    public void enableShopifySync() {
//        waitForElementClickable(driver, );
//        clickToElement(driver, );
    }
}
