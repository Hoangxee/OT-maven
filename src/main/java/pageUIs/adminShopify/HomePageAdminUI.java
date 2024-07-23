package pageUIs.adminShopify;

public class HomePageAdminUI {
    public static final String SETTING_ICON = "xpath=//a[contains(@href,'settings')]";
    public static final String LIST_APP_IN_SHOPIFY = "xpath=//span[text()='Apps and sales channels']";
    public static final String APP_NAME_IN_NAVIGATION = "xpath=//span[contains(text(),'%s')]";
    public static final String UNINSTALL_APP_BUTTON = "xpath=//button//span[text()='Uninstall app']";
    public static final String UNINSTALL_APP_POPUP = "xpath=//div[contains(@class,'Polaris-Modal-Dialog')]//div[@role='dialog']";
    public static final String UNINSTALL_BUTTON_IN_POPUP = "xpath=//div[contains(@class,'Polaris-Modal-Dialog')]//span[text()='Uninstall']/parent::button";
    
    static String messageSuccessfully = "\"You've successfully uninstalled\"";
    public static final String UNINSTALL_MESSAGE_SUCCESSFULLY = "xpath=//h2[contains(text(),"+messageSuccessfully+")]";

    public static final String INSTALL_APP_POPUP_TITLE = "xpath=//div/h2[text()='Install app']";

    public static final String INSTALL_APP_TITLE = "xpath=//h1[@class='Polaris-Header-Title']/span";

    public static final String INSTALL_BTN = "xpath=//button[@id='proceed_cta']";

    public static final String PAGE_RD_IN_NAVIGATION = "xpath=//span[text()='%s')]";

    public static final String APP_NAME_IN_SEARCH_APPS = "xpath=//a[contains(@href,'%s')]";

}
