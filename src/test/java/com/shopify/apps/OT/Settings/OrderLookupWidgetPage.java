package com.shopify.apps.OT.Settings;

import commons.BaseTest;
import commons.PageGeneratorManager;
import commons.constant.GlobalConstants;
import commons.constant.OT_SettingsPageConstants;
import endPoints.apps.OT.SettingsEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.SettingsPageOTAppObject;
import pageObject.shopify.admin.HomePageAdminObject;
import pageObject.shopify.admin.LoginPageAdminObject;
import payload.apps.OT.OrderLookupWidgetPayload;
import utilities.Environment;


public class OrderLookupWidgetPage extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        ConfigFactory.setProperty("env",environmentName);
        environment = ConfigFactory.create(Environment.class);

        orderLookupWidgetPayload = new OrderLookupWidgetPayload();
        orderLookupWidgetPayload.setShop(GlobalConstants.SHOP_API);
        orderLookupWidgetPayload.setUrlParams(GlobalConstants.URL_PARAM_API);
        orderLookupWidgetPayload.setWidgetTitle(widgetTitle);
        orderLookupWidgetPayload.setWidgetSize(widgetSize1);
        orderLookupWidgetPayload.setAlignment(alignment1);
        orderLookupWidgetPayload.setIsShowResultInWidget(true);
        orderLookupWidgetPayload.setIsLoading(true);

        trackingForm = new OrderLookupWidgetPayload.TrackingForm();
        trackingForm.setIsRequireEmail(true);
        trackingForm.setTypeMethodTracking(typeMethodTracking1);
        orderLookupWidgetPayload.setTrackingForm(trackingForm);

        buttonStyle = new OrderLookupWidgetPayload.ButtonStyle();
        buttonStyle.setLabel(label);
        buttonStyle.setBackground(backgroundColor);
        buttonStyle.setText(textColor);
        buttonStyle.setShape(buttonShape);
        orderLookupWidgetPayload.setButtonStyle(buttonStyle);

        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
    }

    @Description("Order lookup widget page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void orderLookupWidget(){
        settingsOT = dashboardOT.openPageInSettings(OT_SettingsPageConstants.ORDER_LOOKUP_WIDGET_IN_TRACKING_PAGE);
        settingsOT.sendKeyToWidgetTitle(widgetTitle);
        settingsOT.chooseWidgetTrackingForm(typeMethodTracking);
        settingsOT.chooseWidgetTrackingFormAndClickToRequireEmail(typeMethodTracking2);
        settingsOT.chooseWidgetTrackingFormAndClickToRequireEmail(typeMethodTracking1);
        settingsOT.sendKeyToLabel(label);
        settingsOT.selectOptionInField("Widget size",widgetSize);
        settingsOT.selectOptionInField("Alignment",alignment);
        settingsOT.clickToShowTrackingResultCheckbox();
        settingsOT.saveAndVerifyMessage(OT_SettingsPageConstants.ORDER_LOOKUP_WIDGET_SUCCESSFULLY_MESSAGE);
    }

    @Description("Order lookup widget page by API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void orderLookupWidgetByAPI(){
        Response getOrderLookup = SettingsEndpoints.getOrderLookupWidget(orderLookupWidgetPayload);
        SettingsEndpoints.verifyValueInResponse(getOrderLookup,"msg",OT_SettingsPageConstants.GET_ORDER_LOOKUP_WIDGET_SUCCESSFULLY_MESSAGE_API);
        SettingsEndpoints.verifyValueInResponse(getOrderLookup,"data.shop",GlobalConstants.SHOP_API);

        Response updateOrderLookup = SettingsEndpoints.updateOrderLookupWidget(orderLookupWidgetPayload);
        SettingsEndpoints.verifyValueInResponse(updateOrderLookup,"msg",OT_SettingsPageConstants.ORDER_LOOKUP_WIDGET_SUCCESSFULLY_MESSAGE);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    Environment environment;
    OrderLookupWidgetPayload orderLookupWidgetPayload;
    OrderLookupWidgetPayload.TrackingForm trackingForm;
    OrderLookupWidgetPayload.ButtonStyle buttonStyle;
    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    SettingsPageOTAppObject settingsOT;
    String widgetTitle = "Test widget title" + SettingsPageOTAppObject.random;
    String label = "Test label" + SettingsPageOTAppObject.random;
    String backgroundColor = "#dcad2e";
    String textColor = "#6422dd";
    String buttonShape = "25px";
    String widgetSize1 = "18px"; //large
    String widgetSize = "Small";
    String alignment = "Right";
    String alignment1 = "center";
    String typeMethodTracking = "trackingNumber";
    String typeMethodTracking1 = "both";
    String typeMethodTracking2 = "orderId";

}
