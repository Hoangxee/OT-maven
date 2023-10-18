package pageObject.shopify.admin;

import commons.BasePage;
import commons.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.adminShopify.DashboardPageOTAppUI;

public class DashboardPageOTAppObject extends BasePage {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Description("Verify after install app")
    public void verifyAfterInstallOrderTracking(){
        driver.navigate().refresh();
        waitForElementVisible(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT);
        Assert.assertEquals(getWebElement(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT).getText(), "Starter");
    }
}
