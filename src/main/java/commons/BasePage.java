package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private static BasePage basePage;
    public static BasePage getBasePage(){
        if(basePage==null){
            basePage = new BasePage();
        }
        return basePage;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType){
        return driver.findElements(getByLocator(locatorType));
    }

    private By getByLocator(String locatorType){
        By by = null;
        if(locatorType.startsWith("xpath")||locatorType.startsWith("XPATH")||locatorType.startsWith("Xpath")||locatorType.startsWith("XPath")){
            by = By.xpath(locatorType.substring(6));
        } else if(locatorType.startsWith("id")||locatorType.startsWith("ID")||locatorType.startsWith("Id")){
            by = By.id(locatorType.substring(3));
        } else if(locatorType.startsWith("css")||locatorType.startsWith("CSS")||locatorType.startsWith("Css")){
            by = By.cssSelector(locatorType.substring(4));
        } else if(locatorType.startsWith("name")||locatorType.startsWith("NAME")||locatorType.startsWith("Name")){
            by = By.name(locatorType.substring(5));
        } else if(locatorType.startsWith("class")||locatorType.startsWith("CLASS")||locatorType.startsWith("Class")){
            by = By.className(locatorType.substring(6));
        } else{
            throw new RuntimeException("Locator type is not supported!!");
        }
        return by;
    }

    private String getDynamicXpath(String locatorType, String... values){
        if (locatorType.startsWith("xpath")||locatorType.startsWith("XPATH")||locatorType.startsWith("Xpath")||locatorType.startsWith("XPath")){
            locatorType = String.format(locatorType, (Object[]) values);
        }
        return locatorType;
    }

    public void  openURL(WebDriver driver, String pageURL){
        driver.navigate().to(pageURL);
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public void acceptToAlert(WebDriver driver){
        waitForAlertPresent(driver);
        driver.switchTo().alert().accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitForAlertPresent(driver);
        driver.switchTo().alert().dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String valueToSendKey){
        waitForAlertPresent(driver);
        driver.switchTo().alert().sendKeys(valueToSendKey);
    }

    public String getTextInAlert(WebDriver driver){
        waitForAlertPresent(driver);
        return driver.switchTo().alert().getText();
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle){
        // lấy ra tất cả các ID của tab/window đang có
        Set<String> allWindowID = driver.getWindowHandles();

        // Duyệt từng ID
        for(String id:allWindowID){
            // Switch vào từng tab/window
            driver.switchTo().window(id);
            // Lấy ra page title của tab/window đã swtich vào
            String currentPageTitle = driver.getTitle();
            if (currentPageTitle.equals(expectedPageTitle)) {
                // Nếu đúng với title của tab/window muốn swtich vào
                // -> thoát khỏi vòng lặp
                break;
            }
        }
    }

    public void closeAllWindowIgnoreParent(WebDriver driver, String parentID){
        // Lấy ra tất cả các ID của tab/window đang có
        Set<String> allWindowID = driver.getWindowHandles();

        // Duyệt qua từng ID
        for(String id:allWindowID){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                // đóng tab hiện tại
                // khác với driver.quit() : đóng toàn bộ trình duyệt
                driver.close();
            }
        }
        // Switch lại parent window
        driver.switchTo().window(parentID);
    }

    public void clickToElement(WebDriver driver, String locatorType){
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues){
        getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String valueToSendKey){
        getWebElement(driver, locatorType).clear();
        getWebElement(driver, locatorType).sendKeys(valueToSendKey);
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String valueToSendKey, String... dynamicValues){
        getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).clear();
        getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).sendKeys(valueToSendKey);
    }


    public String getTextInElement(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).getText();
    }

    public String getTextInElement(WebDriver driver, String locatorType, String...dynamicValues){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
    }

    public void selectItemInDropdown(WebDriver driver, String locatorType, String valueItem){
        new Select(getWebElement(driver, locatorType)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locatorType, String valueItem, String...dynamicValues){
        new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locatorType){
        return new Select(getWebElement(driver,locatorType)).getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locatorType,String...dynamicValues){
        return new Select(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues))).getFirstSelectedOption().getText();
    }

    public Boolean isDropdownMultiple(WebDriver driver, String locatorType){
        return new Select(getWebElement(driver, locatorType)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemDynamicLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemDynamicLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public String getAttribute(WebDriver driver, String locatorType, String attributeName){
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getAttribute(WebDriver driver, String locatorType, String attributeName, String...dynamicValues){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locatorType, String propertyName){
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementsSize(WebDriver driver, String locatorType){
        return getListWebElement(driver, locatorType).size();
    }

    public int getElementsSize(WebDriver driver, String locatorType, String... dynamicValues){
        return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
    }

    public void checkToCheckboxAndRadio(WebDriver driver, String locatorType){
        if(!isElementSelected(driver,locatorType)){
            getWebElement(driver, locatorType).click();
        }
    }

    public void checkToCheckboxAndRadio(WebDriver driver, String locatorType, String... dynamicValues){
        if(!isElementSelected(driver,getDynamicXpath(locatorType, dynamicValues))){
            getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locatorType){
        if(isElementSelected(driver,locatorType)){
            getWebElement(driver, locatorType).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locatorType, String... dynamicValues){
        if(isElementSelected(driver,getDynamicXpath(locatorType, dynamicValues))){
            getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
        }
    }

    public void UncheckToCheckbox(WebDriver driver, String locatorType){
        if(isElementSelected(driver,locatorType)){
            getWebElement(driver, locatorType).click();
        }
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType){
        try{
            return getWebElement(driver,locatorType).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType, String...dynamicValues){
        try{
            return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementUnDisplay(WebDriver driver, String locatorType){
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, locatorType);
        // gán lại thời gian wait cho những step sau
        overrideImplicitTimeout(driver, longTimeout);
        if(elements.size() == 0){
//            System.out.println("Element not in DOM");
            return true;
        }else  if(elements.size() > 0 && !elements.get(0).isDisplayed()){
//            System.out.println("Element có trong DOM nhưng không displayed");
            return true;
        }else {
//            System.out.println(" Element có trong DOM và displayed");
            return false;
        }
    }

    public boolean isElementUnDisplay(WebDriver driver, String locatorType, String... dynamicValues){
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        // gán lại thời gian wait cho những step sau
        overrideImplicitTimeout(driver, longTimeout);
        if(elements.size() == 0){
//            System.out.println("Element not in DOM");
            return true;
        }else  if(elements.size() > 0 && !elements.get(0).isDisplayed()){
//            System.out.println("Element có trong DOM nhưng không displayed");
            return true;
        }else {
//            System.out.println(" Element có trong DOM và displayed");
            return false;
        }
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeOut){
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public Boolean isElementSelected(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).isSelected();
    }

    public Boolean isElementSelected(WebDriver driver, String locatorType,String...dynamicValues){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
    }

    public Boolean isElementDEnabled(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType){
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType,String...dynamicValues){
        driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }


    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locatorType){
        new Actions(driver).moveToElement(getWebElement(driver, locatorType)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locatorType){
        new Actions(driver).doubleClick(getWebElement(driver, locatorType)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locatorType){
        new Actions(driver).contextClick(getWebElement(driver, locatorType)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver,sourceLocator),getWebElement(driver,targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locatorType, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locatorType),key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String)((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locatorType), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues){
        new Actions(driver).sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    public void scrollToBelowElement(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locatorType, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType,String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForListElementVisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType,String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementAttributeChange(WebDriver driver, String locatorType, String attribute, String value){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.attributeToBe(getByLocator(locatorType),attribute,value));
    }

    public void waitForElementAttributeChange(WebDriver driver, String locatorType, String attribute, String value,String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.attributeToBe(getByLocator(getDynamicXpath(locatorType, dynamicValues)),attribute,value));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForListElementInvisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
    }

    public void waitForElementUnDisplay(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, longTimeout);
    }

    public void waitForElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForListElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAlertPresent(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public Boolean waitForPageUrlToBe(WebDriver driver, String pageURL){
        return new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.urlToBe(pageURL));
    }

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
