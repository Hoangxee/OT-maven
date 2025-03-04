package pageUIs.apps.ST;

public class HomePageSTAppUI {
    public static final String APP_IFRAME = "xpath=//iframe[@name='app-iframe']";
    public static final String SKIP_BTN_IN_ONBOARD = "xpath=//div[@class='Polaris-InlineGrid']//button[child::span[contains(text(),'Skip')]]";
    public static final String INFOMATION_TEXT = "xpath=//div[@class='action-item-container']//p[contains(text(),'%s')]/b";
    public static final String PROGRESS_COUNT_STEP = "xpath=//div[@class='current-step']/p[starts-with(text(),'%s')]";
    public static final String CLOSE_POPUP_BTN = "xpath=//div[@id='PolarisPortalsContainer']//button[@aria-label='Close']";
    public static final String PAGE_IN_NAVIGATE = "xpath=//div[@class='Polaris-Collapsible']//span[text()='%s']";

}
