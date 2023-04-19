package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.user.UserRegisterPageUIs;

public class UserRegisterPO extends BasePage{
	WebDriver driver;
	public UserRegisterPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void enterToTextboxByTextboxID(String textboxID, String textValue) {
		waitForElementVisiable(UserRegisterPageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(UserRegisterPageUIs.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}
	public UserMyDashboardPO clickToRegisterButton() {
		waitForElementClickable(UserRegisterPageUIs.REGISTER_BUTTON);
		clickToElement(UserRegisterPageUIs.REGISTER_BUTTON);
		return PageGeneraterManager.getUserMyDashboardPage(driver);
	}

}
