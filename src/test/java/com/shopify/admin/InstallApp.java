package com.shopify.admin;

import commons.BaseTest;
import commons.constant.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.admin.*;
import pageObject.apps.store.DetailAppPageAppStoreObject;
import pageObject.apps.store.HomePageAppStoreObject;
import pageObject.apps.store.SearchResultPageAppStoreObject;
import utilities.Environment;

public class InstallApp extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_APP_STORE_URL);

        homePageAppStore = PageGeneratorManager.getHomePageAppStore(driver);
    }

    @Parameters({"appName", "appNameInShopifyStore"})
    @Description("Install app")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void InstallApp(String appName, String appNameInShopifyStore) {
        SearchResultPageAppStore = homePageAppStore.searchApp(appNameInShopifyStore);
        detailAppPageAppStore = SearchResultPageAppStore.clickToApp(appNameInShopifyStore);
        loginPage = detailAppPageAppStore.clickToAddAppButton();
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        homePage.clickToInstallApp();
        Assert.assertTrue(homePage.isAppInstalled(appName));
    }

    @AfterClass
    public void afterClass() {
         driver.quit();
    }

    Environment environment;
    private WebDriver driver;
    LoginPageAdminObject loginPage;
    HomePageAdminObject homePage;
    HomePageAppStoreObject homePageAppStore;
    SearchResultPageAppStoreObject SearchResultPageAppStore;
    DetailAppPageAppStoreObject detailAppPageAppStore;
}
