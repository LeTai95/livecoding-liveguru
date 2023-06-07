package adminPageObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public void clickToSortOptionByColumnName(String columnName) {
		waitForElementClickable(AdminInvoicesPageUIs.SORT_OPTION, columnName);
		clickToElementByJS(AdminInvoicesPageUIs.SORT_OPTION, columnName);
	}
	
	public boolean isInvoiceNumberSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> invoicesNumberUIList = new ArrayList<String>();
		List<WebElement> invoicesNumber = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement invoice : invoicesNumber) {
			invoicesNumberUIList.add(invoice.getText());
		}
		ArrayList<String> invoicesNumberSort = new ArrayList<String>();
		for (String invoice : invoicesNumberUIList) {
			invoicesNumberSort.add(invoice);
		}
		Collections.sort(invoicesNumberSort);
		return invoicesNumberSort.equals(invoicesNumberUIList);
	}

	public boolean isInvoiceNumberSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> invoicesNumberUIList = new ArrayList<String>();
		List<WebElement> invoicesNumber = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement invoice : invoicesNumber) {
			invoicesNumberUIList.add(invoice.getText());
		}
		ArrayList<String> invoicesNumberSort = new ArrayList<String>();
		for (String invoice : invoicesNumberUIList) {
			invoicesNumberSort.add(invoice);
		}
		Collections.sort(invoicesNumberSort);
		Collections.reverse(invoicesNumberSort);
		return invoicesNumberSort.equals(invoicesNumberUIList);
	}


	public boolean isOrderNumberSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> orderNumberUIList = new ArrayList<String>();
		List<WebElement> orderNumber = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement number : orderNumber) {
			orderNumberUIList.add(number.getText());
		}
		
		ArrayList<String> orderNumberSort = new ArrayList<String>();
		for (String number : orderNumberUIList) {
			orderNumberSort.add(number);
		}
		Collections.sort(orderNumberSort);
		return orderNumberSort.equals(orderNumberUIList);
	}

	public boolean isOrderNumberSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> orderNumberUIList = new ArrayList<String>();
		List<WebElement> orderNumber = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement number : orderNumber) {
			orderNumberUIList.add(number.getText());
		}
		ArrayList<String> orderNumberSort = new ArrayList<String>();
		for (String number : orderNumberUIList) {
			orderNumberSort.add(number);
		} 
		Collections.sort(orderNumberSort);
		Collections.reverse(orderNumberSort);
		return orderNumberSort.equals(orderNumberUIList);
	}

	public boolean isBillToNameSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> billToNameUIList = new ArrayList<String>();
		List<WebElement> billToName = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement bill : billToName) {
			billToNameUIList.add(bill.getText().toLowerCase());
		}
		ArrayList<String> billToNameSort = new ArrayList<String>();
		for (String bill : billToNameUIList) {
			billToNameSort.add(bill);
		}
		Collections.sort(billToNameSort);
		return billToNameSort.equals(billToNameUIList);
	}

	public boolean isBillToNameSortSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> billToNameUIList = new ArrayList<String>();
		List<WebElement> billToName = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement bill : billToName) {
			billToNameUIList.add(bill.getText().toLowerCase());
		}
		ArrayList<String> billToNameSort = new ArrayList<String>();
		for (String bill : billToNameUIList) {
			billToNameSort.add(bill);
		}
		Collections.sort(billToNameSort);
		Collections.reverse(billToNameSort);
		return billToNameSort.equals(billToNameUIList);
	}

	public boolean isAmountSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<Float> priceUIList = new ArrayList<Float>();
		List<WebElement> priceUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement price : priceUI) {
			priceUIList.add(Float.parseFloat(price.getText().replace("$", "").replace(",", "")));
		}
		ArrayList<Float> priceSort = new ArrayList<Float>();
		for (Float price : priceUIList) {
			priceSort.add(price);
		} 
		Collections.sort(priceSort);
		return priceSort.equals(priceUIList);
	}

	public boolean isAmountSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<Float> priceUIList = new ArrayList<Float>();
		List<WebElement> priceUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement price : priceUI) {
			priceUIList.add(Float.parseFloat(price.getText().replace("$", "").replace(",", "")));
		}
		ArrayList<Float> priceSort = new ArrayList<Float>();
		for (Float price : priceUIList) {
			priceSort.add(price);
		}
		Collections.sort(priceSort);
		Collections.reverse(priceSort);
		return priceSort.equals(priceUIList);
	}

	public boolean isInvoiceDateSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> dateUIList = new ArrayList<String>();	
		List<WebElement> dateUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement webElement : dateUI) {
			dateUIList.add(webElement.getText());
		}
		ArrayList<String> dateSortList = new ArrayList<String>();
		dateSortList.addAll(dateUIList);
		Collections.sort(dateSortList, new Comparator<String>() {
			DateFormat f = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			@Override
			public int compare(String date1, String date2) {
				try {
					return f.parse(date1).compareTo(f.parse(date2));
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		return dateSortList.equals(dateUIList);
	}

	public boolean isInvoiceDateSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> dateUIList = new ArrayList<String>();	
		List<WebElement> dateUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement webElement : dateUI) {
			dateUIList.add(webElement.getText());
		}
		ArrayList<String> dateSortList = new ArrayList<String>();
		dateSortList.addAll(dateUIList);
		Collections.sort(dateSortList, new Comparator<String>() {
			DateFormat f = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			@Override
			public int compare(String date1, String date2) {
				try {
					return f.parse(date1).compareTo(f.parse(date2));
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		Collections.reverse(dateSortList);
		return dateSortList.equals(dateUIList);
	}

	public boolean isOrderDateSortByAscending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> dateUIList = new ArrayList<String>();	
		List<WebElement> dateUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		for (WebElement webElement : dateUI) {
			dateUIList.add(webElement.getText());
		}
		ArrayList<String> dateSortList = new ArrayList<String>();
		dateSortList.addAll(dateUIList);
		Collections.sort(dateSortList, new Comparator<String>() {
			DateFormat f = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			@Override
			public int compare(String date1, String date2) {
				try {
					return f.parse(date1).compareTo(f.parse(date2));
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		return dateSortList.equals(dateUIList);
	}

	public boolean isOrderDateSortByDescending(String columnName) {
		waitForElementInvisiable(AdminInvoicesPageUIs.LOADING_ICON);
		int columnIndex = getElementSize(AdminInvoicesPageUIs.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		ArrayList<String> dateUIList = new ArrayList<String>();	
		List<WebElement> dateUI = getListWebElement(AdminInvoicesPageUIs.INVOICE_COLUMN_BY_COLUMN_INDEX, String.valueOf(columnIndex)	);
		for (WebElement webElement : dateUI) {
			dateUIList.add(webElement.getText());
		}
		ArrayList<String> dateSortList = new ArrayList<String>();
		dateSortList.addAll(dateUIList);
		Collections.sort(dateSortList, new Comparator<String>() {
			DateFormat f = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			@Override
			public int compare(String date1, String date2) {
				try {
					return f.parse(date1).compareTo(f.parse(date2));
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		Collections.reverse(dateSortList);
		return dateSortList.equals(dateUIList);
	}
	
	
}
