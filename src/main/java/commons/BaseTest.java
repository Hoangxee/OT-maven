package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest extends BasePage{
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
                String projectPath = System.getProperty("user.dir");
                System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case EDGE:
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                throw new RuntimeException("Please enter the correct Browser name!");
        }
//        driver.manage().window().setSize(new Dimension(1366,768));
//        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        openURL(driver, appURL);
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
