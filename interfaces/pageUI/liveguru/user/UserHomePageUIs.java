package pageUI.liveguru.user;

public class UserHomePageUIs {
	public static final String MY_ACCOUNT_LINK = "xpath=//div[@class='footer']//a[text()='My Account']";
	public static final String CREATE_AN_ACCOUNT_BUTTON = "xpath=//a[@title='Create an Account']";
	public static final String MOBILE_MENU_LINK = "xpath=//a[text()='Mobile']";
	public static final String DYNAMIC_PRODUCT_COST_IN_MOBILE_MENU_BY_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div/span/span";
	public static final String DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME = "xpath=//a[text()='%s']";
	public static final String PRODUCT_DETAIL_COST = "xpath=//span[@class='price']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String LOGIN_BUTTON = "xpath=//button[@id='send2']";
	public static final String DYNAMIC_ADD_TO_CART_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::div[@class='product-info']//button";

}
