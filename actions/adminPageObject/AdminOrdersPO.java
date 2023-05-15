package adminPageObject;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.liveguru.admin.AdminOrdersPageUIs;
public class AdminOrdersPO extends BasePage {
	WebDriver driver;
	public AdminOrdersPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void selectItemInDropdownByDropdownID(String dropdownID, String textItem) {
		waitForElementVisiable(AdminOrdersPageUIs.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(AdminOrdersPageUIs.DYNAMIC_DROPDOWN_BY_ID, textItem, dropdownID);
	}
	
	public void checkToTheFirstCheckbox() {
		waitForElementInvisiable(AdminOrdersPageUIs.LOADING_ICON);
		waitForElementClickable(AdminOrdersPageUIs.FIRST_CHECKBOX);
		checkToDefautCheckboxRadio(AdminOrdersPageUIs.FIRST_CHECKBOX);
	}
	
	public boolean isErrorMessageDisplayed() {
		waitForElementVisiable(AdminOrdersPageUIs.ERROR_MESSAGE);
		return isElementDisplayed(AdminOrdersPageUIs.ERROR_MESSAGE);
	}
	
	public void clickToButtonByName(String buttonName) {
		waitForElementClickable(AdminOrdersPageUIs.DYNAMIC_BUTTON_BY_NAME, buttonName);
		clickToElement(AdminOrdersPageUIs.DYNAMIC_BUTTON_BY_NAME, buttonName);
		sleepInSecond(5);
	}

	public String getFirstOrderNumber() {
		waitForElementVisiable(AdminOrdersPageUIs.FIRST_ORDER_NUMBER);
		return getElementText(AdminOrdersPageUIs.FIRST_ORDER_NUMBER);
	}

	public boolean isInvoiceDownloaded(String fileName){
		try {
			waitForDownloadFileContainsNameCompleted(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isFileDownloaded(fileName);
	}

	public void closeInvoiceTab(String parentID) {
		closeAllTabWithoutParent(parentID);
		sleepInSecond(3);
	}

	public void deleteFileDownloaded(String fileName) {
		deleteFileContainName(fileName);
	}

	public String numberItemDisplayed() {
		waitForElementInvisiable(AdminOrdersPageUIs.LOADING_ICON);
		waitForAllElementVisiable(AdminOrdersPageUIs.ROW_NUMBER);
		return String.valueOf(getElementSize(AdminOrdersPageUIs.ROW_NUMBER));
	}

	public void selectNumberItemDisplay(String number) {
		waitForElementVisiable(AdminOrdersPageUIs.VIEWS_DROPDOWN);
		selectItemInDefaultDropdown(AdminOrdersPageUIs.VIEWS_DROPDOWN, number);
	}

	public void clickToLinkByText(String linkText) {
		waitForElementInvisiable(AdminOrdersPageUIs.LOADING_ICON);
		waitForElementClickable(AdminOrdersPageUIs.DYNAMIC_LINK_BY_TEXT, linkText);
		clickToElement(AdminOrdersPageUIs.DYNAMIC_LINK_BY_TEXT, linkText);
	}

	public boolean isNumberItemSelectedDisplayed(String numberItem) {
		waitForElementVisiable(AdminOrdersPageUIs.DYNAMIC_NUMBER_ITEM_DISPLAYED_MESSAGE, numberItem);
		return isElementDisplayed(AdminOrdersPageUIs.DYNAMIC_NUMBER_ITEM_DISPLAYED_MESSAGE, numberItem);
	}

	public String numberCheckboxIsSelected() {
		List<WebElement> checkBox = getListWebElement(AdminOrdersPageUIs.CHECKBOX);
		int checkedCount = 0;
		for (int i = 0; i < checkBox.size(); i++) {
			if (checkBox.get(i).isSelected()) {
				checkedCount++;
			}
		}
		return String.valueOf(checkedCount);
	}

}
