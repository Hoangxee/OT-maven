package com.shopify.apps.ST;

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
import pageObject.apps.ST.HomePageSTAppObject;
import pageObject.apps.ST.OrdersPageSTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;

public class OrdersPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);
        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
    }

    @Description("Import orders")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_importOrders() {
        // shopify polaris ẩn input[type='file'] -> tìm kỹ/ search trong DOM

        homePageST = homePage.openAppSynctrack();
        ordersPageST = homePageST.openOrdersPage();
        ordersPageST.importOrders(ordersFilePath,ordersFileName);
        Assert.assertTrue(ordersPageST.verifyOrdersImport(ordersPageST.getDataFormExcel(ordersFilePath,"Upload OrdersPage","Transaction Id",1)));
        Assert.assertTrue(ordersPageST.verifyOrdersImport(ordersPageST.getDataFormExcel(ordersFilePath,"Upload OrdersPage","Transaction Id",2)));
        }

    @Description("Download orders")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_exportOrders(){
        ordersPageST.exportFileFullOptions();
        ordersPageST.verifyFileAfterExport();
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
    OrdersPageSTAppObject ordersPageST;
    String ordersFileName = "synctrack_upload_orders.xlsx";
    String ordersFilePath = GlobalConstants.UPLOAD_FILE + ordersFileName;
}
