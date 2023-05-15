package pageUI.liveguru.admin;

public class AdminInvoicesPageUIs {
	public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String DYNAMIC_INVOICES_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER = "xpath=//div[@class='grid']//tbody//tr[%s]/td[%s]";
	public static final String ROW_NUMBER = "xpath=//div[@class='grid']//tbody//tr";
	public static final String SORT_OPTION = "xpath=//span[text()='%s']/ancestor::span";
	public static final String LOADING_ICON = "xpath=//p[@id='loading_mask_loader']";


}
