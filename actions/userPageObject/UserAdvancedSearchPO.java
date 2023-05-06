package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.user.UserAdvancedSearchPageUIs;

public class UserAdvancedSearchPO extends BasePage {
	WebDriver driver;
	public UserAdvancedSearchPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void enterToTextboxByID(String textboxID, String textValue) {
		waitForElementVisiable(UserAdvancedSearchPageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(UserAdvancedSearchPageUIs.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}
	public void clickToSearchButton() {
		waitForElementClickable(UserAdvancedSearchPageUIs.SEARCH_BUTTON);
		clickToElement(UserAdvancedSearchPageUIs.SEARCH_BUTTON);
	}
	public String productNumberDisplayed() {
		waitForElementVisiable(UserAdvancedSearchPageUIs.PRODUCT_NUMBER);
		return String.valueOf(getElementSize(UserAdvancedSearchPageUIs.PRODUCT_NUMBER));
	}

}
