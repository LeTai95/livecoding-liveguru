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
}
