package com.liveguru.user;

import java.lang.reflect.Method;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.livegure.data.LiveGuruData;

import commons.BaseTest;
import commons.PageGeneraterManager;
import reportConfig.ExtentTestManager;
import userPageObject.UserHomePO;
import userPageObject.UserMyDashboardPO;
import userPageObject.UserRegisterPO;
import userPageObject.UserShoppingCartPO;

@Listeners(commons.MethodListener.class)
public class LiveGuru_User_Role extends BaseTest {
	WebDriver driver;
	public static Set<Cookie> loggedCookies;
	private String email;
	UserHomePO homePage;
	UserRegisterPO registerPage;
	UserMyDashboardPO myDashboardPage;
	UserShoppingCartPO shoppingCartPage;

	@Parameters({ "browser", "enviroment" })
	@BeforeClass
	public void beforeClass(String browserName, String enviromentName, Method method) {
		driver = getBrowserDriver(browserName, enviromentName);
		homePage = PageGeneraterManager.getUserHomePage(driver);
		loggedCookies = homePage.getAllCookie();
		email = LiveGuruData.UserData.EMAIL + getRandomNumberByDateTime() + "@gmail.com";
	}

	@Test
	public void TC_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system");
		ExtentTestManager.getTest().log(Status.INFO, "Register_01: Click to 'My Account' link");
		homePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register_02: Click to 'Create an Account' button");
		registerPage = homePage.clickToCreatAnAccountButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_03: Enter to 'First Name' textbox with value is'" + LiveGuruData.UserData.FIRST_NAME + "'");
		registerPage.enterToTextboxByTextboxID("firstname", LiveGuruData.UserData.FIRST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_04: Enter to 'Last Name' textboxis'" + LiveGuruData.UserData.LAST_NAME + "'");
		registerPage.enterToTextboxByTextboxID("lastname", LiveGuruData.UserData.LAST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_05: Enter to 'Email Address' textbox with value is'" + email + "'");
		registerPage.enterToTextboxByTextboxID("email_address", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_06: Enter to 'Password' textbox with value is'" + LiveGuruData.UserData.PASSWORD + "'");
		registerPage.enterToTextboxByTextboxID("password", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Register_07: Enter to 'Confirm Password' textbox with value is'"
				+ LiveGuruData.UserData.PASSWORD + "'");
		registerPage.enterToTextboxByTextboxID("confirmation", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Register_08: Click to 'Register' button");
		myDashboardPage = registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_09: Verify register success message displayed is equals 'Thank you for registering with Main Website Store.'");
		Assert.assertEquals(myDashboardPage.isSuccessMessageDisplayed(),
				"Thank you for registering with Main Website Store.");

	}

	@Test
	public void TC_02_My_Account(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify user information is correct");
		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01: Click to 'Account Information' link");
		myDashboardPage.clickToAccountInformationLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"My_Account_02: Verify 'First Name' is equals'" + LiveGuruData.UserData.FIRST_NAME + "'");
		Assert.assertEquals(myDashboardPage.textboxValueByTextboxID("firstname"), LiveGuruData.UserData.FIRST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"My_Account_03: Verify 'Last Name' is equals'" + LiveGuruData.UserData.LAST_NAME + "'");
		Assert.assertEquals(myDashboardPage.textboxValueByTextboxID("lastname"), LiveGuruData.UserData.LAST_NAME);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03: Verify 'First Name' is equals'" + email + "'");
		Assert.assertEquals(myDashboardPage.textboxValueByTextboxID("email"), email);
	}

	@Test
	public void TC_03_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system");
		ExtentTestManager.getTest().log(Status.INFO, "Login_01: Set cookie and reload page");
		myDashboardPage.setCookie(LiveGuru_User_Role.loggedCookies);
		myDashboardPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login_02: Click to 'My Account' link");
		homePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Login_03: Enter to 'Email Address' textbox with value is '" + email + "'");
		homePage.enterToTextboxByTextboxID("email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Login_04: Enter to 'Password' textbox with value is '" + LiveGuruData.UserData.PASSWORD + "'");
		homePage.enterToTextboxByTextboxID("pass", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Login_05: Click to 'Login' button");
		myDashboardPage = homePage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_06: Verify dashboard header text displayed");
		Assert.assertEquals(myDashboardPage.isDashboardHeaderTextDisplayed(),
				"Hello, " + LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME + "!");
	}

	@Test
	public void TC_04_Verify_Cost_In_Mobile_Menu_And_Cost_In_Product_Detail(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify price");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		myDashboardPage.setCookie(LiveGuru_User_Role.loggedCookies);
		myDashboardPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		homePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Get 'Sony Xperia' cost");
		String priceInMobileMenu = homePage.getProductPriceByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click on 'Sony Xperia' detail");
		homePage.clickOnProductDetailByProducrName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_05: Verify cost in list of all mobiles is equals cost in detail page");
		Assert.assertEquals(priceInMobileMenu, homePage.getDetailProductPrice());
	}
	
	@Test
	public void TC_05_Verify_Discount_Coupon_Works_Correctly(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify discount coupon works correctly");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		homePage.setCookie(LiveGuru_User_Role.loggedCookies);
		homePage.refreshToPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		homePage.clickToMobileMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to cart' in 'Sony Xperia' info");
		shoppingCartPage = homePage.clickToAddToCartByProductName("Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Verify product added to cart success message is displayed");
		Assert.assertTrue(shoppingCartPage.isProductAddedToCartSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Enter to 'Discount Codes' textbox with value 'GURU50'");
		shoppingCartPage.enterToDiscountCodesTextbox("GURU50");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Apply' button");
		shoppingCartPage.clickToApplyButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Verify coupon code was applied success message is displayed");
		Assert.assertTrue(shoppingCartPage.isCouponCodeAppliedSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Verify 'Discount' value equals '-$5.00'");
		Assert.assertEquals(shoppingCartPage.getDiscountPriceValueByColumnNumberAndRowName("2", "Discount"), "-$5.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Grand Total' value equals '$100.00'");
		Assert.assertEquals(shoppingCartPage.getGrandTotalPrice(), "$100.00");
	}
	
	@Test
	public void TC_06_Verify_Can_Not_Add_More_Than_500_Items_Of_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify can't add more than 500 products");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		shoppingCartPage.setCookie(LiveGuru_User_Role.loggedCookies);
		shoppingCartPage.refreshToPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		homePage.clickToMobileMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to cart' in 'Sony Xperia' info");
		shoppingCartPage = homePage.clickToAddToCartByProductName("Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Enter to 'QTY' textbox with value is '501'");
		shoppingCartPage.enterToQTYTextbox("501");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click to 'Update' button");
		shoppingCartPage.clickToUpdateButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Verify error message is displayed");
		Assert.assertTrue(shoppingCartPage.isErrorMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Verify error item message is displayed");
		Assert.assertTrue(shoppingCartPage.isErrorItemMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Delete' button");
		shoppingCartPage.clickToDeleteButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify empty shopping cart message is displayed");
		Assert.assertTrue(shoppingCartPage.isEmptyShoppingCartMessageDisplayed());
		
		
	}
	

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
