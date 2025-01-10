package com.shopify.apps.OT;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.NotificationsPageOTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import utilities.Environment;


public class NotificationsPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
        notificationsOT = dashboardOT.openNotificationsPage();
    }

    @Description("Email tab in Notifications page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC01_NotificationEmail(){
        notificationsOT.openEmailTab();
        notificationsOT.openEmailAccountTab();
        notificationsOT.chooseNoReply();
        notificationsOT.chooseYourEmailSender(GlobalConstants.SHOPIFY_ADMIN_EMAIL, senderName);

        notificationsOT.openEmailTemplateTab();
        notificationsOT.clickToToCustomer();
        notificationsOT.openEditEmailTemplateByOrderStatus("Information received");
        notificationsOT.enableEmail();
        notificationsOT.sendTextToSubjectInput(subject);
        notificationsOT.saveEmailTemplate();
        notificationsOT.backToEmailTemplate();
    }

    @Description("SMS tab in Notifications page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC02_NotificationSMS(){
        notificationsOT.openSMSTab();
        notificationsOT.openSMSCreditTab();

        //SMS lỗi Add fund tại SMS credit

        notificationsOT.openSMSTemplateTab();
        notificationsOT.enableSendSMSByOrderStatus("Out for delivery");
        notificationsOT.disableSendSMSByOrderStatus("Pending");

        notificationsOT.openSMSHistoryTab();

    }

    @Description("Email tab in Notifications page API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC03_NotificationEmailAPI(){

    }

    @Description("SMS tab in Notifications page API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC04_NotificationSMSAPI(){

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    NotificationsPageOTAppObject notificationsOT;
    String senderName = "returns-drive-test";
    String subject = "Email Template test";

}
