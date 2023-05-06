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
	
	public void clickToAddToCompareLinkByProductName(String productName) {
		waitForElementVisiable(UserHomePageUIs.DYNAMMIC_ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
		clickToElement(UserHomePageUIs.DYNAMMIC_ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
	}
	
	public boolean isProductAddToCompareSuccessMessageDisplayed() {
		waitForElementVisiable(UserHomePageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserHomePageUIs.SUCCESS_MESSAGE);
	}
	
	public void clickToCompareButton() {
		waitForElementClickable(UserHomePageUIs.COMPARE_BUTTON);
		clickToElement(UserHomePageUIs.COMPARE_BUTTON);
	}
	
	public UserCompareProductsPO switchToCompareProductWindowsByTitle(String windowsTitle) {
		switchToWindowByPageTitle(windowsTitle);
		return PageGeneraterManager.getCompareProductsPage(driver);
	}
	
	public void switchToHomePageWindowsByTitle(String windowTitle) {
		switchToWindowByPageTitle(windowTitle);
	}
	public boolean isCompareProductsUndisplayed() {
		waitForElementUndisplayed(UserHomePageUIs.COMPARE_PRODUCTS_TITLE);
		return isElementUndisplayed(UserHomePageUIs.COMPARE_PRODUCTS_TITLE);
	}
	public void clickToTVMenuLink() {
		waitForElementClickable(UserHomePageUIs.TV_MENU_LINK);
		clickToElement(UserHomePageUIs.TV_MENU_LINK);
	}
	public UserMyWishlistPO clickToAddToWishlisttByProductName(String productName) {
		waitForElementClickable(UserHomePageUIs.DYNAMIC_ADD_TO_WISHLIST_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(UserHomePageUIs.DYNAMIC_ADD_TO_WISHLIST_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneraterManager.getMyWishlistPage(driver);
	}
	
	public void clickToAddYourReviewLink() {
		waitForElementClickable(UserHomePageUIs.ADD_YOUR_REVIEW_LINK);
		clickToElement(UserHomePageUIs.ADD_YOUR_REVIEW_LINK);
	}
	
	public void checkToQualityRadioButtonByValue(String buttonValue) {
		waitForElementClickable(UserHomePageUIs.DYNAMIC_QUALITY_RADIO_BUTTON_BY_VALUE, buttonValue);
		checkToDefautCheckboxRadio(UserHomePageUIs.DYNAMIC_QUALITY_RADIO_BUTTON_BY_VALUE, buttonValue);
	}
	
	public void enterToTextareaByID(String textareaID, String textValue) {
		waitForElementVisiable(UserHomePageUIs.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		sendkeyToElement(UserHomePageUIs.DYNAMIC_TEXTAREA_BY_ID, textValue, textareaID);
		sleepInSecond(1);
	}
	
	public void clickToSubmitReviewButton() {
		waitForElementClickable(UserHomePageUIs.SUBMIT_REVIEW_BUTTON);
		clickToElement(UserHomePageUIs.SUBMIT_REVIEW_BUTTON);
	}
	
	public boolean isYourReviewAcceptedMessageDisplayed() {
		waitForElementVisiable(UserHomePageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserHomePageUIs.SUCCESS_MESSAGE);
	}
	public void clickToGoToWishlist() {
		waitForElementClickable(UserHomePageUIs.GO_TO_WISHLIST_LINK);
		clickToElementByJS(UserHomePageUIs.GO_TO_WISHLIST_LINK);
	}
	
	public boolean isReceivedSuccessMessageDisplayed() {
		waitForElementVisiable(UserHomePageUIs.ORDER_RECEIVED_SUCCESS_MESSAGE);
		return isElementDisplayed(UserHomePageUIs.ORDER_RECEIVED_SUCCESS_MESSAGE);
	}
	public void clickToAccountMenu() {
		waitForElementVisiable(UserHomePageUIs.ACCOUNT_MENU);
		clickToElement(UserHomePageUIs.ACCOUNT_MENU);
		sleepInSecond(3);
	}
	
	public void clickToWishlistInMyAccountMenu() {
		waitForElementVisiable(UserHomePageUIs.MY_WISHLIST);
		clickToElement(UserHomePageUIs.MY_WISHLIST);
		
	}
	public UserShoppingCartPO clickToAddToCartButton() {
		waitForElementClickable(UserHomePageUIs.ADD_TO_CART_BUTTON);
		clickToElement(UserHomePageUIs.ADD_TO_CART_BUTTON);
		return PageGeneraterManager.getShoppingCartPage(driver);
	}
	public UserAdvancedSearchPO clickToAdvancedSearch() {
		waitForElementClickable(UserHomePageUIs.ADVANCED_SEARCH_LINK);
		clickToElement(UserHomePageUIs.ADVANCED_SEARCH_LINK);
		return PageGeneraterManager.getAdvancedSearchPage(driver);
	}
	public boolean isLoginUnsuccessMessageDisplayed() {
		waitForElementVisiable(UserHomePageUIs.LOGIN_UNSUCCESS_MESSAGE);
		return isElementDisplayed(UserHomePageUIs.LOGIN_UNSUCCESS_MESSAGE);
	}
	public void clickToReviewTab() {
		waitForElementClickable(UserHomePageUIs.REVIEW_TAB);
		clickToElement(UserHomePageUIs.REVIEW_TAB);
	}
	public boolean isReviewDisplay(String reviewValue) {
		waitForElementVisiable(UserHomePageUIs.PRODUCT_REVIEW_VALUE, reviewValue);
		return isElementDisplayed(UserHomePageUIs.PRODUCT_REVIEW_VALUE, reviewValue);
	}

}
