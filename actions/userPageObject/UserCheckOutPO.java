package userPageObject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert.ThrowingRunnable;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.user.UserCheckOutPageUIs;

public class UserCheckOutPO extends BasePage {
	WebDriver driver;

	public UserCheckOutPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterToTextboxByTextboxID(String textboxID, String textValue) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(UserCheckOutPageUIs.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}

	public void selectItemInDropdownByDropdownID(String dropdownID, String textItem) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(UserCheckOutPageUIs.DYNAMIC_DROPDOWN_BY_ID, textItem, dropdownID);
	}

	public void checkToRadioCheckBoxByLabel(String radioCheckboxLabel) {
		waitForElementClickable(UserCheckOutPageUIs.DYNAMIC_RADIO_CHECKBOX_BY_LABEL, radioCheckboxLabel);
		checkToDefautCheckboxRadio(UserCheckOutPageUIs.DYNAMIC_RADIO_CHECKBOX_BY_LABEL, radioCheckboxLabel);
	}

	public void clickToContinueButton(String buttonID) {
		waitForElementClickable(UserCheckOutPageUIs.DYNAMIC_CONTINUE_BUTTON, buttonID);
		clickToElement(UserCheckOutPageUIs.DYNAMIC_CONTINUE_BUTTON, buttonID);
	}

	public boolean isFlatRateDisplayed() {
		waitForElementVisiable(UserCheckOutPageUIs.FLAT_RATE);
		return isElementDisplayed(UserCheckOutPageUIs.FLAT_RATE);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplayed(UserCheckOutPageUIs.DYNAMIC_PRODUCT_NAME, productName);
	}

	public String productPriceByColumnNumberAndRowName(String columnNumber, String rowName) {
		int rowIndex = getElementSize(UserCheckOutPageUIs.DYNAMIC_ROW_INDEX_BY_ROW_NAME, rowName) + 1;
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME,
				String.valueOf(rowIndex), columnNumber);
		return getElementText(UserCheckOutPageUIs.DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME,
				String.valueOf(rowIndex), columnNumber);
	}

	public String productGrandTotalPrice() {
		waitForElementVisiable(UserCheckOutPageUIs.PRODUCT_GRAND_TOTAL_PRICE);
		return getElementText(UserCheckOutPageUIs.PRODUCT_GRAND_TOTAL_PRICE);
	}
	public boolean checkoutProgressInfoByProductInfo(String productInfo, String info) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_PRODUCT_ADDRESS, productInfo, info);
		return isElementDisplayed(UserCheckOutPageUIs.DYNAMIC_PRODUCT_ADDRESS, productInfo, info);
	}

	public UserHomePO clickToPlaceOrderButton() {
		waitForElementClickable(UserCheckOutPageUIs.PLACE_ORDER_BUTTON);
		clickToElement(UserCheckOutPageUIs.PLACE_ORDER_BUTTON);
		return PageGeneraterManager.getUserHomePage(driver);
	}

	public boolean checkoutProgressShippingMethod(String productInfo) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_SHIPPING_METHOD, productInfo);
		return isElementDisplayed(UserCheckOutPageUIs.DYNAMIC_SHIPPING_METHOD, productInfo);
	}

	public boolean checkoutProgressPaymentMethod(String productInfo) {
		waitForElementVisiable(UserCheckOutPageUIs.DYNAMIC_PAYMENT_METHOD, productInfo);
		return isElementDisplayed(UserCheckOutPageUIs.DYNAMIC_PAYMENT_METHOD, productInfo);
	}


}


