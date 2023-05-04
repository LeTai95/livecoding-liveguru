package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
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

	public void selectItemInDropdownByDropdownID(String dropdownID, String textItem) {
		waitForElementVisiable(UserShoppingCartPageUIs.DYNAMIC_DROPDOWN_BY_DROPDOWN_ID, dropdownID);
		selectItemInDefaultDropdown(UserShoppingCartPageUIs.DYNAMIC_DROPDOWN_BY_DROPDOWN_ID, textItem, dropdownID);
	}

	public void enterToZipTextbox(String zipNumber) {
		waitForElementVisiable(UserShoppingCartPageUIs.ZIP_CODE_TEXTBOX);
		sendkeyToElement(UserShoppingCartPageUIs.ZIP_CODE_TEXTBOX, zipNumber);
	}

	public void clickToEstimateButton() {
		waitForElementClickable(UserShoppingCartPageUIs.ESTIMATE_BUTTON);
		clickToElement(UserShoppingCartPageUIs.ESTIMATE_BUTTON);
	}

	public void checkToFlatRateRadioButton() {
		waitForElementClickable(UserShoppingCartPageUIs.FLAT_RATE_RADIO_BUTTON);
		checkToDefautCheckboxRadio(UserShoppingCartPageUIs.FLAT_RATE_RADIO_BUTTON);
	}

	public void clickToUpdateTotalButton() {
		waitForElementClickable(UserShoppingCartPageUIs.UPDATE_TOTAL_BUTTON);
		clickToElement(UserShoppingCartPageUIs.UPDATE_TOTAL_BUTTON);
	}

	public String productPriceByColumnNumberAndRowName(String columnNumber, String rowName) {
		int rowIndex = getElementSize(UserShoppingCartPageUIs.DYNAMIC_ROW_INDEX_BY_ROW_NAME, rowName) + 1;
		waitForElementVisiable(UserShoppingCartPageUIs.DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME,
				String.valueOf(rowIndex), columnNumber);
		return getElementText(UserShoppingCartPageUIs.DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME,
				String.valueOf(rowIndex), columnNumber);
	}

	public UserCheckOutPO clickToProceedToCheckoutButton() {
		waitForElementClickable(UserShoppingCartPageUIs.PROCEED_TO_CHECK_OUT_BUTTON);
		clickToElement(UserShoppingCartPageUIs.PROCEED_TO_CHECK_OUT_BUTTON);
		return PageGeneraterManager.getCheckOutPage(driver);
	}

	public void clickToCancelButton() {
		waitForElementClickable(UserShoppingCartPageUIs.CANCEL_BUTTON);
		clickToElement(UserShoppingCartPageUIs.CANCEL_BUTTON);
	}



}
