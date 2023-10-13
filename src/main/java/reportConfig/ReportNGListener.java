package reportConfig;

import commons.BaseTest;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportNGListener implements ITestListener {

    // Lưu ảnh ở local
//    @Override
//    public void onTestFailure(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " FAILED test ----------");
//        System.setProperty("org.uncommons.reportng.escape-output", "false");
//
//        Object testClass = result.getInstance();
//        // Lấy driver của BaseTest do khi chạy testcase sẽ khởi tạo luôn
//        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();
//
//        String screenshotPath = captureScreenshot(webDriver, result.getName());
//        Reporter.getCurrentTestResult();
//        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
//        Reporter.setCurrentTestResult(null);
//    }
//    public String captureScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String screenPath = GlobalConstants.REPORTNG_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
//            FileUtils.copyFile(source, new File(screenPath));
//            return screenPath;
//        } catch (IOException e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//            return e.getMessage();
//        }
//    }

    // Lưu ảnh online base64
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriverInstance();

            String screenshotPath = captureScreenshot(driver, result.getName());
            Reporter.getCurrentTestResult();

            Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
            Reporter.setCurrentTestResult(null);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
