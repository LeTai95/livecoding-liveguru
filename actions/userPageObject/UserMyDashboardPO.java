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

}
