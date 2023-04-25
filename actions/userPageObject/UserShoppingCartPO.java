package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveguru.user.UserShoppingCartPageUIs;

public class UserShoppingCartPO extends BasePage {
	WebDriver driver;

	public UserShoppingCartPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductAddedToCartSuccessMessageDisplayed() {
		waitForElementVisiable(UserShoppingCartPageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserShoppingCartPageUIs.SUCCESS_MESSAGE);
	}

	public void enterToDiscountCodesTextbox(String couponCode) {
		waitForElementVisiable(UserShoppingCartPageUIs.DISCOUNT_CODES_TEXTBOX);
		sendkeyToElement(UserShoppingCartPageUIs.DISCOUNT_CODES_TEXTBOX, couponCode);
	}

	public boolean isCouponCodeAppliedSuccessMessageDisplayed() {
		waitForElementVisiable(UserShoppingCartPageUIs.SUCCESS_MESSAGE);
		return isElementDisplayed(UserShoppingCartPageUIs.SUCCESS_MESSAGE);
	}

	public void clickToApplyButton() {
		waitForElementClickable(UserShoppingCartPageUIs.APPLY_BUTTON);
		clickToElement(UserShoppingCartPageUIs.APPLY_BUTTON);
	}

	public String getDiscountPriceValueByColumnNumberAndRowName(String columnNumber, String rowName) {
		int rowIndex = getElementSize(UserShoppingCartPageUIs.DYNAMIC_DISCOUNT_ROW_INDEX_BY_ROW_NAME, rowName) + 1;
		waitForElementVisiable(UserShoppingCartPageUIs.DYNAMIC_DISCOUNT_VALUE_BY_ROW_INDEX_AND_COLUMN_NUMBER,
				String.valueOf(rowIndex), columnNumber);
		return getElementText(UserShoppingCartPageUIs.DYNAMIC_DISCOUNT_VALUE_BY_ROW_INDEX_AND_COLUMN_NUMBER,
				String.valueOf(rowIndex), columnNumber);
	}

	public String getGrandTotalPrice() {
		waitForElementVisiable(UserShoppingCartPageUIs.GRAND_TOTAL_PRICE_VALUE);
		return getElementText(UserShoppingCartPageUIs.GRAND_TOTAL_PRICE_VALUE);
	}

	public void enterToQTYTextbox(String textValue) {
		waitForElementClickable(UserShoppingCartPageUIs.QTY_TEXTBOX);
		clickToElement(UserShoppingCartPageUIs.QTY_TEXTBOX);
		sleepInSecond(1);
		sendkeyToElement(UserShoppingCartPageUIs.QTY_TEXTBOX, textValue);
	}

	public boolean isErrorMessageDisplayed() {
		waitForElementVisiable(UserShoppingCartPageUIs.ERROR_MESSAGE);
		return isElementDisplayed(UserShoppingCartPageUIs.ERROR_MESSAGE);
	}

	public boolean isErrorItemMessageDisplayed() {
		waitForElementVisiable(UserShoppingCartPageUIs.ERROR_ITEM_MESSAGE);
		return isElementDisplayed(UserShoppingCartPageUIs.ERROR_ITEM_MESSAGE);
	}

	public void clickToDeleteButton() {
		waitForElementClickable(UserShoppingCartPageUIs.REMOVE_ICON);
		clickToElement(UserShoppingCartPageUIs.REMOVE_ICON);
	}

	public boolean isEmptyShoppingCartMessageDisplayed() {
		waitForElementVisiable(UserShoppingCartPageUIs.SHOPPING_CART_EMPTY_MESSAGE);
		return isElementDisplayed(UserShoppingCartPageUIs.SHOPPING_CART_EMPTY_MESSAGE);
	}

	public void clickToUpdateButton() {
		waitForElementClickable(UserShoppingCartPageUIs.UPDATE_BUTTON);
		clickToElement(UserShoppingCartPageUIs.UPDATE_BUTTON);
	}

}
