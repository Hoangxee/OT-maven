package com.shopify.apps.ST.SettingsPage;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.STConstants;
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
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.apps.ST.SettingsPageSTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;

public class MultiStoresPage extends BaseTest {

    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);
        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        homePageST = homePage.openAppSynctrack();
        settingsPageST = homePageST.openSettingsPage();
        settingsPageST.openMultiStoresTab();
    }

    @Description("Generate a connection key")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_GenerateConnectionKey() {
        connectionKey = settingsPageST.generateConnectionKey();
        Assert.assertTrue(settingsPageST.isConnectionKeyExclusively(connectionKey));
        settingsPageST.copyConnectionKey();
        settingsPageST.changeConnectionKey();
        settingsPageST.deleteConnectionKey();
    }

    @Description("Connect integrated stores")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_ConnectIntegratedStores() {
        connectionKey = settingsPageST.generateConnectionKey();
        Assert.assertTrue(settingsPageST.isConnectionKeyExclusively(connectionKey));
        homePageST = settingsPageST.clickToAddConnectionBtnInPrimaryStore();

        settingsPageST = homePageST.openSettingsPage();
        settingsPageST.openMultiStoresTab();
        settingsPageST.addExistingKey(connectionKey);
        settingsPageST.verifyIntegratedStoresInPrimaryStore();
    }

    @Description("Disconnect integrated stores")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC03_DisconnectIntegratedStores() {
        settingsPageST.disconnectIntegratedStores();
        settingsPageST.deleteConnectionKey();
        settingsPageST.verifyDisconnectInIntegratedStore();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    HomePageSTAppObject homePageST;
    SettingsPageSTAppObject settingsPageST;
    String connectionKey;
}
