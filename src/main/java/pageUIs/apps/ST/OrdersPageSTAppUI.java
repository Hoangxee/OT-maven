package pageUIs.apps.ST;

public class OrdersPageSTAppUI {
    public static final String PROCESS_OLD_ORDERS = "xpath=//button[descendant::span[text()='Process old orders']]";
    public static final String PROCESS_OLD_ORDERS_POPUP_BTN = "xpath=//div[@class='Polaris-InlineStack']/button[descendant::span[text()='%s']]";
    public static final String PROCESS_OLD_ORDERS_POPUP_TITLE = "xpath=//div[@class='Polaris-InlineStack']/h2";
    public static final String MESSAGE_TOAST = "xpath=//span[text()='%s']";
    public static final String ORDER_NAME_IN_TABLE = "xpath=//div[@class='Polaris-IndexTable']//button[text()='%s']";
    public static final String TRACKING_NUMBER_IN_TABLE = "xpath=//div[@class='Polaris-IndexTable']//span[text()='%s']";
    public static final String SEARCH_FILTER_SELECTED = "xpath=//button[child::div[@class='Polaris-InlineStack']]";
    public static final String CLEAR_ALL_SEARCH_FILTER = "xpath=//button/span[text()='Clear all']";
    public static final String ORDERS_SECONDARY_BTN = "xpath=//button[child::span[text()='%s']]";
    public static final String IMPORT_ORDERS_POPUP = "xpath=//div[@class='Polaris-Modal-Dialog'][@role='dialog']";
    public static final String BUTTON_IN_EXPORT_OR_IMPORT_POPUP = "xpath=//div[@class='Polaris-InlineStack']//button[child::span[text()='%s']]";
    public static final String INPUT_FILE_IN_IMPORT_ORDERS_POPUP = "xpath=//input[@id='upload-csv']";
    public static final String FILE_UPLOAD_IN_IMPORT_ORDERS_POPUP = "xpath=//div[@class='Polaris-Box']//h6";
    public static final String ORDERS_IN_TABLE = "xpath=//tr[descendant::button[text()='%s']][descendant::span[text()='%s']]";
    public static final String SEARCH_BAR_IN_TABLE = "xpath=//div[@class='Polaris-Connected']//input";
    public static final String EXPORT_ORDERS_POPUP = "xpath=//div[@class='Polaris-InlineGrid']/div/h2";
}
