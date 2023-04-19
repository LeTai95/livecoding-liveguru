package commons;

import org.openqa.selenium.WebDriver;

import userPageObject.UserHomePO;
import userPageObject.UserMyDashboardPO;
import userPageObject.UserRegisterPO;

public class PageGeneraterManager {
	
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPO(driver);
	}
	
	public static UserMyDashboardPO getUserMyDashboardPage(WebDriver driver) {
		return new UserMyDashboardPO(driver);
	}
}