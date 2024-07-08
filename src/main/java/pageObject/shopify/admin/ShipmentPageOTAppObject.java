package pageObject.shopify.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.shopify.storeFront.TrackingResultPageObject;
import pageUIs.adminShopify.DashboardPageOTAppUI;
import pageUIs.adminShopify.ShipmentPageOTAppUI;

import java.util.List;

public class ShipmentPageOTAppObject extends BasePage {
    private WebDriver driver;

    public ShipmentPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Verify old order had been get to Shipment page")
    public boolean hadOldOrderBeenGetToShipment(String orderName, String orderID, String trackingNo) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);
        String trackingNum = null;
        List<WebElement> orderNames = getListWebElement(driver, ShipmentPageOTAppUI.LIST_ORDERS_BY_ORDER_NAME);
        for (WebElement order:orderNames) {
            if (order.getText().equals(orderName)){
                log.info(order.getText()+"is available");
                waitForElementVisible(driver, ShipmentPageOTAppUI.TRACKING_NO_BY_ORDER_NAME, orderName);
                trackingNum = getTextInElement(driver, ShipmentPageOTAppUI.TRACKING_NO_BY_ORDER_NAME,orderName);

                waitForElementVisible(driver, ShipmentPageOTAppUI.ORDER_ID_BY_ORDER_NAME, orderName);
                Assert.assertEquals(getTextInElement(driver, ShipmentPageOTAppUI.ORDER_ID_BY_ORDER_NAME,orderName), orderID);
            }
            else {
                log.info(order.getText()+"is not available");
            }
        }
        return trackingNum.equals(trackingNo);
    }

    @Step("Open Detail order page in Shopify admin")
    public OrderPageAdminObject clickToOrderID(String orderName) {
        waitForElementClickableByIndex(driver, ShipmentPageOTAppUI.ORDER_ID_BY_ORDER_NAME, orderName);
        clickToElement(driver, ShipmentPageOTAppUI.ORDER_ID_BY_ORDER_NAME, orderName);

        return PageGeneratorManager.getOrderPageAdmin(driver);
    }

    @Step("Open Tracking result in store front")
    public TrackingResultPageObject clickToTrackingNo(String orderName) {
//        hoangxe-test-3 · Synctrack Order Tracking · Shopify
        switchToWindowByTitleContains(driver, "Synctrack Order Tracking · Shopify");
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementClickableByIndex(driver, ShipmentPageOTAppUI.TRACKING_NO_BY_ORDER_NAME,orderName);
        clickToElement(driver, ShipmentPageOTAppUI.TRACKING_NO_BY_ORDER_NAME,orderName);

        return PageGeneratorManager.getTrackingResultPageStore(driver);
    }


}
