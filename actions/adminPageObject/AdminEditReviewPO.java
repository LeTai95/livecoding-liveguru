package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.admin.AdminEditReviewPageUIs;

public class AdminEditReviewPO extends BasePage {
	WebDriver driver;
	public AdminEditReviewPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void selectItemByDropdownID(String dropdownID, String textItem) {
		waitForElementVisiable(AdminEditReviewPageUIs.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(AdminEditReviewPageUIs.DYNAMIC_DROPDOWN_BY_ID, textItem, dropdownID);
	}
	public void clickToSaveReview() {
		waitForElementClickable(AdminEditReviewPageUIs.SAVE_REVIEW_BUTTON);
		clickToElement(AdminEditReviewPageUIs.SAVE_REVIEW_BUTTON);
	}
	public boolean isReviewSavedSuccessMessageDisplayed() {
		waitForElementVisiable(AdminEditReviewPageUIs.REVIEW_SAVED_SUCCESS_MESSAGE);
		return isElementDisplayed(AdminEditReviewPageUIs.REVIEW_SAVED_SUCCESS_MESSAGE);
	}

}
