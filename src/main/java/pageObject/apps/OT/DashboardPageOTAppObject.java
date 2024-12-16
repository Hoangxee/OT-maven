package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.OTConstants;
import commons.PageGeneratorManager;
import commons.constant.OT_SettingsPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.apps.OT.SettingsPageOTAppUI;

public class DashboardPageOTAppObject extends BasePage {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Verify Plan is Starter")
    public boolean isPlanStarter(String planName, String quota) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1");
        boolean planBL = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1").equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2");
        boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2").contains(quota);
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

    @Step("Click to Settings tab and open Tracking link set-up page")
    public SettingsPageOTAppObject openPageInSettings(String pageName) {
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SETTINGS_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SETTINGS_PAGES_OT_APP_IN_NAVIGATION);

        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
        if(!getAttribute(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,"aria-pressed",pageName).equals("true")){
            waitForElementClickable(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
            clickToElement(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
        }

        return PageGeneratorManager.getSettingsPageOTApp(driver);
    }

}
