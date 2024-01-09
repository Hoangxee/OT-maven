package com.shopify.admin;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.admin.*;

public class UninstallApp extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
    }

    @Description("Uninstall app")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void UninstallApp() {
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        homePage.uninstallApp("Order Tracking");

    }

    @AfterClass
    public void afterClass() {
         driver.quit();
    }

    private WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
}
