package pageObject.apps.OT;

import commons.BasePage;
import commons.OTConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;

public class DashboardPageOTAppObject extends BasePage {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Verify Plan is Starter")
    public boolean isPlanStarter(String planName, String quota) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT);
        boolean planBL = getTextInElement(driver, DashboardPageOTAppUI.ACCOUNT_PLAN_TEXT).equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.QUOTA_TEXT);
        boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.QUOTA_TEXT).contains(quota);
        return planBL && quotaBl;
    }

    @Step("Get old order")
    public void getOldOrder() {
        waitForElementClickable(driver, DashboardPageOTAppUI.GET_OLD_ORDER_BTN);
        clickToElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_BTN);

        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_TITLE), OTConstants.GET_OLD_ORDER_POPUP_TITLE);

        waitForElementClickable(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_BTN,"OK");
        clickToElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_POPUP_BTN,"OK");

        waitForElementVisible(driver, DashboardPageOTAppUI.GET_OLD_ORDER_MESSAGE_SUCCESS);
        Assert.assertEquals(getTextInElement(driver, DashboardPageOTAppUI.GET_OLD_ORDER_MESSAGE_SUCCESS), OTConstants.GET_OLD_ORDER_PROCESS_SUCCESSFULLY_MESSAGE);
    }

    @Step("Click to Shipment tab")
    public ShipmentPageOTAppObject openShipmentPage() {
        switchToDefaultContent(driver);
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SHIPMENTS_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SHIPMENTS_PAGES_OT_APP_IN_NAVIGATION);

        return PageGeneratorManager.getShipmentPageOTApp(driver);
    }

}
