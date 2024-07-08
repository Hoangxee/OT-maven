package pageObject.shopify.storeFront;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.storeFront.HomePagePortalUI;

public class HomePagePortalObject extends BasePage {
    private WebDriver driver;

    public HomePagePortalObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click to Searchbar")
    public void clickToSearchIcon() {
        waitForElementClickableByIndex(driver, HomePagePortalUI.SEARCH_ICON);
        clickToElement(driver, HomePagePortalUI.SEARCH_ICON);
    }

    @Step("Enter to Searchbar with value is {0}")
    public void inputToSearchbar(String value) {
        waitForElementVisible(driver, HomePagePortalUI.SEARCH_INPUT);
        sendKeyToElement(driver, HomePagePortalUI.SEARCH_INPUT, value);
    }

    @Step("Open to product detail")
    public void openToProductDetail() {
        waitForElementClickableByIndex(driver, HomePagePortalUI.PRODUCT_IN_SEARCH_RESULT);
        clickToElement(driver, HomePagePortalUI.PRODUCT_IN_SEARCH_RESULT);
    }

    @Step("Open to Checkout screen")
    public CheckoutPagePortalObject clickToBuyItNowButton() {
        waitForElementClickableByIndex(driver, HomePagePortalUI.BUY_IT_NOW_BUTTON);
        clickToElement(driver, HomePagePortalUI.BUY_IT_NOW_BUTTON);
        return PageGeneratorManager.getCheckoutPageStoreFront(driver);
    }
}
