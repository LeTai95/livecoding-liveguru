package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.user.UserHomePageUIs;

public class UserHomePO extends BasePage {
	WebDriver driver;
	public UserHomePO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void clickToMyAccountLink() {
		waitForElementClickable(UserHomePageUIs.MY_ACCOUNT_LINK);
		clickToElement(UserHomePageUIs.MY_ACCOUNT_LINK);
	}
	public UserRegisterPO clickToCreatAnAccountButton() {
		waitForElementClickable(UserHomePageUIs.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(UserHomePageUIs.CREATE_AN_ACCOUNT_BUTTON);
		return PageGeneraterManager.getUserRegisterPage(driver);
	}

}
