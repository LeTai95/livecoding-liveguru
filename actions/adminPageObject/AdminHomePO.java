package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.admin.AdminHomePageUIs;
import userPageObject.UserHomePO;

public class AdminHomePO extends BasePage {
	WebDriver driver;

	public AdminHomePO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterToTextboxByID(String textboxID, String textValue) {
		waitForElementVisiable(AdminHomePageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(AdminHomePageUIs.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}

	public void clickToLoginButton() {
		waitForElementClickable(AdminHomePageUIs.LOGIN_BUTTON);
		clickToElement(AdminHomePageUIs.LOGIN_BUTTON);
	}

	public void closeAlert() {
		cancelAlert();
	}

	public boolean isPopupDisplayed() {
		waitForElementVisiable(AdminHomePageUIs.POPUP);
		return isElementDisplayed(AdminHomePageUIs.POPUP);
	}

	public void closePopup() {
		waitForElementClickable(AdminHomePageUIs.CLOSE_POPUP_BUTTON);
		clickToElement(AdminHomePageUIs.CLOSE_POPUP_BUTTON);
	}

	public void enterToTextboxByName(String textboxName, String textValue) {
		waitForElementVisiable(AdminHomePageUIs.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendkeyToElement(AdminHomePageUIs.DYNAMIC_TEXTBOX_BY_NAME, textValue, textboxName);
	}


	public String customerInfoByColumnNameAndRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(AdminHomePageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		waitForElementVisiable(AdminHomePageUIs.DYNAMIC_CUSTOMER_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER, rowNumber,
				String.valueOf(columnIndex));
		return getElementText(AdminHomePageUIs.DYNAMIC_CUSTOMER_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER, rowNumber,
				String.valueOf(columnIndex));
	}

	public void checkToCheckbox() {
		waitForElementInvisiable(AdminHomePageUIs.LOADING_ICON);
		waitForElementClickable(AdminHomePageUIs.CHECKBOX);
		clickToElement(AdminHomePageUIs.CHECKBOX);
	}

	public void selectItemInDropdownByDropdownID(String dropdownID, String textItem) {
		waitForElementVisiable(AdminHomePageUIs.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(AdminHomePageUIs.DYNAMIC_DROPDOWN_BY_ID, textItem, dropdownID);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(AdminHomePageUIs.SUBMIT_BUTTON);
		clickToElement(AdminHomePageUIs.SUBMIT_BUTTON);
	}

	public boolean isCustomerInfoDeletedSuccessMessageDisplayed() {
		waitForElementVisiable(AdminHomePageUIs.CUSTOMER_INFO_DELETED_SUCCESS_MESSAGE);
		return isElementDisplayed(AdminHomePageUIs.CUSTOMER_INFO_DELETED_SUCCESS_MESSAGE);
	}

	public void hoverToMenuByName(String menuName) {
		waitForElementVisiable(AdminHomePageUIs.DYNAMIC_OBJECT_BY_NAME, menuName);
		hoverMouseToElement(AdminHomePageUIs.DYNAMIC_OBJECT_BY_NAME, menuName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(AdminHomePageUIs.SEARCH_BUTTON);
		clickToElement(AdminHomePageUIs.SEARCH_BUTTON);
	}
 
}


