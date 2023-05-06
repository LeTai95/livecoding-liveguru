package pageUI.liveguru.user;

public class UserCheckOutPageUIs {
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
	public static final String DYNAMIC_RADIO_CHECKBOX_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CONTINUE_BUTTON = "xpath=//div[@id='%s']//button";
	public static final String FLAT_RATE = "xpath=//div[@id='checkout-shipping-method-load']";
	public static final String SHIPPING_METHOD_CONTINUE_BUTTON = "xpath=//div[@id='shipping-method-buttons-container']//button";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//td//h3[text()='%s']";
	public static final String DYNAMIC_ROW_INDEX_BY_ROW_NAME = "xpath=//td[contains(text(),'%s')]/parent::tr/preceding-sibling::tr";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_ROW_INDEX_AND_COLUMN_NAME = "xpath=//tfoot//tr[%s]/td[%s]//span";
	public static final String PRODUCT_GRAND_TOTAL_PRICE = "xpath=//strong[text()='Grand Total']/parent::td/following-sibling::td//strong//span";
	public static final String DYNAMIC_PRODUCT_ADDRESS = "xpath=//dt[contains(text(),'%s')]/following-sibling::dd/address[contains(text(),'%s')]";
	public static final String DYNAMIC_SHIPPING_METHOD = "xpath=//dt[contains(text(),'Shipping Method')]/following-sibling::dd[contains(text(),'%s')]";
	public static final String DYNAMIC_PAYMENT_METHOD = "xpath=//dt[contains(text(),'Payment Method')]/following-sibling::dd//p[contains(text(),'%s')]";
	public static final String PLACE_ORDER_BUTTON = "xpath=//span[text()='Place Order']/ancestor::button";
}
