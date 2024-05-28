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


}
