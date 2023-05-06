package pageUI.liveguru.admin;

public class AdminHomePageUIs {
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String LOGIN_BUTTON = "xpath=//input[@class='form-button']";
	public static final String POPUP = "xpath=//div[@class='message-popup-content']";
	public static final String CLOSE_POPUP_BUTTON = "xpath=//a[@title='close']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
	public static final String SEARCH_BUTTON = "xpath=//button[@title='Search']";
	public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//span[text()='%s']/ancestor::span/parent::th/preceding-sibling::th";
	public static final String DYNAMIC_CUSTOMER_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER = "xpath=//table[@id='customerGrid_table']//tbody//tr[%s]//td[%s]";
	public static final String CHECKBOX = "xpath=//input[@class='massaction-checkbox']";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
	public static final String SUBMIT_BUTTON = "xpath=//button[@title='Submit']";
	public static final String CUSTOMER_INFO_DELETED_SUCCESS_MESSAGE = "xpath=//td[text()='No records found.']";
	public static final String LOADING_ICON = "xpath=//div[@id='loading-mask']";
	public static final String FIRST_CHECKBOX = "xpath=//tr[1]//input[@class='massaction-checkbox']";
	public static final String DYNAMIC_OBJECT_BY_NAME = "xpath=//span[text()='%s']/parent::a";
	public static final String EDIT_BUTTON = "xpath=//a[text()='Edit']";

}
