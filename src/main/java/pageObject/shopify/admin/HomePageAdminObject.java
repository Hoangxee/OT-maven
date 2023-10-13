package pageObject.shopify.admin;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.adminShopify.HomePageAdminUI;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomePageAdminObject extends BaseTest {
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

    @Step("Uninstall app Order Tracking in Shopify")
    public void uninstallAppOrderTracking() {
        List<WebElement> uninstallButton = getListWebElement(driver,HomePageAdminUI.UNINSTALL_BUTTON);
        if(uninstallButton.size() != 0) {
            waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_BUTTON);
            clickToElement(driver, HomePageAdminUI.UNINSTALL_BUTTON);

            waitForElementVisible(driver, HomePageAdminUI.UNINSTALL_APP_POPUP);
            Assert.assertTrue(getWebElement(driver, HomePageAdminUI.UNINSTALL_APP_POPUP).isDisplayed());

            waitForElementClickable(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
            waitForElementAttributeChange(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP,"aria-disabled","false");
            clickToElement(driver, HomePageAdminUI.UNINSTALL_BUTTON_IN_POPUP);
            Assert.assertEquals(getWebElement(driver, HomePageAdminUI.UNINSTALL_MESSAGE_SUCCESSFULLY).getText(), "You've successfully uninstalled Omega - Order Tracking");
            log.info("------------ PASSED ------------");
        }
        else{
            log.info("------------ FAILED ------------");
        }
    }

    @Step("Click install app Order Tracking in Shopify")
    public DashboardPageOTAppObject clickToInstallApp() {
        List<WebElement> installAppButton = getListWebElement(driver,HomePageAdminUI.INSTALL_APP_BUTTON);
        if(installAppButton.size() == 0){
            System.out.println("App was installed!!");
        } else {
            waitForElementClickable(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
            clickToElement(driver, HomePageAdminUI.INSTALL_APP_BUTTON);
        }
        return PageGeneratorManager.getDashboardPageOTApp(driver);
    }
}
