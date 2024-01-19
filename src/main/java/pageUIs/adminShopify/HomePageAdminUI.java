package pageUIs.adminShopify;

public class HomePageAdminUI {
    public static final String SETTING_ICON = "xpath=//span[text()='Settings']/parent::a";
    public static final String LIST_APP_MENU = "xpath=//span[text()='Apps and sales channels']";
    public static final String APP_DETAIL = "xpath=//span[contains(text(),'%s')]";
    public static final String UNINSTALL_APP_BUTTON = "xpath=//button//span[text()='Uninstall app']";
    public static final String UNINSTALL_APP_POPUP = "xpath=//div[contains(@class,'Polaris-Modal-Dialog')]//div[@role='dialog']";
    public static final String UNINSTALL_BUTTON_IN_POPUP = "xpath=//div[contains(@class,'Polaris-Modal-Dialog')]//span[text()='Uninstall']/parent::span/parent::button";
    
    static String messageSuccessfully = "\"You've successfully uninstalled Omega - Order Tracking\"";
    public static final String UNINSTALL_MESSAGE_SUCCESSFULLY = "xpath=//h2[text()="+messageSuccessfully+"]";

    public static final String INSTALL_APP_BUTTON = "xpath=//button[@id='proceed_cta']";

}
