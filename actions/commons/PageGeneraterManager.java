package commons;

import org.openqa.selenium.WebDriver;

import userPageObject.UserCheckOutPO;
import userPageObject.UserCompareProductsPO;
import userPageObject.UserHomePO;
import userPageObject.UserMyDashboardPO;
import userPageObject.UserMyWishlistPO;
import userPageObject.UserRegisterPO;
import userPageObject.UserShoppingCartPO;

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
	
	public static UserShoppingCartPO getShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPO(driver);
	}
	
	public static UserCompareProductsPO getCompareProductsPage(WebDriver driver) {
		return new UserCompareProductsPO(driver);
	}
	
	public static UserMyWishlistPO getMyWishlistPage(WebDriver driver) {
		return new UserMyWishlistPO(driver);
	}
	
	public static UserCheckOutPO getCheckOutPage(WebDriver driver) {
		return new UserCheckOutPO(driver);
	}
}
