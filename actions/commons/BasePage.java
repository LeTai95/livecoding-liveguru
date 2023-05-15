package commons;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import adminPageObject.AdminHomePO;
import adminPageObject.AdminOrdersPO;
import pageUI.liveguru.admin.AdminHomePageUIs;
import userPageObject.UserHomePO;

 

public class BasePage {
	private WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPageUrl(String pageUrl) {
		
		driver.get(pageUrl);
	}
	
	public String getPageTitle() {
		
		return driver.getTitle();	
	}
	
	public String getPageUrl() {
		
		return driver.getCurrentUrl();
	}
	
	public String getPageSource() {
		
		return driver.getPageSource();
	}
	
	public void backToPage() {
		
		driver.navigate().back();
	}
	
	public void forwardToPage() {
		
		driver.navigate().forward();
	}
	
	public void refreshToPage() {
		
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookie() {
		return driver.manage().getCookies();
	}
	
	public void setCookie(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert() {
		waitForAlertPresence().accept();
		
	}
	
	public void cancelAlert() {
		waitForAlertPresence().dismiss();
	}
	
	public String getAlertText() {
		return waitForAlertPresence().getText();
	}
	
	public void sendkeyToAlert(String textValue) {
		waitForAlertPresence().sendKeys(textValue);
	}
	
 	public void switchToWindowByID (String windowID) {
 		
		Set<String> allWindowIDs = driver.getWindowHandles();
			for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
 	}
 	
 	public void switchToWindowByPageTitle (String tabTitle) {
 		
 		Set<String> allWindowIDs = driver.getWindowHandles();
 		for (String id : allWindowIDs) {
 			driver.switchTo().window(id);
 			String actualTitle = driver.getTitle();
 			if (actualTitle.equals(tabTitle)) {
 				break;
			}
 		}	
 	}
 	
 	public void closeAllTabWithoutParent(String parentID) {
 		
 		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
		if (!id.equals(parentID)) {
			driver.switchTo().window(id);
			driver.close();
			}
			driver.switchTo().window(parentID);
		}
 	}
	
 	public By getByLocator(String locatorType) {
 		By by = null;
 		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
 			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not support !");
		}
 		return by;
 	}
 	
