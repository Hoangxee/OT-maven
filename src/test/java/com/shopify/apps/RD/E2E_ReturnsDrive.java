package com.shopify.apps.RD;

import com.shopify.admin.InstallApp;
import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.RD.SettingsPageRDAppObject;
import pageObject.shopify.admin.*;
import pageObject.apps.store.DetailAppPageAppStoreObject;
import pageObject.apps.store.HomePageAppStoreObject;
import pageObject.apps.store.SearchResultPageAppStoreObject;

public class E2E_ReturnsDrive extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_APP_STORE_URL);

        homePage = PageGeneratorManager.getHomePageAdmin(driver);

    }

    @Parameters({"appName","appNameInShopifyStore"})
    @Description("")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void createOrderShopify(String appName, String appNameInShopifyStore){
//        installApp.InstallApp(appName,appNameInShopifyStore);
//        settingsPage = homePage.openSettingsPage();
//        settingsPage.clickToGeneralSettingsMenu();
//        settingsPage.enableShopifySync();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    LoginPageAdminObject loginPage;
    HomePageAdminObject homePage;
    OrderPageAdminObject orderPage;
    HomePageAppStoreObject homePageAppStore;
    SearchResultPageAppStoreObject SearchResultPageAppStore;
    DetailAppPageAppStoreObject detailAppPageAppStore;
    DashboardPageOTAppObject dashboardPageOTApp;
    InstallApp installApp;
    SettingsPageRDAppObject settingsPage;
}
