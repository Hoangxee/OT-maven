package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;

import static org.testng.Reporter.log;

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
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
//                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                options.addArguments(
                         "user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.6099.71 Safari/537.36'");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-web-security");
                options.addArguments("disable-infobars");

                driver = new ChromeDriver(options);

                // driver = new ChromeDriver();
                break;

            case FIREFOX:
                // driver = WebDriverManager.firefoxdriver().create();
                driver = new FirefoxDriver();
                break;

            case EDGE:
                if (osName.contains("linux") || osName.contains("Linux")) {
                    System.out.println(osName);
                    System.out.println(projectPath);
                    System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
                    System.out.println("osName: Linux!");
                } else if (osName.contains("windows") || osName.contains("Windows")) {
                    System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
                    System.out.println("osName: Windows!");
                }

                driver = new EdgeDriver();

                break;

            default:
                throw new RuntimeException("Please enter the correct Browser name!");
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

    protected int getRandomTime() {
        return new Random().nextInt(999999);
    }

    protected void deleteAllFileInFolder() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void killDriverAfterRunFail() throws IOException {
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
    }

    public void getChromeInfo(){
//         //DesiredCapabilities only work with selenium 3x
//         log chromedriver version
//         caps = DesiredCapabilities.chrome();
//         caps = ((RemoteWebDriver) driver).getCapabilities();
//         Map<String, String> a = (Map<String, String>) caps.getCapability("chrome");
//         System.out.println(String.format("Driver Version: %s",
//         a.get("chromedriverVersion")));

         //log chrome browser version
        caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName().toLowerCase();
        System.out.println(browserName);
        String os = caps.getBrowserName();
        System.out.println(os);
        String v = caps.getBrowserVersion();
        System.out.println(v);
    }
}
