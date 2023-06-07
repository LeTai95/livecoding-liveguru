package com.liveguru.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.livegure.data.LiveGuruData;

import adminPageObject.AdminEditReviewPO;
import adminPageObject.AdminHomePO;
import adminPageObject.AdminInvoicesPO;
import adminPageObject.AdminManageCustomersPO;
import adminPageObject.AdminOrdersPO;
import adminPageObject.AdminPendingReviewsPO;
import commons.BaseTest;
import commons.PageGeneraterManager;
import reportConfig.ExtentTestManager;
import userPageObject.UserHomePO;
import userPageObject.UserRegisterPO;

@Listeners(commons.MethodListener.class)
public class LiveGuru_Admin_Role extends BaseTest {
	WebDriver driver;
	String email, BEurl, FEurl, reviewNickname, reviewValue, reviewSummary;
	AdminHomePO adminHomePage;
	AdminOrdersPO adminOrdersPage;
	AdminEditReviewPO adminEditReviewPage;
	AdminInvoicesPO adminInvoicesPage;
	AdminPendingReviewsPO adminPendingReviewsPage;
	AdminManageCustomersPO adminManageCustomersPage;
	UserHomePO userHomePage;
	UserRegisterPO userRegisterPage;

	@Parameters({ "browser", "FEenviroment", "BEenviroment" })
	@BeforeClass
	public void beforeClass(String browserName, String userEvr, String adminEvrd) {
		driver = getBrowserDriver(browserName, userEvr);
		userHomePage = PageGeneraterManager.getUserHomePage(driver);
		email = LiveGuruData.UserData.EMAIL + getRandomNumberByDateTime() + "@gmail.com";
		BEurl = getEnvironmentUrl(adminEvrd);
		FEurl = getEnvironmentUrl(userEvr);
		reviewNickname = "Tester" + getRandomNumberByDateTime();
		reviewValue = "Hello" + getRandomNumberByDateTime();
		reviewSummary = "Word" + getRandomNumberByDateTime();
	}

