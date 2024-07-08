package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.adminShopify.DashboardPageOTAppUI;

public class DashboardPageOTAppObject extends BasePage {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Verify Plan is Starter")
    public boolean isPlanStarter() {
        waitForElementVisible(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT);
        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT), "Starter");

        waitForElementVisible(driver, DashboardPageOTAppUI.QUOTA_TEXT);
        return getTextInElement(driver, DashboardPageOTAppUI.QUOTA_TEXT).contains("35");
    }

    @Step("Get old order")
    public void getOldOrder() {
        waitForElementClickableByIndex(driver, DashboardPageOTAppUI.GET_OLD_ORDER_BTN);
        clickToElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_BTN);

        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_TITLE), "Would you like to process the old orders?");

        waitForElementClickableByIndex(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_BTN,"OK");
        clickToElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_BTN,"OK");

        waitForElementVisible(driver, DashboardPageOTAppUI.GET_OLD_ORDER_MESSAGE_SUCCESS);
        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_MESSAGE_SUCCESS), "Process orders successfully!");
    }

    @Step("Click to Shipment tab")
    public ShipmentPageOTAppObject openShipmentPage() {
        switchToDefaultContent(driver);
        waitForElementClickableByIndex(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,"Shipments");
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,"Shipments");

        return PageGeneratorManager.getShipmentPageOTApp(driver);
    }

}
