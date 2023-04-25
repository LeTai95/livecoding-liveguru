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
	public void clickToMobileMenuLink() {
		waitForElementClickable(UserHomePageUIs.MOBILE_MENU_LINK);
		clickToElement(UserHomePageUIs.MOBILE_MENU_LINK);
	}
	public String getProductPriceByProductName(String productName) {
		waitForElementVisiable(UserHomePageUIs.DYNAMIC_PRODUCT_COST_IN_MOBILE_MENU_BY_NAME, productName);
		return getElementText(UserHomePageUIs.DYNAMIC_PRODUCT_COST_IN_MOBILE_MENU_BY_NAME, productName);
	}
	public void clickOnProductDetailByProducrName(String productName) {
		waitForElementClickable(UserHomePageUIs.DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME, productName);
		clickToElement(UserHomePageUIs.DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME, productName);
	}
	public String getDetailProductPrice() {
		waitForElementVisiable(UserHomePageUIs.PRODUCT_DETAIL_COST);
		return getElementText(UserHomePageUIs.PRODUCT_DETAIL_COST);
	}
	public void enterToTextboxByTextboxID(String textboxID, String textValue) {
		waitForElementVisiable(UserHomePageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(UserHomePageUIs.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}
	public UserMyDashboardPO clickToLoginButton() {
		waitForElementClickable(UserHomePageUIs.LOGIN_BUTTON);
		clickToElement(UserHomePageUIs.LOGIN_BUTTON);
		return PageGeneraterManager.getUserMyDashboardPage(driver);
	}
	public UserShoppingCartPO clickToAddToCartByProductName(String productName) {
		waitForElementClickable(UserHomePageUIs.DYNAMIC_ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(UserHomePageUIs.DYNAMIC_ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneraterManager.getShoppingCartPage(driver);
	}

}
