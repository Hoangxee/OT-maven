package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.adminShopify.DashboardPageOTAppUI;
import pageUIs.adminShopify.HomePageAdminUI;

import java.util.List;

public class HomePageAdminObject extends BasePage {
    private WebDriver driver;

    public HomePageAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Open Settings menu in Shopify admin")
    public void openSettingsMenu() {
        waitForElementClickableByIndex(driver, HomePageAdminUI.SETTING_ICON);
        clickToElement(driver, HomePageAdminUI.SETTING_ICON);
    }

    @Step("Open List apps in Shopify admin")
    public void openListAppsInSettingMenu() {
        waitForElementClickableByIndex(driver, HomePageAdminUI.LIST_APP_IN_SHOPIFY);
        clickToElement(driver, HomePageAdminUI.LIST_APP_IN_SHOPIFY);
    }

    @Step("Check available app {0}")
    public void checkAvailableApp(String appName) {
        List<WebElement> appInListApps = getListWebElement(driver,HomePageAdminUI.APP_NAME_IN_NAVIGATION, appName);
        if(appInListApps.size() != 0) {
            waitForElementClickableByIndex(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,appName);
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

            waitForElementClickableByIndex(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);

            List<WebElement> uninstallPopup = getListWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
            if(uninstallPopup.size() != 0){
                waitForElementVisible(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
                Assert.assertTrue(getWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP).isDisplayed());

                waitForElementClickableByIndex(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
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
        List<WebElement> installAppButton = getListWebElement(driver,HomePageAdminUI.INSTALL_APP_BUTTON);
        if(installAppButton.size() == 0){
            log.info("App was installed!!");
        } else {
            waitForElementClickableByIndex(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
        }
        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }

    @Step("Click to Order tab in Shopify admin")
    public OrderPageAdminObject clickToOrdersTab() {
        waitForElementClickableByIndex(driver, CreateOrderPageAdminUI.ORDERS_MENU);
        clickToElement(driver, CreateOrderPageAdminUI.ORDERS_MENU);

        return PageGeneratorManager.getOrderPageAdmin(driver);
    }

    @Step("Verify after install app")
    public boolean isAppInstalled(String appName) {
        return isElementDisplayed(driver,HomePageAdminUI.APP_NAME_IN_NAVIGATION, appName);
    }

    @Step("Open Settings page of Returns Drive app")
    public SettingsPageRDAppObject openSettingsPage() {
        waitForElementClickableByIndex(driver, HomePageAdminUI.PAGE_RD_IN_NAVIGATION,"Settings");
        clickToElement(driver, HomePageAdminUI.PAGE_RD_IN_NAVIGATION,"Settings");

        return PageGeneratorManager.getSettingsPageRDApp(driver);
    }

    @Step("Open Order Tracking app")
    public DashboardPageOTAppObject openAppOrderTracking(){
        waitForElementClickableByIndex(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,"Apps");
        clickToElement(driver, HomePageAdminUI.APP_NAME_IN_NAVIGATION,"Apps");

        waitForElementClickableByIndex(driver, HomePageAdminUI.APP_NAME_IN_SEARCH_APPS,"tracking-order-now");
        clickToElement(driver, HomePageAdminUI.APP_NAME_IN_SEARCH_APPS,"tracking-order-now");

        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }
}
