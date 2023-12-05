package com.shopify.admin;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.admin.*;

public class InstallApp extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_APP_STORE_URL);

        homePageAppStore = PageGeneratorManager.getHomePageAppStore(driver);
    }

    @Description("Install app")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void InstallApp() {
        SearchResultPageAppStore = homePageAppStore.searchApp("order tracking");
        detailAppPageAppStore = SearchResultPageAppStore.clickToApp("Omega Order Tracking");
        loginPage = detailAppPageAppStore.clickToAddAppButton();
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardPageOTApp = homePage.clickToInstallApp();
        dashboardPageOTApp.verifyAfterInstallOrderTracking();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    LoginPageAdminObject loginPage;
    HomePageAdminObject homePage;
    HomePageAppStoreObject homePageAppStore;
    SearchResultPageAppStoreObject SearchResultPageAppStore;
    DetailAppPageAppStoreObject detailAppPageAppStore;
    DashboardPageOTAppObject dashboardPageOTApp;
}
