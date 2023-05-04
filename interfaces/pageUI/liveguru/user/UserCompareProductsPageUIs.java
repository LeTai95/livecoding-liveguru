package pageUI.liveguru.user;

public class UserCompareProductsPageUIs {
	public static final String COMPARE_PRODUCTS_TITLE = "xpath=//h1[text()='Compare Products']";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//h2/a[text()='%s']";
	public static final String DYNAMIC_ROW_INDEX_BY_PRODUCT_NAME_AND_ROW_NAME = "xpath=//a[text()='%s']/ancestor::tbody/following-sibling::tbody//span[text()='%s']/ancestor::tr/preceding-sibling::tr";
	public static final String DYNAMIC_PRODUCT_INFO_BY_ROW_NAME_ROW_INDEX_AND_COLUMN_NUMBER = "xpath=//span[text()='%s']/ancestor::tbody/tr[%s]/td[%s]/div";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/ancestor::tbody/following-sibling::tbody//div[@class='price-box']//span[text()='%s']";
	public static final String CLOSE_WINDOW_BUTTON = "xpath=//span[text()='Close Window']/ancestor::button";
}
