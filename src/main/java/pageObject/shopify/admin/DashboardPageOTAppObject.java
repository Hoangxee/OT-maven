package pageObject.shopify.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.adminShopify.DashboardPageOTAppUI;

public class DashboardPageOTAppObject extends BaseTest {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    public void verifyAfterInstallOrderTracking(){
        driver.navigate().refresh();
        waitForElementVisible(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT);
        Assert.assertEquals(getWebElement(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT).getText(), "Starter");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://apps.trackordernow.com/dashboard"));
    }
}