	@Test
	public void TC_01_Create_Account_And_Check_Account_Created_Success(Method method) {
		ExtentTestManager.startTest(method.getName(), "Create new account and verify");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Click to 'My Account' link");
		userHomePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Click to 'Create an Account' button");
		userRegisterPage = userHomePage.clickToCreatAnAccountButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Step_03: Enter to 'First Name' textbox with value is'" + LiveGuruData.UserData.FIRST_NAME + "'");
		userRegisterPage.enterToTextboxByTextboxID("firstname", LiveGuruData.UserData.FIRST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Step_04: Enter to 'Last Name' textboxis'" + LiveGuruData.UserData.LAST_NAME + "'");
		userRegisterPage.enterToTextboxByTextboxID("lastname", LiveGuruData.UserData.LAST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Step_05: Enter to 'Email Address' textbox with value is'" + email + "'");
		userRegisterPage.enterToTextboxByTextboxID("email_address", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Step_06: Enter to 'Password' textbox with value is'" + LiveGuruData.UserData.PASSWORD + "'");
		userRegisterPage.enterToTextboxByTextboxID("password", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO,
				"Step_07: Enter to 'Confirm Password' textbox with value is'" + LiveGuruData.UserData.PASSWORD + "'");
		userRegisterPage.enterToTextboxByTextboxID("confirmation", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Click to 'Register' button");
		userRegisterPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Open BackEnd site");
		adminHomePage = userRegisterPage.openBackEndSite(BEurl);

		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Enter to 'User Name' textbox with value is 'user01'");
		adminHomePage.enterToTextboxByID("username", "user01");

		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Enter to 'Password' textbox with value is 'guru99com'");
		adminHomePage.enterToTextboxByID("login", "guru99com");

		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Click to 'Login' button");
		adminHomePage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Verify popup is displayed");
		Assert.assertTrue(adminHomePage.isPopupDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Close popup");
		adminHomePage.closePopup();

		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Enter to 'Email' textbox with value is '" + email + "'");
		adminHomePage.enterToTextboxByName("email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Click to 'Search' button");
		adminHomePage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Verify 'Email' value equals '" + email + "'");
		Assert.assertEquals(adminHomePage.customerInfoByColumnNameAndRowNumber("Email", "1"), email);

		ExtentTestManager.getTest().log(Status.INFO, "Step_18: Verify 'Name' value equals '"
				+ LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME + "'");
		Assert.assertEquals(adminHomePage.customerInfoByColumnNameAndRowNumber("Name", "1"),
				LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_19: Check to checkbox");
		adminHomePage.checkToCheckbox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_20: Select 'Delete' in 'Actions' dropdown");
		adminHomePage.selectItemInDropdownByDropdownID("customerGrid_massaction-select", "Delete");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_21: Click to 'Submit' button");
		adminHomePage.clickToSubmitButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_22: Accept alert");
		adminHomePage.acceptAlert();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_23: Verify customer info is deleted success");
		Assert.assertTrue(adminHomePage.isCustomerInfoDeletedSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_24: Open FontEnd site");
		userHomePage = adminHomePage.openFontEndSite(FEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_25: Click to 'My Account' link");
		userHomePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_26: Enter 'Email Address' textbox with value is '" + email + "'");
		userHomePage.enterToTextboxByTextboxID("email", email);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_27: Enter 'Password' textbox with value is '" + LiveGuruData.UserData.PASSWORD + "'");
		userHomePage.enterToTextboxByTextboxID("pass", LiveGuruData.UserData.PASSWORD);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_28: Click to 'Login' button");
		userHomePage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_29: Verify login unsuccess message is displayed");
		Assert.assertTrue(userHomePage.isLoginUnsuccessMessageDisplayed());
		
	}
	
	@Test
	public void TC_02_Verify_Invoice_Can_Be_Printed(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify invoice can be printed");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open BackEnd site");
		adminHomePage = userRegisterPage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Hover to 'Sales'");
		adminHomePage.hoverToMenuByName("Sales");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Orders'");
		adminOrdersPage = (AdminOrdersPO) adminHomePage.clickToPageByName("Orders");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Select 'Canceled' in 'Status' dropdown");
		adminOrdersPage.selectItemInDropdownByDropdownID("sales_order_grid_filter_status", "Canceled");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Click to 'Search' button");
		adminOrdersPage.clickToButtonByName("Search");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Check to the first checkbox");
		adminOrdersPage.checkToTheFirstCheckbox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Select 'Print Invoices' in 'Actions' dropdown");
		adminOrdersPage.selectItemInDropdownByDropdownID("sales_order_grid_massaction-select", "Print Invoices");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Click to 'Submit' button");
		adminOrdersPage.clickToButtonByName("Submit");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Verify error message is displayed");
		Assert.assertTrue(adminOrdersPage.isErrorMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Select 'Complete' in 'Status' dropdown");
		adminOrdersPage.selectItemInDropdownByDropdownID("sales_order_grid_filter_status", "Complete");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Click to 'Search' button");
		adminOrdersPage.clickToButtonByName("Search");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Check to the first checkbox");
		adminOrdersPage.checkToTheFirstCheckbox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Select 'Print Invoices' in 'Actions' dropdown");
		adminOrdersPage.selectItemInDropdownByDropdownID("sales_order_grid_massaction-select", "Print Invoices");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Click to 'Submit' button");
		adminOrdersPage.clickToButtonByName("Submit");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Get Admin Home Page ID");
		String adminHomePageID = driver.getWindowHandle();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Verify invoice is downloaded");
		Assert.assertTrue(adminOrdersPage.isInvoiceDownloaded("invoice"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Close invoice tab");
		adminOrdersPage.closeInvoiceTab(adminHomePageID);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_18: Clear 'Status' dropdown");
		adminOrdersPage.selectItemInDropdownByDropdownID("sales_order_grid_filter_status", "");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_19: Click to 'Search' button");
		adminOrdersPage.clickToButtonByName("Search");
		
	}
	
	@Test
	public void TC_03_Verify_The_Product_Review_Merchanism(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify the product review merchanism");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open FontEnd site");
		userHomePage = adminOrdersPage.openFontEndSite(FEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Sony Xperia' detail");
		userHomePage.clickOnProductDetailByProducrName("Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Click to 'Add your review' link");
		userHomePage.clickToAddYourReviewLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Click to 'Quality' radio button");
		userHomePage.checkToQualityRadioButtonByValue("5");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Enter to 'Let us know your thoughts' textarea with value is '" + reviewValue + "'");
		userHomePage.enterToTextareaByID("review_field", reviewValue);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Enter to 'Summary of Your Review' textarea with value is '" + reviewSummary + "'");
		userHomePage.enterToTextboxByTextboxID("summary_field", reviewSummary);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Enter to 'What's your nickname?' textarea with value is '" + reviewNickname + "'");
		userHomePage.enterToTextboxByTextboxID("nickname_field", reviewNickname);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Click to 'Submit Review' button");
		userHomePage.clickToSubmitReviewButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Open BackEnd site");
		adminHomePage = userHomePage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Hover to 'Catalog' menu");
		adminHomePage.hoverToMenuByName("Catalog");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Hover to 'Reviews and Ratings' menu");
		adminHomePage.hoverToMenuByName("Reviews and Ratings");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Click to 'Pending Reviews'");
		adminHomePage.hoverToMenuByName("Customer Reviews");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Click to 'Pending Reviews'");
		adminPendingReviewsPage = (AdminPendingReviewsPO) adminHomePage.clickToPageByName("Pending Reviews");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Enter to 'Nickname' textbox with value is '" + reviewNickname + "'");
		adminPendingReviewsPage.enterToTextboxByName("nickname", reviewNickname);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Click to 'Search' button");
		adminPendingReviewsPage.clickToSearchButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Click to 'Edit' button");
		adminEditReviewPage = adminPendingReviewsPage.clickToEditButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_18: Select 'Approved' in 'Status' dropdown");
		adminEditReviewPage.selectItemByDropdownID("status_id", "Approved");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_19: Click to 'Save Review' button");
		adminEditReviewPage.clickToSaveReview();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_20: Verify 'The review has been saved' message is displayed");
		Assert.assertTrue(adminEditReviewPage.isReviewSavedSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_21: Open FontEnd site");
		userHomePage = adminEditReviewPage.openFontEndSite(FEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_22: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_23: Click to 'Sony Xperia' detail");
		userHomePage.clickOnProductDetailByProducrName("Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_24: Click to 'Review' tab");
		userHomePage.clickToReviewTab();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_25: Verify '" + reviewValue +  " is displayed");
		Assert.assertTrue(userHomePage.isReviewDisplay(reviewValue));
		
	}
	
	@Test
	public void TC_04_Sort_Function(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify sort function");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open BackEnd site");
		adminHomePage = userHomePage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Hover to 'Sales'");
		adminHomePage.hoverToMenuByName("Sales");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Invoices' page");
		adminInvoicesPage = (AdminInvoicesPO) adminHomePage.clickToPageByName("Invoices");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Click to 'Invoice #' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Invoice #"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Verify 'Invoice #' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isInvoiceNumberSortByAscending("Invoice #"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Click to 'Invoice #' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Invoice #");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Verify 'Invoice #' column sort by descending");
		Assert.assertTrue(adminInvoicesPage.isInvoiceNumberSortByDescending("Invoice #"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Click to 'Order #' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Order #"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Verify 'Order #' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isOrderNumberSortByAscending("Order #"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Click to 'Order #' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Order #"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Verify 'Order #' column sort by descending");
		Assert.assertTrue(adminInvoicesPage.isOrderNumberSortByDescending("Order #"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Click to 'Bill to Name' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Bill to Name"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Verify 'Bill to Name' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isBillToNameSortByAscending("Bill to Name"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Click to 'Bill to Name' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Bill to Name"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Verify 'Bill to Name' column sort by descending");
		Assert.assertTrue(adminInvoicesPage.isBillToNameSortSortByDescending("Bill to Name"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Click to 'Amount' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Amount"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Verify 'Amount' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isAmountSortByAscending("Amount"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_18: Click to 'Amount' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Amount"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_19: Verify 'Amount' column sort by descending");
		Assert.assertTrue(adminInvoicesPage.isAmountSortByDescending("Amount"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_20: Click to 'Invoice Date' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Invoice Date"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_21: Verify 'Invoice Date' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isInvoiceDateSortByAscending("Invoice Date"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_22: Click to 'Invoice Date' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Invoice Date"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_23: Verify 'Invoice Date' sort by descending");
		Assert.assertTrue(adminInvoicesPage.isInvoiceDateSortByDescending("Invoice Date"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_24: Click to 'Order Date' for sort ascending");
		adminInvoicesPage.clickToSortOptionByColumnName("Order Date"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_25: Verify 'Order Date' column sort by ascending");
		Assert.assertTrue(adminInvoicesPage.isOrderDateSortByAscending("Order Date"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_26: Click to 'Order Date' for sort descending");
		adminInvoicesPage.clickToSortOptionByColumnName("Order Date"); 
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_27: Verify 'Invoice Date' sort by descending");
		Assert.assertTrue(adminInvoicesPage.isOrderDateSortByDescending("Order Date"));
		
		
	}
	
	@Test
	public void TC_05_Pagination_Function(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify pagination function");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open BackEnd site");
		adminHomePage = userHomePage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Hover to 'Sales'");
		adminHomePage.hoverToMenuByName("Sales");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Orders'");
		adminOrdersPage = (AdminOrdersPO) adminHomePage.clickToPageByName("Orders");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Select '20' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("20");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Verify table displayed matching '20'");
		Assert.assertEquals(adminOrdersPage.numberItemDisplayed(), "20");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Select '30' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("30");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Verify table displayed matching '30'");
		Assert.assertEquals(adminOrdersPage.numberItemDisplayed(), "30");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Select '50' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("50");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Verify table displayed matching '50'");
		Assert.assertEquals(adminOrdersPage.numberItemDisplayed(), "50");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Select '100' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("100");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Verify table displayed matching '100'");
		Assert.assertEquals(adminOrdersPage.numberItemDisplayed(), "100");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Select '200' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("200");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Verify table displayed matching '200'");
		Assert.assertEquals(adminOrdersPage.numberItemDisplayed(), "200");
		
	}
	
	@Test
	public void TC_06_Search_Function(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify search function");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open BackEnd site");
		adminHomePage = userHomePage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Hover to 'Customers'");
		adminHomePage.hoverToMenuByName("Customers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Manage Customers'");
		adminManageCustomersPage = (AdminManageCustomersPO) adminHomePage.clickToPageByName("Manage Customers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Enter to 'From' textbox with value is '" + LiveGuruData.AdminData.ID + "'");
		adminManageCustomersPage.enterToTextboxByTextboxName("entity_id[from]", LiveGuruData.AdminData.ID);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Enter to 'Name' textbox with value is '" + LiveGuruData.AdminData.NAME + "'");
		adminManageCustomersPage.enterToTextboxByTextboxName("name", LiveGuruData.AdminData.NAME);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Enter to 'Email' textbox with value is '" + LiveGuruData.AdminData.EMAIL + "'");
		adminManageCustomersPage.enterToTextboxByTextboxName("email", LiveGuruData.AdminData.EMAIL);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Enter to 'Telephone' textbox with value is '" + LiveGuruData.AdminData.PHONE + "'");
		adminManageCustomersPage.enterToTextboxByTextboxName("Telephone", LiveGuruData.AdminData.PHONE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Select '" + LiveGuruData.AdminData.COUNTRY + "' in 'Country' dropdown");
		adminManageCustomersPage.selectItemInDropdownByID("country_id", LiveGuruData.AdminData.COUNTRY);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Enter to 'State/Province' textbox with value is '" + LiveGuruData.AdminData.STATE + "'");
		adminManageCustomersPage.enterToTextboxByTextboxName("billing_region", LiveGuruData.AdminData.STATE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Click to 'Manage Customers'");
		adminManageCustomersPage.clickToSearchButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_11: Verify '" + LiveGuruData.AdminData.ID + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.ID));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Verify '" + LiveGuruData.AdminData.NAME + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.NAME));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Verify '" + LiveGuruData.AdminData.EMAIL + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.EMAIL));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Verify '" + LiveGuruData.AdminData.PHONE + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.PHONE));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Verify '" + LiveGuruData.AdminData.ZIP + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.ZIP));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Verify '" + LiveGuruData.AdminData.COUNTRY + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.COUNTRY));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Verify '" + LiveGuruData.AdminData.STATE + "' is displayed");
		Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(LiveGuruData.AdminData.STATE));
		
	}
	
	@Test
	public void TC_07_Select_Checkbox_Function(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify checkbox function");
		ExtentTestManager.getTest().log(Status.INFO, "Step_01: Open BackEnd site");
		adminHomePage = adminManageCustomersPage.openBackEndSite(BEurl);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_02: Hover to 'Sales'");
		adminHomePage.hoverToMenuByName("Sales");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Orders'");
		adminOrdersPage = (AdminOrdersPO) adminHomePage.clickToPageByName("Orders");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Select '30' in 'Views' dropdown");
		adminOrdersPage.selectNumberItemDisplay("30");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_05: Click to 'Select Visible' link");
		adminOrdersPage.clickToLinkByText("Select Visible");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_06: Verify '30 items selected' message is displayed");
		Assert.assertTrue(adminOrdersPage.isNumberItemSelectedDisplayed("30"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_07: Verify 30 checkbox is selected");
		Assert.assertEquals(adminOrdersPage.numberCheckboxIsSelected(), "30");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_08: Click to 'Unselect Visible' link");
		adminOrdersPage.clickToLinkByText("Unselect Visible");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_09: Verify '0 items selected' message is displayed");
		Assert.assertTrue(adminOrdersPage.isNumberItemSelectedDisplayed("0"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_10: Verify 0 checkbox is selected");
		Assert.assertEquals(adminOrdersPage.numberCheckboxIsSelected(), "0");
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}
	