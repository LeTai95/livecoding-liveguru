package pageUI.liveguru.user;

public class UserShoppingCartPageUIs {
	public static final String SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']";
	public static final String DISCOUNT_CODES_TEXTBOX = "xpath=//input[@id='coupon_code']"; 
	public static final String APPLY_BUTTON = "xpath=//span[text()='Apply']/ancestor::button";
	public static final String DYNAMIC_DISCOUNT_ROW_INDEX_BY_ROW_NAME = "xpath=//td[contains(text(),'%s')]/parent::tr/preceding-sibling::tr";
	public static final String DYNAMIC_DISCOUNT_VALUE_BY_ROW_INDEX_AND_COLUMN_NUMBER = "xpath=//div[@class='cart-totals']//tbody//tr[%s]//td[%s]/span";
	public static final String GRAND_TOTAL_PRICE_VALUE = "xpath=//strong[text()='Grand Total']/parent::td/following-sibling::td/strong/span";
	public static final String QTY_TEXTBOX = "xpath=//input[@class='input-text qty']";
	public static final String UPDATE_BUTTON = "xpath=//button[@class='button btn-update']";
	public static final String ERROR_MESSAGE = "xpath=//li[@class='error-msg']";
	public static final String ERROR_ITEM_MESSAGE = "xpath=//p[@class='item-msg error']";
	public static final String REMOVE_ICON = "xpath=//td[contains(@class,'product-cart-remove')]//a[contains(@class,'btn-remove')]";
	public static final String SHOPPING_CART_EMPTY_MESSAGE = "xpath=//div[@class='cart-empty']";
	public static final String DYNAMIC_DROPDOWN_BY_DROPDOWN_ID = "xpath=//select[@id='%s']";
	public static final String ZIP_CODE_TEXTBOX = "xpath=//input[@id='postcode']";
	public static final String ESTIMATE_BUTTON = "xpath=//span[text()='Estimate']/ancestor::button";
	public static final String FLAT_RATE_RADIO_BUTTON = "xpath=//input[@id='s_method_flatrate_flatrate']";
	public static final String UPDATE_TOTAL_BUTTON = "xpath=//span[text()='Update Total']/ancestor::button";
	public static final String DYNAMIC_ROW_INDEX_BY_ROW_NAME = "xpath=//td[contains(text(),'%s')]/parent::tr/preceding-sibling::tr";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME = "xpath=//table[@id='shopping-cart-totals-table']//tbody//tr[%s]//td[%s]//span";
	public static final String PROCEED_TO_CHECK_OUT_BUTTON = "xpath=//li[contains(@class,'bottom')]//button[contains(@class,'btn-checkout')]";
	public static final String CANCEL_BUTTON = "xpath=//button[contains(@class,'cancel-coupon')]";










}
