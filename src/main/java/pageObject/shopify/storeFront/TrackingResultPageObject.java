package pageObject.shopify.storeFront;

import commons.BasePage;
import commons.constant.OTConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.apps.OT.SubscriptionPlansOTAppObject;
import pageUIs.apps.OT.DashboardPageOTAppUI;
import pageUIs.storeFront.TrackingResultPageUI;

import java.util.List;

public class TrackingResultPageObject extends BasePage {
    private WebDriver driver;

    public TrackingResultPageObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Verify Tracking result in store front")
    public boolean isTrackingResultCorrect(String orderName, String trackingNumber) {
        switchToWindowByTitle(driver, OTConstants.TRACKING_RESULT_TITLE_PAGE_IN_STORE);
        
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

    @Step("Switch window and open Subscription Plans page")
    public SubscriptionPlansOTAppObject openSubscriptionPlans() {
        switchToWindowByTitleContains(driver, OTConstants.OT_APP_ADMIN_TITLE_PAGE);

        switchToDefaultContent(driver);
        waitForElementClickable(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SUBSCRIPTION_PLANS_PAGES_OT_APP_IN_NAVIGATION);
        clickToElement(driver, DashboardPageOTAppUI.PAGE_OT_IN_NAVIGATE,OTConstants.SUBSCRIPTION_PLANS_PAGES_OT_APP_IN_NAVIGATION);

        return PageGeneratorManager.getSubscriptionPlansOTApp(driver);
    }
}
