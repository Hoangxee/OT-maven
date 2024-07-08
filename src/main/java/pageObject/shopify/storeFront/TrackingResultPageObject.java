package pageObject.shopify.storeFront;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.storeFront.TrackingResultPageUI;

import java.util.List;

public class TrackingResultPageObject extends BasePage {
    private WebDriver driver;

    public TrackingResultPageObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    public boolean isTrackingResultCorrect(String orderName, String trackingNumber) {
        switchToWindowByTitle(driver, "Returns drive");
        
        String trackingNum = null;
        List<WebElement> trackingResultList = getListWebElement(driver, TrackingResultPageUI.TRACKING_RESULT_BLOCK);
        if(trackingResultList.size()>1){
            for (int i = 0;i<trackingResultList.size();i++) {
                waitForElementClickableByIndex(driver, TrackingResultPageUI.TRACKING_RESULT_BLOCK, i);
                scrollToElement(driver,TrackingResultPageUI.TRACKING_RESULT_BLOCK,i);
                clickToListElementByIndex(driver,TrackingResultPageUI.TRACKING_RESULT_BLOCK,i);
                waitForElementVisibleByIndex(driver, TrackingResultPageUI.ORDER_NAME_TEXT,i);

                if(getTextInElementByIndex(driver, TrackingResultPageUI.ORDER_NAME_TEXT, i).equals(orderName)){
                    waitForElementVisible(driver, TrackingResultPageUI.TRACKING_NUMBER_TEXT);
                    trackingNum = getTextInElement(driver, TrackingResultPageUI.TRACKING_NUMBER_TEXT);
                    break;
                }
            }
        }
        else if (trackingResultList.size() == 1) {
            waitForElementVisible(driver, TrackingResultPageUI.ORDER_NAME_TEXT);
            Assert.assertEquals(getTextInElement(driver, TrackingResultPageUI.ORDER_NAME_TEXT), orderName);

            waitForElementVisible(driver, TrackingResultPageUI.TRACKING_NUMBER_TEXT);
            trackingNum = getTextInElement(driver, TrackingResultPageUI.TRACKING_NUMBER_TEXT);
        }
        else {
            log.info("---Order not found---");
        }
        return trackingNum.equals(trackingNumber);
    }
}
