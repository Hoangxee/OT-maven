package pageObject.apps.ST;

import commons.BasePage;
import commons.constant.ST_OrdersPageConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pageUIs.apps.ST.HomePageSTAppUI;
import pageUIs.apps.ST.OrdersPageSTAppUI;

import java.util.List;

public class OrdersPageSTAppObject extends BasePage {
    private WebDriver driver;
    Actions action;

    public OrdersPageSTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Process old orders")
    public void processOldOrders() {
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS);
        clickToElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS);

        Assert.assertEquals(getTextInElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_TITLE), ST_OrdersPageConstants.PROCESS_OLD_ORDERS_POPUP_TITLE);

        waitForElementClickable(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_BTN,"OK");
        clickToElement(driver, OrdersPageSTAppUI.PROCESS_OLD_ORDERS_POPUP_BTN,"OK");

//        Assert.assertTrue(isElementDisplayed(driver, OrdersPageSTAppUI.MESSAGE_TOAST, STConstants.PROCESS_OLD_ORDERS_PROCESS_SUCCESSFULLY_MESSAGE));
        Assert.assertTrue(isListElementDisplayed(driver, OrdersPageSTAppUI.MESSAGE_TOAST, ST_OrdersPageConstants.PROCESS_OLD_ORDERS_PROCESS_SUCCESSFULLY_MESSAGE));
    }

    @Step("Verify Get old orders successful")
    public boolean isOrdersSynced(String orderName, String trackingNumber) {
        List<WebElement> order;
        do {
            refreshCurrentPage(driver);
            switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);
            waitForElementVisible(driver, OrdersPageSTAppUI.SEARCH_FILTER_SELECTED);
            waitForElementClickable(driver, OrdersPageSTAppUI.CLEAR_ALL_SEARCH_FILTER);
            clickToElement(driver, OrdersPageSTAppUI.CLEAR_ALL_SEARCH_FILTER);

            order = getListWebElement(driver, OrdersPageSTAppUI.ORDER_NAME_IN_TABLE, orderName);

        } while (order.isEmpty());

        waitForElementVisible(driver, OrdersPageSTAppUI.ORDER_NAME_IN_TABLE, orderName);
        boolean orderNameResult = getTextInElement(driver, OrdersPageSTAppUI.ORDER_NAME_IN_TABLE, orderName).equals(orderName);

        waitForElementVisible(driver, OrdersPageSTAppUI.TRACKING_NUMBER_IN_TABLE, trackingNumber);
        boolean trackingNumberNameResult = getTextInElement(driver, OrdersPageSTAppUI.TRACKING_NUMBER_IN_TABLE, trackingNumber).equals(trackingNumber);

        return orderNameResult && trackingNumberNameResult;
    }

    @Step("Import orders")
    public void importOrders(String fileUploadPath, String fileUploadName) {
        action = new Actions(driver);
        switchToFrameIframe(driver, HomePageSTAppUI.APP_IFRAME);

        waitForElementClickable(driver, OrdersPageSTAppUI.ORDERS_SECONDARY_BTN,"Import");
        clickToElement(driver, OrdersPageSTAppUI.ORDERS_SECONDARY_BTN,"Import");

        waitForElementVisible(driver, OrdersPageSTAppUI.IMPORT_ORDERS_POPUP);
        Assert.assertTrue(isElementDisplayed(driver, OrdersPageSTAppUI.IMPORT_ORDERS_POPUP));

        waitForElementVisible(driver, OrdersPageSTAppUI.INPUT_FILE_IN_IMPORT_ORDERS_POPUP);
        sendKeyToElement(driver, OrdersPageSTAppUI.INPUT_FILE_IN_IMPORT_ORDERS_POPUP,fileUploadPath);

        Assert.assertTrue(getTextInElement(driver,OrdersPageSTAppUI.FILE_UPLOAD_IN_IMPORT_ORDERS_POPUP).equals(fileUploadName));

        waitForElementClickable(driver, OrdersPageSTAppUI.BUTTON_IN_EXPORT_OR_IMPORT_POPUP,"Upload");
        clickToElement(driver, OrdersPageSTAppUI.BUTTON_IN_EXPORT_OR_IMPORT_POPUP,"Upload");

        Assert.assertTrue(isListElementDisplayed(driver, OrdersPageSTAppUI.MESSAGE_TOAST, ST_OrdersPageConstants.IMPORT_ORDERS_SUCCESSFULLY_MESSAGE));
    }

    @Step("Verify order was imported")
    public boolean verifyOrdersImport(String transactionId) {
        waitForElementVisible(driver, OrdersPageSTAppUI.SEARCH_BAR_IN_TABLE);
        sendKeyToElementAfterClearText(driver, OrdersPageSTAppUI.SEARCH_BAR_IN_TABLE, transactionId);

        return isElementDisplayed(driver, OrdersPageSTAppUI.ORDERS_IN_TABLE,transactionId,getDateTimeNow());
    }

    @Step("Export orders")
    public void exportFileFullOptions() {
        deleteAllFileInFolder();

        waitForElementClickable(driver, OrdersPageSTAppUI.ORDERS_SECONDARY_BTN,"Export");
        clickToElement(driver, OrdersPageSTAppUI.ORDERS_SECONDARY_BTN,"Export");

        waitForElementVisible(driver, OrdersPageSTAppUI.EXPORT_ORDERS_POPUP);
        Assert.assertTrue(isElementDisplayed(driver, OrdersPageSTAppUI.EXPORT_ORDERS_POPUP));

        waitForElementClickable(driver, OrdersPageSTAppUI.BUTTON_IN_EXPORT_OR_IMPORT_POPUP,"Export");
        clickToElement(driver, OrdersPageSTAppUI.BUTTON_IN_EXPORT_OR_IMPORT_POPUP,"Export");
    }

    @Step("Verify file after export")
    public void verifyFileAfterExport() {
        waitForDownloadFileContainsNameCompleted(".xlsx");

        Assert.assertEquals(countFilesInDirectory(), 1); // count file before delete

        deleteFileContainName(".xlsx");

        Assert.assertEquals(countFilesInDirectory(), 0); // count file after delete
    }
}
