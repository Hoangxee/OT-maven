package pageObject.apps.OT;

import commons.BasePage;
import commons.PageGeneratorManager;
import commons.constant.OTConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.apps.OT.DashboardPageOTAppUI;

public class SettingsPageOTAppObject extends BasePage {
    private WebDriver driver;

    public SettingsPageOTAppObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("")
    public boolean isPlanStarter(String planName, String quota) {
        switchToFrameIframe(driver, DashboardPageOTAppUI.APP_IFRAME);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1");
        boolean planBL = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"1").equals(planName);

        waitForElementVisible(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2");
        boolean quotaBl = getTextInElement(driver, DashboardPageOTAppUI.INFORMATION_PLAN,"2").contains(quota);
        return planBL && quotaBl;
    }

}
