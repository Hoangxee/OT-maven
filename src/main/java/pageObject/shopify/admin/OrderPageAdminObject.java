package pageObject.shopify.admin;

import commons.BasePage;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.adminShopify.CreateOrderPageAdminUI;

import java.util.List;

public class OrderPageAdminObject extends BasePage {
    private WebDriver driver;

    public OrderPageAdminObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Description("Click Create order button")
    public void clickToCreateOrderBtn(){
        waitForElementClickable(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.CREATE_ORDER_BTN);
    }

    @Description("Choose product {0}")
    public void chooseProduct(String productName){
        waitForElementClickable(driver, CreateOrderPageAdminUI.BROWSE_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.BROWSE_BTN);

        waitForElementVisible(driver, CreateOrderPageAdminUI.SEARCH_PRODUCT_INPUT_IN_SELECT_PRODUCTS_POPUP);
        sendKeyToElement(driver, CreateOrderPageAdminUI.SEARCH_PRODUCT_INPUT_IN_SELECT_PRODUCTS_POPUP,productName);

        waitForElementVisible(driver, CreateOrderPageAdminUI.PRODUCT_IN_SEARCH_PRODUCT_POPUP,productName);
        clickToElement(driver, CreateOrderPageAdminUI.PRODUCT_IN_SEARCH_PRODUCT_POPUP,productName);

        waitForElementClickable(driver, CreateOrderPageAdminUI.ADD_PRODUCT_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.ADD_PRODUCT_BTN);
    }

    @Description("Choose customer {0}")
    public void chooseCustomer(String email){
        waitForProcessBar();
        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_INPUT);

        waitForElementClickable(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
        clickToElement(driver, CreateOrderPageAdminUI.CUSTOMER_EMAIL_ITEM,email);
    }

    @Description("Click to Collect Payment Button")
    public void clickToCollectPaymentBtn(){
        waitForProcessBar();
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_BTN);
    }

    @Description("Choose option Payment {0}")
    public void chooseOptionPayment(String optionName){
        waitForElementClickable(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
        clickToElement(driver, CreateOrderPageAdminUI.COLLECT_PAYMENT_ITEM,optionName);
    }

    @Description("Click to Create order in Mark as paid popup")
    public void clickToCreateOrderInMarkAsPaidPopup(){
        waitForElementVisible(driver, "xpath=//h2[text()='Mark as paid']");
        waitForElementClickable(driver, "xpath=//span[text()='Create order']/parent::button");
        clickToElement(driver, "xpath=//span[text()='Create order']/parent::button");
    }




    public void waitForProcessBar(){
        List<WebElement> progressBarShopify = driver.findElements(By.xpath("//div[@class='Polaris-Frame-Loading']"));
        if(progressBarShopify.size() != 0){
            waitForElementInvisible(driver,"xpath=//div[@class='Polaris-Frame-Loading']");
        }
    }
}
