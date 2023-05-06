package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.user.UserMyWishlistPageUIs;

public class UserMyWishlistPO extends BasePage {
	WebDriver driver;
	public UserMyWishlistPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public boolean isProductAddedToWishlistSuccessMessageDisplayed() {
		waitForElementVisiable(UserMyWishlistPageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserMyWishlistPageUIs.SUCCESS_MESSAGE);
	}
	public void clickToShareWishlistButton() {
		waitForElementVisiable(UserMyWishlistPageUIs.SHARE_WISHLIST_BUTTON);
		clickToElement(UserMyWishlistPageUIs.SHARE_WISHLIST_BUTTON);
	}
	public void enterToTextareaByID(String textareaID, String textValue) {
		waitForElementVisiable(UserMyWishlistPageUIs.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		sendkeyToElement(UserMyWishlistPageUIs.DYNAMIC_TEXTAREA_BY_ID, textValue, textareaID);
	}
	public boolean isWishlistSharedSuccessMessageDisplayed() {
		waitForElementVisiable(UserMyWishlistPageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserMyWishlistPageUIs.SUCCESS_MESSAGE);
	}
	public boolean isProductDisplayedByProductName(String productName) {
		waitForElementVisiable(UserMyWishlistPageUIs.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplayed(UserMyWishlistPageUIs.DYNAMIC_PRODUCT_NAME, productName);
	}

}
 