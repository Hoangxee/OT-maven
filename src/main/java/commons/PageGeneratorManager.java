package commons;

import org.openqa.selenium.WebDriver;
import pageObject.apps.OT.DashboardPageOTAppObject;
import pageObject.apps.OT.SettingsPageOTAppObject;
import pageObject.apps.OT.ShipmentPageOTAppObject;
import pageObject.apps.OT.SubscriptionPlansOTAppObject;
import pageObject.apps.RD.SettingsPageRDAppObject;
import pageObject.apps.ST.*;
import pageObject.apps.ST.paypal.LoginPagePaypalObject;
import pageObject.shopify.admin.*;
import pageObject.shopify.storeFront.CheckoutPagePortalObject;
import pageObject.shopify.storeFront.HomePagePortalObject;
import pageObject.shopify.storeFront.LoginPagePortalObject;
import pageObject.shopify.storeFront.TrackingResultPageObject;
import pageObject.apps.store.DetailAppPageAppStoreObject;
import pageObject.apps.store.HomePageAppStoreObject;
import pageObject.apps.store.SearchResultPageAppStoreObject;

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

    public static SettingsPageRDAppObject getSettingsPageRDApp(WebDriver driver){
        return new SettingsPageRDAppObject(driver);
    }

    public static ShipmentPageOTAppObject getShipmentPageOTApp(WebDriver driver) {
        return new ShipmentPageOTAppObject(driver);
    }

    public static TrackingResultPageObject getTrackingResultPageStore(WebDriver driver) {
        return new TrackingResultPageObject(driver);
    }

    public static SubscriptionPlansOTAppObject getSubscriptionPlansOTApp(WebDriver driver) {
        return new SubscriptionPlansOTAppObject(driver);
    }

    public static ApproveSubscriptionAdminObject getApproveSubscriptionAdmin(WebDriver driver) {
        return new ApproveSubscriptionAdminObject(driver);
    }

    public static HomePageSTAppObject getHomePageSTApp(WebDriver driver) {
        return new HomePageSTAppObject(driver);
    }

    public static LoginPagePaypalObject getLoginPagePaypal(WebDriver driver) {
        return new LoginPagePaypalObject(driver);
    }

    public static OrdersPageSTAppObject getOrdersPageSTAppObject(WebDriver driver) {
        return new OrdersPageSTAppObject(driver);
    }

    public static SettingsPageSTAppObject getSettingsPageSTAppObject(WebDriver driver) {
        return new SettingsPageSTAppObject(driver);
    }

    public static SubscriptionPlansPageSTAppObject getSubscriptionPlansPageSTAppObject(WebDriver driver) {
        return new SubscriptionPlansPageSTAppObject(driver);
    }

    public static ApproveSubscriptionAdminObject getApproveSubscriptionAdminObject(WebDriver driver) {
        return new ApproveSubscriptionAdminObject(driver);
    }

    public static HomePageSTAppObject getHomePageSTAppObject(WebDriver driver) {
        return new HomePageSTAppObject(driver);
    }

    public static DisputePageSTAppObject getDisputePageSTAppObject(WebDriver driver) {
        return new DisputePageSTAppObject(driver);
    }

    public static SettingsPageOTAppObject getSettingsPageOTApp(WebDriver driver) {
        return new SettingsPageOTAppObject(driver);
    }
}
