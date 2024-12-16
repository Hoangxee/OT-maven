package pageUIs.apps.OT;

public class SettingsPageOTAppUI {
    public static final String TITLE_PAGE_IN_SETTINGS_PAGE = "xpath=//div[@class='custom-card-header']/h6";
    public static final String SAVE_BTN = "xpath=//div[@class='custom-card-header']/button";
    public static final String MESSAGE_TOAST = "xpath=//span[text()='%s']";
    public static final String MENU_BTN_IN_SETTINGS_PAGE = "xpath=//button[descendant::b[text()='%s']]";
    public static final String REPLACE_COURIER_LINK_CHECKBOX_IN_TRACKING_LINK_SETUP = "xpath=//span[@class='Polaris-Checkbox' and child::input[@id='replaceCourierLink']]";
    public static final String REPLACE_COURIER_LINK_STATUS_IN_TRACKING_LINK_SETUP = "xpath=//span[@class='Polaris-Checkbox']/input[@id='replaceCourierLink']";
    public static final String ADD_LINK_TO_ORDER_CHECKBOX_IN_TRACKING_LINK_SETUP = "xpath=//span[@class='Polaris-Checkbox' and child::input[@id='addLinkToOrder']]";
    public static final String ADD_LINK_TO_ORDER_STATUS_IN_TRACKING_LINK_SETUP = "xpath=//span[@class='Polaris-Checkbox']/input[@id='addLinkToOrder']";
    public static final String LINK_DESCRIPTION_INPUT_IN_TRACKING_LINK_SETUP = "xpath=//input[@id='linkDescription']";
    public static final String COURIER_LIST_DROPDOWN = "xpath=//div[descendant::label[text()='%s']]/following-sibling::div[@class='Polaris-Connected']//input";
    public static final String COURIER_LIST_OPTIONS = "xpath=//li[descendant::div[text()='%s']]";
    public static final String DELETE_BTN_IN_FREQUENTLY_USED_COURIERS = "xpath=//div[@class='list-sortable']//button";
    public static final String COURIER_CHOSE_IN_FREQUENTLY_USED_COURIERS = "xpath=//div[@class='list-sortable']/div/div[text()='%s']";
    public static final String ADD_BTN_IN_COURIER_MAPPING = "xpath=//div/following-sibling::div[@class='courier-mapping']//button";
    public static final String LIST_COURIER_MAPPING = "xpath=//div[@class='group-btn']/preceding-sibling::div//input";
    public static final String CHANGE_OR_DELETE_BTN_IN_COURIER_MAPPING = "xpath=//div[@class='Polaris-BlockStack']/div/div[@class='courier-mapping']//button[%s]";
    public static final String CONFIRM_BTN_IN_DELETE_COURIER_MAPPING_POPUP = "xpath=//button[descendant::span[text()='%s']]";
}
