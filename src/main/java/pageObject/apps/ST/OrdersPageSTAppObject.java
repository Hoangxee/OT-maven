package pageObject.apps.ST;

import commons.BasePage;
import commons.STConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.OrdersPageSTAppUI;

public class OrdersPageSTAppObject extends BasePage {
    private WebDriver driver;

    public OrdersPageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Process old orders")
    public void processOldOrders() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS);
        clickToElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS);

        Assert.assertEquals(getTextInElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_TITLE), STConstants.PROCESS_OLD_ORDERS_POPUP_TITLE);

        waitForElementClickable(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_BTN,"OK");
        clickToElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_BTN,"OK");

        waitForElementVisible(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_MESSAGE_SUCCESS);
        Assert.assertEquals(getTextInElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_MESSAGE_SUCCESS), STConstants.PROCESS_OLD_ORDERS_PROCESS_SUCCESSFULLY_MESSAGE);
    }
}
