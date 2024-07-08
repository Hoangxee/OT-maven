import com.shopify.common.CreateOrderInShopify;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.shopify.admin.*;
import pageObject.shopify.storeFront.TrackingResultPageObject;

public class E2E_OrderTracking extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.SHOPIFY_ADMIN_URL);

        loginPage = PageGeneratorManager.getLoginPageAdmin(driver);

    }

    @Description("Get old order")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getOldOrder(){
        homePage = loginPage.loginToShopifyAdmin(GlobalConstants.SHOPIFY_ADMIN_EMAIL,
                GlobalConstants.SHOPIFY_ADMIN_PASSWORD);
        dashboardOT = homePage.openAppOrderTracking();
        Assert.assertTrue(dashboardOT.isPlanStarter());
        dashboardOT.getOldOrder();
        shipmentOT = dashboardOT.openShipmentPage();
        Assert.assertTrue(shipmentOT.hadOldOrderBeenGetToShipment(CreateOrderInShopify.orderName,CreateOrderInShopify.orderID,GlobalConstants.TRACKING_NUMBER));
        orderPage = shipmentOT.clickToOrderID(CreateOrderInShopify.orderName);
        shipmentOT = orderPage.verifyOrderInShopifyAdmin(CreateOrderInShopify.orderName,CreateOrderInShopify.orderID,GlobalConstants.SHOPIFY_ADMIN_EMAIL,GlobalConstants.TRACKING_NUMBER);
        trackingResultInStoreFront = shipmentOT.clickToTrackingNo(CreateOrderInShopify.orderName);
        Assert.assertTrue(trackingResultInStoreFront.isTrackingResultCorrect(CreateOrderInShopify.orderName,GlobalConstants.TRACKING_NUMBER));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    HomePageAdminObject homePage;
    LoginPageAdminObject loginPage;
    DashboardPageOTAppObject dashboardOT;
    OrderPageAdminObject orderPage;
    TrackingResultPageObject trackingResultInStoreFront;
    ShipmentPageOTAppObject shipmentOT;
}
