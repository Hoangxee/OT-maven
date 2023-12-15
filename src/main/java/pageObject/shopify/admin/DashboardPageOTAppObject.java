package pageObject.shopify.admin;

import commons.BasePage;
import commons.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.adminShopify.DashboardPageOTAppUI;
import pageUIs.adminShopify.HomePageAdminUI;

import java.util.List;

public class DashboardPageOTAppObject extends BasePage {
    private WebDriver driver;

    public DashboardPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Description("Verify after install app")
    public void verifyAfterInstallOrderTracking(String appName){
        List<WebElement> appInListApps = getListWebElement(driver, HomePageAdminUI.APP_DETAIL, appName);
        if(appInListApps.size() != 0) {
            System.out.println("Install app "+appName+" was successful!!");
        }
        else{
            System.out.println("Install app "+appName+" was failed!!");
        }
    }
}
