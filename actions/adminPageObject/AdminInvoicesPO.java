package adminPageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.liveguru.admin.AdminInvoicesPageUIs;

public class AdminInvoicesPO extends BasePage {
	WebDriver driver;

	public AdminInvoicesPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isInvoiceInfoSortByAscendingByColumnName(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		int rowNumber = getElementSize(AdminInvoicesPageUIs.ROW_NUMBER);
		ArrayList<String> invoicesInfoUIList = new ArrayList<String>();
		for (int i = 1; i <= rowNumber; i++) {
			List<WebElement> invoicesInfoText = getListWebElement(
					AdminInvoicesPageUIs.DYNAMIC_INVOICES_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER, String.valueOf(i),
					String.valueOf(columnIndex));
			for (WebElement invoice : invoicesInfoText) {
				invoicesInfoUIList.add(invoice.getText());
			}
		}
		ArrayList<String> invoicesInfoSort = new ArrayList<String>();
		for (String string : invoicesInfoUIList) {
			invoicesInfoSort.add(string);
		}
		Collections.sort(invoicesInfoSort);
		return invoicesInfoSort.equals(invoicesInfoUIList);
	}

	public void clickToSortOptionByColumnName(String columnName) {
		waitForElementVisiable(AdminInvoicesPageUIs.SORT_OPTION, columnName);
		clickToElementByJS(AdminInvoicesPageUIs.SORT_OPTION, columnName);
	}

	public boolean isInvoiceInfoSortByDescendingByColumnName(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		int rowNumber = getElementSize(AdminInvoicesPageUIs.ROW_NUMBER);
		ArrayList<String> invoicesInfoUIList = new ArrayList<String>();
		for (int i = 1; i <= rowNumber; i++) {
			List<WebElement> invoicesInfoText = getListWebElement(
					AdminInvoicesPageUIs.DYNAMIC_INVOICES_INFO_BY_COLUMN_INDEX_AND_ROW_NUMBER, String.valueOf(i),
					String.valueOf(columnIndex));
			for (WebElement invoice : invoicesInfoText) {
				invoicesInfoUIList.add(invoice.getText());
			}
		}
		ArrayList<String> invoicesInfoSort = new ArrayList<String>();
		for (String string : invoicesInfoUIList) {
			invoicesInfoSort.add(string);
		}
		Collections.sort(invoicesInfoSort);
		Collections.reverse(invoicesInfoSort);
		return invoicesInfoSort.equals(invoicesInfoUIList);
	}

}
