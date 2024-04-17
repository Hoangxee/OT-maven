package commons;

import org.openqa.selenium.WebDriver;
import pageObject.shopify.admin.*;
import pageObject.shopify.storeFront.CheckoutPagePortalObject;
import pageObject.shopify.storeFront.HomePagePortalObject;
import pageObject.shopify.storeFront.LoginPagePortalObject;

public class PageGeneratorManager extends BasePage {
    public static HomePagePortalObject getHomePageStoreFront(WebDriver driver){
        return new HomePagePortalObject(driver);
    }

    public static CheckoutPagePortalObject getCheckoutPageStoreFront(WebDriver driver){
        return new CheckoutPagePortalObject(driver);
    }

    public static LoginPagePortalObject getLoginPageStoreFront(WebDriver driver){
        return new LoginPagePortalObject(driver);
    }

    public static LoginPageAdminObject getLoginPageAdmin(WebDriver driver){
        return new LoginPageAdminObject(driver);
    }

    public static HomePageAdminObject getHomePageAdmin(WebDriver driver){
        return new HomePageAdminObject(driver);
    }

    public static OrderPageAdminObject getOrderPageAdmin(WebDriver driver){
        return new OrderPageAdminObject(driver);
    }

    public static HomePageAppStoreObject getHomePageAppStore(WebDriver driver){
        return new HomePageAppStoreObject(driver);
    }

    public static SearchResultPageAppStoreObject getSearchResultPageAppStore(WebDriver driver){
        return new SearchResultPageAppStoreObject(driver);
    }

    public static DetailAppPageAppStoreObject getDetailAppPageAppStore(WebDriver driver){
        return new DetailAppPageAppStoreObject(driver);
    }

    public static DashboardPageOTAppObject getDashboardPageOTApp(WebDriver driver){
        return new DashboardPageOTAppObject(driver);
    }
}
