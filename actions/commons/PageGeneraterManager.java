package commons;

import org.openqa.selenium.WebDriver;

import adminPageObject.AdminEditReviewPO;
import adminPageObject.AdminHomePO;
import adminPageObject.AdminOrdersPO;
import userPageObject.UserAdvancedSearchPO;
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
	
	public static UserAdvancedSearchPO getAdvancedSearchPage(WebDriver driver) {
		return new UserAdvancedSearchPO(driver);
	}
	
	public static AdminHomePO getAdminHomePage(WebDriver driver) {
		return new AdminHomePO(driver);
	}
	
	public static AdminOrdersPO getAdminOrdersPage(WebDriver driver) {
		return new AdminOrdersPO(driver);
	}
	
	public static AdminEditReviewPO getAdminEditReviewPage(WebDriver driver) {
		return new AdminEditReviewPO(driver);
	}
	
	
	
	
	
	
	
}
