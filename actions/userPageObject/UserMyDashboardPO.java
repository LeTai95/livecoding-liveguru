package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.user.UserMyDashboardPageUIs;

public class UserMyDashboardPO extends BasePage{
	WebDriver driver;
	public UserMyDashboardPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public String isSuccessMessageDisplayed() {
		waitForElementVisiable(UserMyDashboardPageUIs.SUCCESS_MESSAGE);
		return getElementText(UserMyDashboardPageUIs.SUCCESS_MESSAGE);
	}
	public void clickToAccountInformationLink() {
		waitForElementClickable(UserMyDashboardPageUIs.ACCOUNT_INFORMATION_LINK);
		clickToElement(UserMyDashboardPageUIs.ACCOUNT_INFORMATION_LINK);
	}
	public String textboxValueByTextboxID(String textboxID) {
		waitForElementVisiable(UserMyDashboardPageUIs.TEXTBOX_VALUE_BY_ID, textboxID);
		return getElementAttribute(UserMyDashboardPageUIs.TEXTBOX_VALUE_BY_ID, "value", textboxID);
	}
	public String isDashboardHeaderTextDisplayed() {
		waitForElementVisiable(UserMyDashboardPageUIs.DASHBOARD_TITLE_TEXT);
		return getElementText(UserMyDashboardPageUIs.DASHBOARD_TITLE_TEXT);
	}

}
