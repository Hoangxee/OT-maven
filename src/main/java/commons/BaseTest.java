package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Reporter.log;

public class BaseTest {
    WebDriver driver;
    protected final Log log;
    String osName = System.getProperty("os.name");

    @BeforeSuite
    public void initBeforeSuite(){
        deleteAllFileInFolder();
    }
    protected BaseTest(){
        log = LogFactory.getLog(getClass());
    }
    protected WebDriver getBrowserDriver(String browserName, String appURL){
        BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
        switch (browser){
            case CHROME:
//                driver = WebDriverManager.chromedriver().create();
//
                String projectPath = System.getProperty("user.dir");
//                System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");

                if(osName.contains("linux")||osName.contains("Linux")){
                    System.out.println(osName);
                    System.out.println(projectPath);
//                    System.setProperty("webdriver.chrome.driver", "/usr/lib64/chromium-browser/chromedriver");
                    System.setProperty("webdriver.chrome.driver", projectPath+ "/browserDrivers/chromedriver");
                    System.out.println("osName: Linux!");
                } else if(osName.contains("windows")||osName.contains("Windows")){
                    System.setProperty("webdriver.chrome.driver", projectPath+ "/browserDrivers/chromedriver.exe");
                    System.out.println("osName: Windows!");
                }
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("start-maximized");
//                options.addArguments("disable-infobars");
//                options.addArguments("--no-sandbox");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("start-maximized");
                options.addArguments("disable-infobars");
                options.addArguments("--no-sandbox");

                options.addArguments("--disable-extensions");
                options.addArguments("--crash-dumps-dir={os.path.expanduser('~/tmp/Crashpad')}");

                driver = new ChromeDriver(options);

//                driver = new ChromeDriver();
                break;
            case FIREFOX:
//                driver = WebDriverManager.firefoxdriver().create();
                driver = new FirefoxDriver();
                break;
            case EDGE:
//                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                throw new RuntimeException("Please enter the correct Browser name!");
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }
    public WebDriver getDriverInstance(){
        return this.driver;
    }
    protected int getRandomTime(){
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
