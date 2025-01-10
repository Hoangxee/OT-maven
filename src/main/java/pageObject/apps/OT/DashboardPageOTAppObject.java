package pageObject.apps.OT;

import commons.BasePage;
import commons.constant.OTConstants;
import commons.PageGeneratorManager;
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

        waitForElementVisible(driver, DashboardPageOTAppUI.PLAN_NAME);
        boolean planBL = getTextInElement(driver, DashboardPageOTAppUI.PLAN_NAME).equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.QUOTA);
        boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.QUOTA).contains(quota);
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
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SHIPMENT_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SHIPMENT_PAGES_OT_APP_IN_NAVIGATION);

        return PageGeneratorManager.getShipmentPageOTApp(driver);
    }

    @Step("Click to Settings tab and open Tracking link set-up page")
    public SettingsPageOTAppObject openPageInSettings(String pageName) {
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SETTING_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SETTING_PAGES_OT_APP_IN_NAVIGATION);

        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
        if(!getAttribute(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,"aria-pressed",pageName).equals("true")){
            waitForElementClickable(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
            clickToElement(driver, SettingsPageOTAppUI.MENU_BTN_IN_SETTINGS_PAGE,pageName);
        }

        return PageGeneratorManager.getSettingsPageOTApp(driver);
    }

    @Step("Click to Notification tab")
    public NotificationsPageOTAppObject openNotificationsPage() {
        switchToDefaultContent(driver);
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.NOTIFICATIONS_PAGE_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.NOTIFICATIONS_PAGE_OT_APP_IN_NAVIGATION);

        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        return PageGeneratorManager.getNotificationsPageOTAppObject(driver);
    }

    @Step("Click to Tracking Page tab")
    public TrackingPageOTAppObject openTrackingPage() {
        switchToDefaultContent(driver);
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.TRACKING_PAGE_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.TRACKING_PAGE_OT_APP_IN_NAVIGATION);

        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        return PageGeneratorManager.getTrackingPageOTAppObject(driver);
    }


}
