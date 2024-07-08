package pageUIs.adminShopify;

public class ShipmentPageOTAppUI {

    public static final String LIST_ORDERS_BY_ORDER_NAME = "xpath=//td/a/preceding-sibling::h6";
    public static final String ORDER_ID_BY_ORDER_NAME = "xpath=//h6[text()='%s']/following-sibling::a";
    public static final String TRACKING_NO_BY_ORDER_NAME = "xpath=//h6[text()='%s']/parent::td/following-sibling::td/a";

}
