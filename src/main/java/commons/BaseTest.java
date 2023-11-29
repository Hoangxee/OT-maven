package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import java.io.File;
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
                // driver = WebDriverManager.chromedriver().create();

                // WebDriverManager.chromedriver().driverVersion("116.0.5845.96").setup();
                //
                // ChromeOptions options = new ChromeOptions();
                // options.addArguments("--remote-allow-origins=*");
                // options.addArguments("--start-maximized");
                //
                // driver = new ChromeDriver(options);

                // //change local driver
                // if(osName.contains("linux")||osName.contains("Linux")){
                // System.out.println(osName);
                // System.out.println(projectPath);
                //// System.setProperty("webdriver.chrome.driver",
                // "/usr/lib64/chromium-browser/chromedriver");
                // System.setProperty("webdriver.chrome.driver", projectPath+
                // "/browserDrivers/chromedriver");
                // System.out.println("osName: Linux!");
                // } else if(osName.contains("windows")||osName.contains("Windows")){
                // System.setProperty("webdriver.chrome.driver", projectPath+
                // "/browserDrivers/chromedriver.exe");
                // System.out.println("osName: Windows!");
                // }
                //
                // ChromeOptions options = new ChromeOptions();
                // options.setBinary("/opt/google/chrome/chrome");
                // options.addArguments("--no-sandbox");
                // options.addArguments("--headless");
                // options.addArguments("--disable-dev-shm-usage");
                // options.addArguments("start-maximized");
                // options.addArguments("disable-infobars");
                //// options.setBinary("/usr/bin/google-chrome/chrome");
                // options.addArguments("start-maximized");
                // options.setExperimentalOption("excludeSwitches",
                // Collections.singletonList("enable-automation"));
                // options.setExperimentalOption("useAutomationExtension", false);

                // DesiredCapabilities only work with selenium 3x
                // log chromedriver version
                // caps = DesiredCapabilities.chrome();
                // caps = ((RemoteWebDriver) driver).getCapabilities();
                // Map<String, String> a = (Map<String, String>) caps.getCapability("chrome");
                // System.out.println(String.format("Driver Version: %s",
                // a.get("chromedriverVersion")));
                //
                // //log chrome browser version
                // String browserVersion = caps.getVersion().toString();
                // System.out.println("Browser version: " + browserVersion);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--headless");
                // options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("start-maximized");
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        // driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT,
        // TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected int getRandomTime() {
        return new Random().nextInt(999999);
    }

    public void deleteAllFileInFolder() {
        try {
            String workingDir = System.getProperty("user.dir");
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
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
}
