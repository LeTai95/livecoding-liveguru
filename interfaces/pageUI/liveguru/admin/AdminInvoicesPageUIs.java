package pageUI.liveguru.admin;

public class AdminInvoicesPageUIs {
	public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String SORT_OPTION = "xpath=//span[text()='%s']/parent::a";
	public static final String LOADING_ICON = "xpath=//p[@id='loading_mask_loader']";
	public static final String INVOICE_COLUMN_BY_COLUMN_INDEX = "xpath=//div[@class='grid']//tbody//tr/td[%s]";

}
