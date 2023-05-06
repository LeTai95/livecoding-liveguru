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
import adminPageObject.AdminOrdersPO;
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
		adminHomePage.hoverToObjectByName("Sales");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_03: Click to 'Orders'");
		adminOrdersPage = adminHomePage.clickToObjectByName("Orders");
		
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
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_04: Get Admin Home Page ID");
		String adminHomePageID = driver.getWindowHandle();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Verify invoice is downloaded");
		Assert.assertTrue(adminOrdersPage.isInvoiceDownloaded("invoice2023-05-06"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Close invoice tab");
		adminOrdersPage.closeInvoiceTab(adminHomePageID);
		
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
		adminHomePage.hoverToObjectByName("Catalog");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_12: Hover to 'Reviews and Ratings' menu");
		adminHomePage.hoverToObjectByName("Reviews and Ratings");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_13: Click to 'Pending Reviews'");
		adminHomePage.clickToObjectByName("Customer Reviews");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_14: Click to 'Pending Reviews'");
		adminHomePage.clickToObjectByName("Pending Reviews");
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_15: Enter to 'Nickname' textbox with value is '" + reviewNickname + "'");
		adminHomePage.enterToTextboxByName("nickname", reviewNickname);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_16: Click to 'Search' button");
		adminHomePage.clickToSearchButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step_17: Click to 'Edit' button");
		adminEditReviewPage = adminHomePage.clickToEditButton();
		
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

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}
