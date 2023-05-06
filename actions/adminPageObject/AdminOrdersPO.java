package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.admin.AdminHomePageUIs;
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
		waitForElementInvisiable(AdminHomePageUIs.LOADING_ICON);
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

	public boolean isInvoiceDownloaded(String fileName) {
		return isFileDownloaded(fileName);
	}

	public void closeInvoiceTab(String parentID) {
		closeAllTabWithoutParent(parentID);
		sleepInSecond(3);
	}

}
