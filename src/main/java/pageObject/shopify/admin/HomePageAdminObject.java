package pageObject.shopify.admin;

import commons.BasePage;
import commons.GlobalConstants;
import commons.OTConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.RD.SettingsPageRDAppObject;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.adminShopify.HomePageAdminUI;

import java.util.List;

public class HomePageAdminObject extends BasePage {
    private WebDriver driver;

    public HomePageAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Open Settings menu in Shopify admin")
    public void openSettingsMenu() {
        waitForElementClickable(driver, HomePageAdminUI.SETTING_ICON);
        clickToElement(driver, HomePageAdminUI.SETTING_ICON);
    }

    @Step("Open List apps in Shopify admin")
    public void openListAppsInSettingMenu() {
        waitForElementClickable(driver, HomePageAdminUI.LIST_APP_IN_SHOPIFY);
        clickToElement(driver, HomePageAdminUI.LIST_APP_IN_SHOPIFY);
    }

    @Step("Check available app {0}")
    public void checkAvailableApp(String appName) {
        List<WebElement> appInListApps = getListWebElement(driver,HomePageAdminUI.APP_NAME_IN_NAVIGATION, appName);
        if(appInListApps.size() != 0) {
            waitForElementClickable(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,appName);
            clickToElement(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,appName);
            log.info("App "+appName+" was available!!");
        }
        else{
            log.info("App "+appName+" not found!!");
        }
    }

    @Step("Uninstall app {0}")
    public void uninstallApp(String appName) {
        openSettingsMenu();
        openListAppsInSettingMenu();
        List<WebElement> appInListApps = getListWebElement(driver,HomePageAdminUI.APP_NAME_IN_NAVIGATION, appName);
        if(appInListApps.size() != 0) {
            checkAvailableApp(appName);

            waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);

            List<WebElement> uninstallPopup = getListWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
            if(uninstallPopup.size() != 0){
                waitForElementVisible(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
                Assert.assertTrue(getWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP).isDisplayed());

                waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
                waitForElementAttributeChange(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP,"aria-disabled","false");
                clickToElement(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
                Assert.assertTrue(getWebElement(driver, HomePageAdminUI.UNINSTALL_MESSAGE_SUCCESSFULLY).getText().contains("You've successfully uninstalled"));
            }
            else {
                log.info("Popup uninstall was not opened !!");
            }
        }
        else{
            log.info("App "+appName+" not found!!");
        }
    }

    @Step("Click install app Order Tracking in Shopify admin")
    public DashboardPageOTAppObject clickToInstallApp() {
        List<WebElement> installAppButton = getListWebElement(driver,HomePageAdminUI.INSTALL_APP_POPUP_TITLE);
        if(installAppButton.size() == 0){
            log.info("App was installed!!");
        } else {
            waitForElementClickable(driver, HomePageAdminUI.INSTALL_BTN_IN_INSTALL_APP_POPUP);
            clickToElement(driver, HomePageAdminUI.INSTALL_BTN_IN_INSTALL_APP_POPUP);
        }
        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }

    @Step("Click to Order tab in Shopify admin")
    public OrderPageAdminObject clickToOrdersTab() {
        waitForElementClickable(driver, CreateOrderPageAdminUI.ORDERS_MENU);
        clickToElement(driver, CreateOrderPageAdminUI.ORDERS_MENU);

        return PageGeneratorManager.getOrderPageAdmin(driver);
    }

    @Step("Verify after install app")
    public boolean isAppInstalled(String appName) {
        return isElementDisplayed(driver,HomePageAdminUI.APP_NAME_IN_NAVIGATION, appName);
    }

    @Step("Open Settings page of Returns Drive app")
    public SettingsPageRDAppObject openSettingsPage() {
        waitForElementClickable(driver, HomePageAdminUI.PAGE_RD_IN_NAVIGATION,"Settings");
        clickToElement(driver, HomePageAdminUI.PAGE_RD_IN_NAVIGATION,"Settings");

        return PageGeneratorManager.getSettingsPageRDApp(driver);
    }

    @Step("Open Order Tracking app")
    public DashboardPageOTAppObject openAppOrderTracking(){
        waitForElementClickable(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,"Apps");
        clickToElement(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,"Apps");

        waitForElementClickable(driver, HomePageAdminUI.APP_NAME_IN_SEARCH_APPS, OTConstants.APP_NAME_TEXT_IN_SEARCH_APPS);
        clickToElement(driver, HomePageAdminUI.APP_NAME_IN_SEARCH_APPS,OTConstants.APP_NAME_TEXT_IN_SEARCH_APPS);

        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }

}
