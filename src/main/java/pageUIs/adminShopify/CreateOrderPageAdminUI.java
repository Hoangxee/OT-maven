package pageUIs.adminShopify;

public class CreateOrderPageAdminUI {
    public static final String ORDERS_MENU = "xpath=//a[@href='/store/returns-drive/orders']/span";

    public static final String CREATE_ORDER_BTN ="xpath=//a[@href='/store/returns-drive/draft_orders/new']/span";

    public static final String BROWSE_BTN = "xpath=//span[text()='Browse']/parent::button";

    public static final String SEARCH_PRODUCT_INPUT_IN_SELECT_PRODUCTS_POPUP = "xpath=//div[@class='Polaris-Modal-Dialog']//input[@name='productPicker']";

    public static final String PRODUCT_IN_SEARCH_PRODUCT_POPUP = "xpath=//div[@class='Polaris-BlockStack']//span[text()='%s']";

    public static final String ADD_PRODUCT_BTN = "xpath=//span[text()='Add']/parent::button";

    public static final String COLLECT_PAYMENT_BTN = "xpath=//span[text()='Collect payment']/parent::button";

    public static final String COLLECT_PAYMENT_ITEM = "xpath=//span[text()='%s']/ancestor::button";

    public static final String CUSTOMER_INPUT = "xpath=//h2[text()='Customer']/parent::div/following-sibling::div//input";

    public static final String CUSTOMER_EMAIL_ITEM = "xpath=//span[text()='%s']";

    public static final String CREATE_ORDER_BTN_IN_MARK_AS_PAID = "xpath=//span[text()='Create order']/parent::button";

    public static final String TIMELINE_LABEL = "xpath=//div[@class='Polaris-Layout']//h2[text()='Timeline']";

}
