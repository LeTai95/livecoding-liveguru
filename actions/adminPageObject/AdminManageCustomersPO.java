package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.admin.AdminManageCustomersPageUIs;

public class AdminManageCustomersPO extends BasePage {
	WebDriver driver;
	public AdminManageCustomersPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void enterToTextboxByTextboxName(String textboxName, String textValue) {
		waitForElementVisiable(AdminManageCustomersPageUIs.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendkeyToElement(AdminManageCustomersPageUIs.DYNAMIC_TEXTBOX_BY_NAME, textValue, textboxName);
	}
	public void selectItemInDropdownByID(String dropdownID, String textItem) {
		waitForElementVisiable(AdminManageCustomersPageUIs.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(AdminManageCustomersPageUIs.DYNAMIC_DROPDOWN_BY_ID, textItem, dropdownID);
	}
	public void clickToSearchButton() {
		waitForElementClickable(AdminManageCustomersPageUIs.SEARCH_BUTTON);
		clickToElement(AdminManageCustomersPageUIs.SEARCH_BUTTON);
	}
	public boolean isCustomerInfoDisplayed(String info) {
		waitForElementVisiable(AdminManageCustomersPageUIs.DYNAMIC_CUSTOMER_INFO, info);
		return isElementDisplayed(AdminManageCustomersPageUIs.DYNAMIC_CUSTOMER_INFO, info);
	}

}
