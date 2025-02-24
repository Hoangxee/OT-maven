package commons;

import commons.constant.GlobalConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ExcelConfig;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class BasePage {
    private static BasePage basePage;
    protected final Log log;
    protected BasePage(){
        log = LogFactory.getLog(getClass());
    }

    public static BasePage getBasePage(){
        if(basePage==null){
            basePage = new BasePage();
        }
        return basePage;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }

    public WebElement getWebElement(WebDriver driver, String locatorType,String...dynamicValues){
        return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType){
        return driver.findElements(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType,String...dynamicValues){
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
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

    public String getTextInPageURL(WebDriver driver, String pageURL){
//        driver.getCurrentUrl().substring(pageURL.lastIndexOf("/") + 1, pageURL.indexOf("?"));
        return driver.getCurrentUrl().substring(pageURL.lastIndexOf("/") + 1);

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
        //note: switch to iframe after switch to window if app in app
        sleepInSecond(1);
        Set<String> allWindowID = driver.getWindowHandles();

        for(String id:allWindowID){
            driver.switchTo().window(id);
            String currentPageTitle = driver.getTitle();
            if (currentPageTitle.equals(expectedPageTitle)) {
                break;
            }
        }
    }

    public void switchToWindowByTitleContains(WebDriver driver, String expectedPageTitle){
        //note: switch to iframe after switch to window if app in app
        sleepInSecond(1);
        Set<String> allWindowID = driver.getWindowHandles();

        for(String id:allWindowID){
            driver.switchTo().window(id);
            String currentPageTitle = driver.getTitle();
            if (currentPageTitle.contains(expectedPageTitle)) {
                break;
            }
        }
    }

    public void switchToWindowByTitleContainsAndCloseAllWindowIgnoreParent(WebDriver driver, String expectedPageTitle){
        //note: switch to iframe after switch to window if app in app
        sleepInSecond(1);
        Set<String> allWindowID = driver.getWindowHandles();

        for(String id:allWindowID){
            driver.switchTo().window(id);
            String currentPageTitle = driver.getTitle();
            if (currentPageTitle.contains(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowIgnoreCurrent(WebDriver driver){
        //note: switch to iframe after switch to window if app in app
        String currentWindowID = driver.getWindowHandle();
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String windowID : allWindowIDs) {
            if (!windowID.equals(currentWindowID)) {
                driver.switchTo().window(windowID);
                driver.close();
            }
        }
        // Switch lại parent window
        driver.switchTo().window(currentWindowID);
    }


    public void clickToElement(WebDriver driver, String locatorType){
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues){
        getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).click();
    }

    public void clickToListElementByIndex(WebDriver driver, String locatorType, int index){
        getListWebElement(driver, locatorType).get(index).click();
    }

    public void clickToListElementByIndex(WebDriver driver, String locatorType, int index, String...dynamicValues){
        getListWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).get(index).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String valueToSendKey){
        getWebElement(driver, locatorType).clear();
        getWebElement(driver, locatorType).sendKeys(valueToSendKey);
    }

//    public void sendKeyToElementAfterClearText(WebDriver driver, String locatorType, String valueToSendKey){
//        getWebElement(driver, locatorType).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
//        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(locatorType),"value",""));
//        getWebElement(driver, locatorType).sendKeys(valueToSendKey);
//    }

    public void sendKeyToElementAfterClearText(WebDriver driver, String locatorType, String valueToSendKey) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(webDriver -> {
                    String value = element.getAttribute("value");
                    return value == null || value.isEmpty();
                });

        element.sendKeys(valueToSendKey);
    }

    public void sendKeyToElementAfterClearText(WebDriver driver, String locatorType, String valueToSendKey, String...dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType,dynamicValues));
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(webDriver -> {
                    String value = element.getAttribute("value");
                    return value == null || value.isEmpty();
                });

        element.sendKeys(valueToSendKey);
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

    public String getTextInElementAndTrim(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).getText().trim();
    }

    public String getTextInElementAndTrim(WebDriver driver, String locatorType, String...dynamicValues){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText().trim();
    }

    public String getTextInElementByIndex(WebDriver driver, String locatorType, int index){
        return getListWebElement(driver, locatorType).get(index).getText();
    }

    public void selectItemInDropdown(WebDriver driver, String locatorType, String valueItem){
        sleepInSecond(1);
        new Select(getWebElement(driver, locatorType)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locatorType, String valueItem, String...dynamicValues){
        sleepInSecond(1);
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

    public void selectItemInCustomDropdownByJS(WebDriver driver, String parentLocator, String childItemDynamicLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemDynamicLocator)));
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

    public void selectItemInCustomDropDown(WebDriver driver, String parentLocator, String childLocator,String expectedItem){
        waitForElementClickable(driver, parentLocator);
        clickToElement(driver, parentLocator);

        waitForElementPresence(driver,childLocator);
        List<WebElement> allNumberDropdown = getListWebElement(driver, childLocator);

        for (WebElement item:allNumberDropdown) {
            if(item.getText().equals(expectedItem)){
                item.click();
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

    public boolean isElementDisplayed(WebDriver driver, String locatorType){
        try{
            return getWebElement(driver,locatorType).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String...dynamicValues){
        try{
            return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isListElementDisplayed(WebDriver driver, String locatorType){
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> dynamicElements = getListWebElement(driver, locatorType);

        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if(dynamicElements.size() != 0 ){
            waitForListElementVisible(driver, locatorType);
//            System.out.println("Element có trong DOM và displayed");
            return true;
        }else {
//            System.out.println("Element not in DOM");
            return false;
        }
    }

    public boolean isListElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues){
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> dynamicElements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));

        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if(dynamicElements.size() != 0 ){
            waitForListElementVisible(driver, getDynamicXpath(locatorType, dynamicValues));
//            System.out.println("Message có trong DOM");
            return true;
        }else {
//            System.out.println("Message not in DOM");
            return false;
        }
    }

    public boolean isElementUnDisplay(WebDriver driver, String locatorType){
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElement(driver, locatorType);
        // gán lại thời gian wait cho những step sau
        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
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
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        // gán lại thời gian wait cho những step sau
        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }

    public boolean isElementSelected(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType,String...dynamicValues){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType){
        waitForFrameToBeAvailable(driver, locatorType);
//        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType,String...dynamicValues){
        waitForFrameToBeAvailable(driver, locatorType,dynamicValues);
//        driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locatorType){
        new Actions(driver).moveToElement(getWebElement(driver, locatorType)).perform();
    }

    public void moveToListElementsByIndex(WebDriver driver, String locatorType, int index){
        new Actions(driver).moveToElement(getListWebElement(driver, locatorType).get(index)).perform();
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

    public void sendKeyBoard(WebDriver driver, Keys key){
        new Actions(driver).sendKeys(key).perform();
    }

    public void sendKeyBoard(WebDriver driver, String key){
        new Actions(driver).sendKeys(key).perform();
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

    public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
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

    public void scrollToElement(WebDriver driver, String locatorType, int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getListWebElement(driver, locatorType).get(index));
    }

    public void scrollToBelowElement(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locatorType, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
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
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType,String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForListElementVisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locatorType)));
    }

    public void waitForListElementVisible(WebDriver driver, String locatorType,String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,(getDynamicXpath(locatorType, dynamicValues)))));
    }

    public void waitForElementVisibleByIndex(WebDriver driver, String locatorType, int index){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locatorType).get(index)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementClickableByIndex(WebDriver driver, String locatorType, int index){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getListWebElement(driver,locatorType).get(index)));
    }

    public void waitForElementAttributeChange(WebDriver driver, String locatorType, String attribute, String value){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(locatorType),attribute,value));
    }

    public void waitForElementAttributeChange(WebDriver driver, String locatorType, String attribute, String value, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(getDynamicXpath(locatorType, dynamicValues)),attribute,value));
    }

    public void waitForElementAttributeChangeToInitialValue(WebDriver driver, String locatorType, String attribute, String initialValue){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(driver1 -> {
                    String currentValue = getAttribute(driver1, locatorType, attribute);
                    return !currentValue.equals(initialValue);
                });
    }

    public void waitForElementAttributeChangeToInitialValue(WebDriver driver, String locatorType, String attribute, String initialValue, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(driver1 -> {
                    String currentValue = getAttribute(driver1, getDynamicXpath(locatorType, dynamicValues), attribute);
                    return !currentValue.equals(initialValue);
                });
    }

    public void waitForTextChange(WebDriver driver, String locatorType, String text){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(driver1 -> {
                    String currentText = getTextInElement(driver1, locatorType);
                    return !currentText.equals(text);
                });
    }
    public void waitForTextChange(WebDriver driver, String locatorType, String text, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(driver1 -> {
                    String currentText = getTextInElement(driver1, getDynamicXpath(locatorType, dynamicValues));
                    return !currentText.equals(text);
                });
    }


    public void waitForElementInvisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForListElementInvisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
    }

    public void waitForListElementSizeChange(WebDriver driver, String locatorType, int initialSize) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(driver1 -> {
            List<WebElement> currentElements = getListWebElement(driver1, locatorType);
            return currentElements.size() != initialSize;
        });
    }

    public void waitForListElementSizeChange(WebDriver driver, String locatorType, int initialSize, String...dynamicValues) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(driver1 -> {
            List<WebElement> currentElements = getListWebElement(driver1,(getDynamicXpath(locatorType, dynamicValues)));
            return currentElements.size() != initialSize;
        });
    }

    public void waitForElementUnDisplay(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }

    public void waitForElementUnDisplay(WebDriver driver, String locatorType, String...dynamicValues){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
        overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }

    public void waitForElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
    }
    public void waitForElementPresence(WebDriver driver, String locatorType, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForListElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAlertPresent(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public Boolean waitForPageUrlToBe(WebDriver driver, String pageURL){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.urlToBe(pageURL));
    }

    public Boolean waitForPageUrlContains(WebDriver driver, String pageURL){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.urlContains(pageURL));
    }

    public void waitForTextPresent(WebDriver driver, String locatorType, String textExpected){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(locatorType),textExpected));
    }

    public void waitForTextPresent(WebDriver driver, String locatorType, String textExpected, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues)),textExpected));
    }

    private void waitForFrameToBeAvailable(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locatorType)));
    }

    public void waitForFrameToBeAvailable(WebDriver driver, String locatorType, String...dynamicValues){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public Boolean waitForInputSuccessful(WebDriver driver, String locatorType, String textInput){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.textToBe(getByLocator(locatorType),textInput));
    }

    public Boolean waitForInputSuccessful(WebDriver driver, String locatorType, String textInput, String...dynamicValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.textToBe(getByLocator(getDynamicXpath(locatorType, dynamicValues)),textInput));
    }

    public void closeWindowTab(WebDriver driver){
        driver.close();
    }

    public void clearCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    public void waitForProcessBar(WebDriver driver, String locator){
        List<WebElement> progressBarShopify = getListWebElement(driver, locator);
        if(progressBarShopify.size() != 0){
            waitForElementInvisible(driver,locator);
        }
    }

    public String getDateTimeNow(){
//        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a", Locale.ENGLISH);
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormat.format(now).replace("AM", "am").replace("PM", "pm");
    }

    public String getDataFormExcel(String filePath,String sheetName, String columnName, int row){
        ExcelConfig excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet(filePath,sheetName);
        return excelConfig.getCellData(columnName, row);
    }

    public void deleteAllFileInFolder() {
        String pathFolderDownload = GlobalConstants.DOWNLOAD_FILE;
        File[] listOfFiles = new File(pathFolderDownload).listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public void waitForDownloadFileContainsNameCompleted(String fileName){
        try {
            int i = 0;
            while (i < 30) {
                boolean exist = isFileContain(fileName);
                if (exist) {
                    i = 30;
                } else {
                    Thread.sleep(500);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.print("Error "+e.getMessage());
        }
    }

    private boolean isFileContain(String fileName) {
        String pathFolderDownload = GlobalConstants.DOWNLOAD_FILE;
        File[] files = new File(pathFolderDownload).listFiles();
        if (files == null) return false;

        for (File file : files) {
            if (file.getName().contains(fileName)) {
                return true;
            }
        }
        return false;
    }

    public int countFilesInDirectory() {
        String pathFolderDownload = GlobalConstants.DOWNLOAD_FILE;
        File file = new File(pathFolderDownload);
        int i = 0;
        for (File listOfFiles : file.listFiles()) {
            if (listOfFiles.isFile()) {
                i++;
            }
        }
        return i;
    }

    public void deleteFileContainName(String fileName) {
        try {
            String files;
            String pathFolderDownload = GlobalConstants.DOWNLOAD_FILE;
            File[] listOfFiles = new File(pathFolderDownload).listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    files = listOfFiles[i].getName();
                    if (files.contains(fileName)) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("Error "+e.getMessage());
        }
    }

    public void waitForPageLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static int getRandomTime() {
        return new Random().nextInt(999999);
    }

    public String upperCaseFirstWord(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String[] words = text.toLowerCase().split(" ");
        StringBuilder titleCase = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return titleCase.toString().trim();
    }

}
