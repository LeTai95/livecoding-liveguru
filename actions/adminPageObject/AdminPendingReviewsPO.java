package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.admin.AdminHomePageUIs;
import pageUI.liveguru.admin.AdminPendingReviewsPageUIs;

public class AdminPendingReviewsPO extends BasePage {
	WebDriver driver;
	public AdminPendingReviewsPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(AdminPendingReviewsPageUIs.SEARCH_BUTTON);
		clickToElement(AdminPendingReviewsPageUIs.SEARCH_BUTTON);
	}

	public AdminEditReviewPO clickToEditButton() {
		waitForElementClickable(AdminPendingReviewsPageUIs.EDIT_BUTTON);
		clickToElement(AdminPendingReviewsPageUIs.EDIT_BUTTON);
		return PageGeneraterManager.getAdminEditReviewPage(driver);
	}

	public void enterToTextboxByName(String textboxName, String textValue) {
		waitForElementVisiable(AdminPendingReviewsPageUIs.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendkeyToElement(AdminPendingReviewsPageUIs.DYNAMIC_TEXTBOX_BY_NAME, textValue, textboxName);
	}

}

