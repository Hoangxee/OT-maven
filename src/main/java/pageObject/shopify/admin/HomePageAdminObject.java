package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.Assert;
import pageUIs.adminShopify.CreateOrderPageAdminUI;
import pageUIs.adminShopify.HomePageAdminUI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public void openListApps() {
        waitForElementClickable(driver, HomePageAdminUI.LIST_APP_MENU);
        clickToElement(driver, HomePageAdminUI.LIST_APP_MENU);
    }

    @Step("Check available app {0}")
    public void checkAvailableApp(String appName) {
        List<WebElement> appInListApps = getListWebElement(driver,HomePageAdminUI.APP_DETAIL, appName);
        if(appInListApps.size() != 0) {
            waitForElementClickable(driver, HomePageAdminUI.APP_DETAIL,appName);
            clickToElement(driver, HomePageAdminUI.APP_DETAIL,appName);
            log.info("App "+appName+" was available!!");
        }
        else{
            log.info("App "+appName+" not found!!");
        }
    }

    @Step("Uninstall app {0}")
    public void uninstallApp(String appName) {
        openSettingsMenu();
        openListApps();
        List<WebElement> appInListApps = getListWebElement(driver,HomePageAdminUI.APP_DETAIL, appName);
        if(appInListApps.size() != 0) {
            checkAvailableApp(appName);

            List<WebElement> uninstallPopup = getListWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
            Assert.assertEquals(uninstallPopup.size(), 0);
            waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.UNINSTALL_APP_BUTTON);

            if(uninstallPopup.size() != 0){
                waitForElementVisible(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
                Assert.assertTrue(getWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP).isDisplayed());

                waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
                waitForElementAttributeChange(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP,"aria-disabled","false");
                clickToElement(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
                Assert.assertEquals(getWebElement(driver, HomePageAdminUI.UNINSTALL_MESSAGE_SUCCESSFULLY).getText(), "You've successfully uninstalled Omega - Order Tracking");

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
            waitForElementClickable(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
        }
        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }

    public OrderPageAdminObject clickToOrdersTab() {
        waitForElementClickable(driver, CreateOrderPageAdminUI.ORDERS_MENU);
        clickToElement(driver, CreateOrderPageAdminUI.ORDERS_MENU);

        return PageGeneratorManager.getOrderPageAdmin(driver);
    }
}
