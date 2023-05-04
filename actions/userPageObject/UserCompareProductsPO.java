package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.liveguru.user.UserCompareProductsPageUIs;

public class UserCompareProductsPO extends BasePage {
	WebDriver driver;

	public UserCompareProductsPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isCompareProductsTitleDisplayed() {
		waitForElementVisiable(UserCompareProductsPageUIs.COMPARE_PRODUCTS_TITLE);
		return isElementDisplayed(UserCompareProductsPageUIs.COMPARE_PRODUCTS_TITLE);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisiable(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplayed(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_NAME, productName);
	}

	public String productInfoByColumnNumberAndRowNumber(String productName, String columnNumber, String rowName) {
		int rowIndex = getElementSize(UserCompareProductsPageUIs.DYNAMIC_ROW_INDEX_BY_PRODUCT_NAME_AND_ROW_NAME,
				productName, rowName) + 1;
		waitForElementVisiable(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_INFO_BY_ROW_NAME_ROW_INDEX_AND_COLUMN_NUMBER,
				rowName, String.valueOf(rowIndex), columnNumber);
		return getElementText(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_INFO_BY_ROW_NAME_ROW_INDEX_AND_COLUMN_NUMBER,
				rowName, String.valueOf(rowIndex), columnNumber);
	}

	public boolean productPriceByProductName(String productName, String productPrice) {
		waitForElementVisiable(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME, productName, productPrice);
		return isElementDisplayed(UserCompareProductsPageUIs.DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME, productName, productPrice);
	}

	public UserHomePO clickToCloseWindowButton() {
		waitForElementClickable(UserCompareProductsPageUIs.CLOSE_WINDOW_BUTTON);
		clickToElement(UserCompareProductsPageUIs.CLOSE_WINDOW_BUTTON);
		return PageGeneraterManager.getUserHomePage(driver);
	}

}