 	public String getDynamicXpath(String locatorType, String... dynamicValues) {
 		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
 				|| locatorType.startsWith("Xpath=")) {
 			locatorType = String.format(locatorType,(Object[])dynamicValues) ;
		}
 		return locatorType;
 	}
 	
 	public WebElement getWebElement(String locatorType) {
 		return driver.findElement(getByLocator(locatorType));
 	}
 	
 	public List<WebElement> getListWebElement(String locatorType) {
 		
 		return driver.findElements(getByLocator(locatorType));
 	}
 	
 	public List<WebElement> getListWebElement(String locatorType, String... dynamicValues) {
 		
 		return driver.findElements(getByLocator(getDynamicXpath(locatorType,dynamicValues)));
 	}
 	
 	public void clickToElement(String locatorType) {
 		
 		getWebElement(locatorType).click();
 	}
 	
	public void clickToElement(String locatorType, String... dynamicValues) {
 		
 		getWebElement(getDynamicXpath(locatorType,dynamicValues)).click();
 		sleepInSecond(1);
 	}
	
	public void clearText(String locatorType) {
		
		getWebElement(locatorType).clear();
	}
	
	public void clearText(String locatorType, String... dynamicValues) {
		
		getWebElement(getDynamicXpath(locatorType,dynamicValues)).clear();
	}
 	
 	public void sendkeyToElement(String locatorType, String textValue) {
 		WebElement element = getWebElement(locatorType);
 		element.clear();
 		element.sendKeys(textValue);
 	}
 	
	public void sendkeyToElement(String locatorType, String textValue, String... dynamicValues) {
 		WebElement element = getWebElement(getDynamicXpath(locatorType,dynamicValues));
 		element.clear();
 		element.sendKeys(textValue);
 	}
 	
 	public String getElementText(String locatorType) {
 		
 		return getWebElement(locatorType).getText();
 	}
 	
 	public String getElementText(String locatorType, String... dynamicValues) {
 		
 		return getWebElement(getDynamicXpath(locatorType,dynamicValues)).getText();
 	}

 	public void selectItemInDefaultDropdown(String locatorType, String textItem) {
 		
 		Select select = new Select(getWebElement(locatorType));
 		select.selectByVisibleText(textItem);
 	}
 	
 	public void selectItemInDefaultDropdown(String locatorType, String textItem, String... dynamicValues) {
 		
 		Select select = new Select(getWebElement(getDynamicXpath(locatorType,dynamicValues)));
 		select.selectByVisibleText(textItem);
 	}
 	
 	public String getSelectedItemInDefaultDropdown(String locatorType) {
 		
 		Select select = new Select(getWebElement(locatorType));
 		return select.getFirstSelectedOption().getText();
 	}
 	
 	public String getSelectedItemInDefaultDropdown(String locatorType, String...dynamicValues ) {
 		
 		Select select = new Select(getWebElement(getDynamicXpath(locatorType,dynamicValues)));
 		return select.getFirstSelectedOption().getText();
 	}
 	
 	public boolean isDropdownMultiple(String locatorType) {
 		
 		Select select = new Select(getWebElement(locatorType));
 		return select.isMultiple();
 	}
 	
 	public void enterAndSelectItemInDropdown(String textboxXpath, String itemListXpath,String  inputItemXpath,String extTextItem) {
 		getWebElement(textboxXpath).click();
 		getWebElement(inputItemXpath).sendKeys(extTextItem);
 		sleepInSecond(2);
 		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);	
		List<WebElement> allDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(itemListXpath)));
 		for (WebElement item : allDropdownItems) {
			if (item.getText().trim().equals(extTextItem)) {
				item.click();
				break;
			}
		}
 	}
 	
 	
	public void selectItemInCustomDropdown (String parentXpath, String childXpath, String extTextItem) {
		getWebElement(parentXpath).click();
		sleepInSecond(3);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);	
		List<WebElement> allDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));	
		for (WebElement item : allDropdownItems) {
			if (item.getText().trim().equals(extTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
				}
			}
		}
	

	public void sleepInSecond(long timeInSecond) {
		try {
		Thread.sleep(timeInSecond * 1000);}
		catch (InterruptedException e) {
		e.printStackTrace();}
		}
	
	public String getElementAttribute(String locatorType, String attributeName) {
		
		return getWebElement(locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(String locatorType, String attributeName, String... dynamicValues) {
		
		return getWebElement(getDynamicXpath(locatorType,dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(String locatorType, String propertyeName) {
		
		return getWebElement(locatorType).getCssValue(propertyeName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(String locatorType) {
		
		return getListWebElement(locatorType).size();
	}
	
	public int getElementSize(String locatorType, String... dynamicValues) {
		
		return getListWebElement(getDynamicXpath(locatorType,dynamicValues)).size();
	}

	public void checkToDefautCheckboxRadio(String locatorType) {
		
		WebElement element = getWebElement(locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void checkToDefautCheckboxRadio(String locatorType, String... dynamicValues) {
		
		WebElement element = getWebElement(getDynamicXpath(locatorType,dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unheckToDefautCheckbox(String locatorType) {
		
		WebElement element = getWebElement(locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	public void unheckToDefautCheckbox(String locatorType, String... dynamicValues) {
		
		WebElement element = getWebElement(getDynamicXpath(locatorType,dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(String locatorType) {
		try {
			return getWebElement(locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementDisplayed(String locatorType, String... dynamicValues) {
		
		return getWebElement(getDynamicXpath(locatorType,dynamicValues)).isDisplayed();
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(String locatorType) {
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(locatorType);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM.");
			return true;
		} else if(elements.size() >0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visiable/displayed.");
			return true;
		} else
		{
			System.out.println("Element in DOM and visiable.");
			return false;
		}
		
	}
	
	public boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(getDynamicXpath(locatorType,dynamicValues));
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM.");
			return true;
		} else if(elements.size() >0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visiable/displayed.");
			return true;
		} else
		{
			System.out.println("Element in DOM and visiable.");
			return false;
		}
		
	}
	
	public boolean isElementEnable(String locatorType) {
		
		return getWebElement(locatorType).isEnabled();
	}
	
	public boolean isElementSelected(String locatorType) {
		
		return getWebElement(locatorType).isSelected();
	}
	
	public boolean isElementSelected(String locatorType, String... dynamicValues) {
		
		return getWebElement(getDynamicXpath(locatorType,dynamicValues)).isSelected();
	}
	
	public void switchToFrameIframe(String locatorType) {
		
		driver.switchTo().frame(getWebElement(locatorType));
	}
	
	public void switchToDefautContent() {
		
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(String locatorType) {
		
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locatorType)).perform();
	}	
	
	public void hoverMouseToElement(String locatorType, String... dynamicValues) {
		
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(getDynamicXpath(locatorType,dynamicValues))).perform();
	}
	
	public void pressKeyToElement(String locatorType, Keys key) {
		
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(locatorType), key).perform();
	}
	
	public void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
		
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(getDynamicXpath(locatorType,dynamicValues)), key).perform();
	}

	public void clearValueInElementByDeleteKey(String locatorType) {
 		WebElement element = getWebElement(locatorType);
 		element.clear();
 		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void hightlightElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void clickToElementByJS(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(locatorType));
	}
	
	public void clickToElementByJS(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(getDynamicXpath(locatorType,dynamicValues)));
	}
	
	public void scrollToElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
	}
	
	public String getElementValueByJSXpath(String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}

	public void removeAttributeInDOM(String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locatorType));
	}
	
	public boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
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

	public String getElementValidationMessage(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locatorType));
	}

	public boolean isImageLoaded(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (Boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(locatorType));
		return status;
	}
	
	public boolean isImageLoaded(String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (Boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(getDynamicXpath(locatorType,dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisiable(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisiable(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	
	public void waitForAllElementVisiable(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisiable(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	
	public void waitForElementInvisiable(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	
	/*
	 *Wait for element undisplayed in DOM or not in DOM and override implicit wait
	 */
	public void waitForElementUndisplayed(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longTimeout);
	}
	
	public void waitForElementUndisplayed(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
		overrideGlobalTimeout(driver, longTimeout);
	}
	
	public void waitForElementInvisiable(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	
	public void waitForAllElementinVisiable(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locatorType)));
	}
	
	public void waitForAllElementinVisiable(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(getDynamicXpath(locatorType,dynamicValues))));
	}
	
	
	public void waitForElementClickable(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}

	public void waitForElementPresence(String locatorType) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementPresence(String locatorType, String... dynamicValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	
	public void uploatMultipleFiles(String...fileNames) {
		
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(BasePageUIs.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	public boolean isFileDownloaded(String fileName) {
		if (isFileExists(fileName)) {
			deleteContainName(fileName);
		} else {
			File fileLocation = new File(GlobalConstants.DOWNLOAD_PATH);
			File[] totalFiles = fileLocation.listFiles();
			for (File file : totalFiles) {
				if (file.getName().contains(fileName)) {
					deleteContainName(fileName);
					return true;
				}
			}
		}
		return false;
	}
	
	public String getPathContainDownload() {
	String path = "";
	String machine_name;
	machine_name = System.getProperty("user.home");
	path = String.format("%s\\Downloads\\", machine_name);
	return path;
	}
	
	public void deleteAllFileInFolder() {
	try {
	String pathFolderDownload = getPathContainDownload();
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
	
	public boolean isFileContain(String fileName) {
	try {
	boolean flag = false;
	String pathFolderDownload = getPathContainDownload();
	File dir = new File(pathFolderDownload);
	File[] files = dir.listFiles();
	if (files == null || files.length == 0) {
	flag = false;
	}
	for (int i = 1; i < files.length; i++) {
	if (files[i].getName().contains(fileName)) {
	flag = true;
		}
	}
	return flag;
	} catch (Exception e) {
	System.out.print(e.getMessage());
	return false;
		}
	}
	
	public void deleteFileContainName(String fileName) {
		deleteContainName(fileName);
		}

		public void deleteContainName(String fileName) {
		try {
		String files;
		String pathFolderDownload = getPathContainDownload();
		File file = new File(pathFolderDownload);
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		if (listOfFiles[i].isFile()) {
		files = listOfFiles[i].getName();
		if (files.contains(fileName)) {
		new File(listOfFiles[i].toString()).delete();
				}
			}
		}
		} catch (Exception e) {
		System.out.print(e.getMessage());
			}
		}
		
		public void waitForDownloadFileContainsNameCompleted(String fileName) throws Exception {
			int i = 0;
			while (i < GlobalConstants.LONG_TIME_OUT) {
			boolean exist = isFileContain(fileName);
			if (exist == true) {
			i = (int) GlobalConstants.LONG_TIME_OUT;
			}
			Thread.sleep(500);
			i = i + 1;
				}
			}
		public boolean isFileExists(String file) {
			try {
			String pathFolderDownload = getPathContainDownload();
			File files = new File(pathFolderDownload + file);
			boolean exists = files.exists();
			return exists;
			} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
				}
			}
	public AdminHomePO openBackEndSite(String BEurl) {
		openPageUrl(BEurl);
		return PageGeneraterManager.getAdminHomePage(driver);
	}
	

	public UserHomePO openFontEndSite(String FEurl) {
		openPageUrl(FEurl);
		return PageGeneraterManager.getUserHomePage(driver);
	}
	
	public BasePage clickToPageByName(String pageName) {
		waitForElementClickable(AdminHomePageUIs.DYNAMIC_OBJECT_BY_NAME, pageName);
		clickToElement(AdminHomePageUIs.DYNAMIC_OBJECT_BY_NAME, pageName);
		switch (pageName) {
		case "Orders":
			return PageGeneraterManager.getAdminOrdersPage(driver);
		case "Pending Reviews":
			return PageGeneraterManager.getAdminPendingReviewsPage(driver);
		case "Invoices":
			return PageGeneraterManager.getAdminInvoicesPage(driver);
		case "Manage Customers":
			return PageGeneraterManager.getAdminManageCustomersPage(driver);
		default:
			throw new RuntimeException("Invalid page name.");
		}
	}
	
	private long longTimeout = GlobalConstants.LONG_TIME_OUT;
	private long shortTimeout = GlobalConstants.SHORT_TIME_OUT;
}



