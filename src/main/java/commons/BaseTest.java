package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    WebDriver driver;
    protected final Log log;

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

//                String projectPath = System.getProperty("user.dir");
//                System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");
//                System.setProperty("webdriver.chrome.driver", "/home/jenkins/workspace/auto-test/Order-tracking-selenium/browserDrivers/chromedriver");
                driver = new ChromeDriver();
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
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
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
