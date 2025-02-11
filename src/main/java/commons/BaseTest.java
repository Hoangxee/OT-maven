package commons;

import commons.constant.GlobalConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    WebDriver driver;
    protected final Log log;
    String osName = System.getProperty("os.name");
    Capabilities caps;
    String projectPath = System.getProperty("user.dir");

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllFileInFolder();
    }

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String appURL) {
        BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                //auto download
                Map<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE);

                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions1.addArguments("--lang-vi"); //change default language
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
                chromeOptions.addArguments("--incognito"); // ẩn danh
                chromeOptions.addArguments("disable-features=DownloadBubble,DownloadBubbleV2"); // download file with incognito
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(chromeOptions);

//                driver = new ChromeDriver();
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;

            case EDGE:
                driver = new EdgeDriver();
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;

            case HEADLESS_CHROME:
//                //old version - good for gitlab, jenkins and almalinux
//                ChromeOptions headlessChromeOptions = new ChromeOptions();
//                headlessChromeOptions.addArguments("--no-sandbox");
//                headlessChromeOptions.addArguments("--headless");
//                headlessChromeOptions.addArguments("--window-size=1920,1080");
//                headlessChromeOptions.addArguments(
//                        "user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.6943.53 Safari/537.36'");
//                headlessChromeOptions.addArguments("--disable-dev-shm-usage");
//                headlessChromeOptions.addArguments("--disable-web-security");
//                headlessChromeOptions.addArguments("disable-infobars");
//
//                driver = new ChromeDriver(headlessChromeOptions);

//                //new version
//                ChromeOptions headlessChromeOptions = new ChromeOptions();
//                headlessChromeOptions.addArguments("--headless");
//                headlessChromeOptions.addArguments("window-size=1920,1080");
//
//                driver = new ChromeDriver(headlessChromeOptions);

                ChromeOptions headlessChromeOptions = new ChromeOptions();
                headlessChromeOptions.addArguments("--no-sandbox");
                headlessChromeOptions.addArguments("--headless");
                headlessChromeOptions.addArguments("--disable-dev-shm-usage");
//                headlessChromeOptions.addArguments("--window-size=1920,1080");
//                headlessChromeOptions.addArguments(
//                        "user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.6943.53 Safari/537.36'");
//                headlessChromeOptions.addArguments("--disable-web-security");
//                headlessChromeOptions.addArguments("disable-infobars");

                driver = new ChromeDriver(headlessChromeOptions);

                break;

            case HEADLESS_FIREFOX:
                FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
                headlessFirefoxOptions.addArguments("--headless");
                headlessFirefoxOptions.addArguments("window-size=1920,1080");
                driver = new FirefoxDriver(headlessFirefoxOptions);
                break;

            case HEADLESS_EDGE:
                EdgeOptions headlessEdgeOptions = new EdgeOptions();
                headlessEdgeOptions.addArguments("--headless");
                headlessEdgeOptions.addArguments("window-size=1920,1080");
                driver = new EdgeDriver(headlessEdgeOptions);
                break;

            default:
                throw new RuntimeException("Browser name is not valid!!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.MEDIUM_TIMEOUT));
        // driver.manage().timeouts().implicitlyWait(GlobalConstants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected void deleteAllFileInFolder() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
            File folder = new File(pathFolderDownload);

            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Folder " + pathFolderDownload + " does not exist.");
                return;
            }

            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print("Not found "+e.getMessage());
        }
    }

    protected void killDriverAfterRunFail() throws IOException {
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
    }

//    public void getChromeInfo(){
////         //DesiredCapabilities only work with selenium 3x
//////         log chromedriver version
//         caps = DesiredCapabilities.chrome();
//         caps = ((RemoteWebDriver) driver).getCapabilities();
//         Map<String, String> a = (Map<String, String>) caps.getCapability("chrome");
//         System.out.println(String.format("Driver Version: %s",
//         a.get("chromedriverVersion")));
//
//         //log chrome browser version
//        caps = ((RemoteWebDriver) driver).getCapabilities();
//        String browserName = caps.getBrowserName().toLowerCase();
//        System.out.println(browserName);
//        String browserVersion = caps.getBrowserVersion();
//        System.out.println(browserVersion);
//    }

}
