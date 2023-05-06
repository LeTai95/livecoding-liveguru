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
import userPageObject.UserAdvancedSearchPO;
import userPageObject.UserCheckOutPO;
import userPageObject.UserCompareProductsPO;
import userPageObject.UserHomePO;
import userPageObject.UserMyDashboardPO;
import userPageObject.UserMyWishlistPO;
import userPageObject.UserRegisterPO;
import userPageObject.UserShoppingCartPO;

@Listeners(commons.MethodListener.class)
public class LiveGuru_User_Role extends BaseTest {
	WebDriver driver;
	public static Set<Cookie> loggedCookies;
	private String email;
	UserHomePO userHomePage;
	UserRegisterPO userRegisterPage;
	UserMyDashboardPO userMyDashboardPage;
	UserShoppingCartPO userShoppingCartPage;
	UserCompareProductsPO userCompareProductsPage;
	UserMyWishlistPO userMyWishlistPage;
	UserCheckOutPO userCheckOutPage;
	UserAdvancedSearchPO userAdvancedSearchPage;

	@Parameters({ "browser", "enviroment" })
	@BeforeClass
	public void beforeClass(String browserName, String enviromentName) {
		driver = getBrowserDriver(browserName, enviromentName);
		userHomePage = PageGeneraterManager.getUserHomePage(driver);
		loggedCookies = userHomePage.getAllCookie();
		email = LiveGuruData.UserData.EMAIL + getRandomNumberByDateTime() + "@gmail.com";
	}

	@Test
	public void TC_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system");
		ExtentTestManager.getTest().log(Status.INFO, "Register_01: Click to 'My Account' link");
		userHomePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register_02: Click to 'Create an Account' button");
		userRegisterPage = userHomePage.clickToCreatAnAccountButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_03: Enter to 'First Name' textbox with value is'" + LiveGuruData.UserData.FIRST_NAME + "'");
		userRegisterPage.enterToTextboxByTextboxID("firstname", LiveGuruData.UserData.FIRST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_04: Enter to 'Last Name' textboxis'" + LiveGuruData.UserData.LAST_NAME + "'");
		userRegisterPage.enterToTextboxByTextboxID("lastname", LiveGuruData.UserData.LAST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_05: Enter to 'Email Address' textbox with value is'" + email + "'");
		userRegisterPage.enterToTextboxByTextboxID("email_address", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_06: Enter to 'Password' textbox with value is'" + LiveGuruData.UserData.PASSWORD + "'");
		userRegisterPage.enterToTextboxByTextboxID("password", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Register_07: Enter to 'Confirm Password' textbox with value is'"
				+ LiveGuruData.UserData.PASSWORD + "'");
		userRegisterPage.enterToTextboxByTextboxID("confirmation", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Register_08: Click to 'Register' button");
		userMyDashboardPage = userRegisterPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Register_09: Verify register success message displayed is equals 'Thank you for registering with Main Website Store.'");
		Assert.assertEquals(userMyDashboardPage.isSuccessMessageDisplayed(),
				"Thank you for registering with Main Website Store.");

	}

	@Test
	public void TC_02_My_Account(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify user information is correct");
		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01: Click to 'Account Information' link");
		userMyDashboardPage.clickToAccountInformationLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"My_Account_02: Verify 'First Name' is equals'" + LiveGuruData.UserData.FIRST_NAME + "'");
		Assert.assertEquals(userMyDashboardPage.textboxValueByTextboxID("firstname"), LiveGuruData.UserData.FIRST_NAME);

		ExtentTestManager.getTest().log(Status.INFO,
				"My_Account_03: Verify 'Last Name' is equals'" + LiveGuruData.UserData.LAST_NAME + "'");
		Assert.assertEquals(userMyDashboardPage.textboxValueByTextboxID("lastname"), LiveGuruData.UserData.LAST_NAME);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03: Verify 'First Name' is equals'" + email + "'");
		Assert.assertEquals(userMyDashboardPage.textboxValueByTextboxID("email"), email);
	}

	@Test
	public void TC_03_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system");
		ExtentTestManager.getTest().log(Status.INFO, "Login_01: Set cookie and reload page");
		userMyDashboardPage.setCookie(LiveGuru_User_Role.loggedCookies);
		userMyDashboardPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login_02: Click to 'My Account' link");
		userHomePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Login_03: Enter to 'Email Address' textbox with value is '" + email + "'");
		userHomePage.enterToTextboxByTextboxID("email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Login_04: Enter to 'Password' textbox with value is '" + LiveGuruData.UserData.PASSWORD + "'");
		userHomePage.enterToTextboxByTextboxID("pass", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Login_05: Click to 'Login' button");
		userMyDashboardPage = userHomePage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_06: Verify dashboard header text displayed");
		Assert.assertEquals(userMyDashboardPage.isDashboardHeaderTextDisplayed(),
				"Hello, " + LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME + "!");
	}

	@Test
	public void TC_04_Verify_Cost_In_Mobile_Menu_And_Cost_In_Product_Detail(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify price");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		userMyDashboardPage.setCookie(LiveGuru_User_Role.loggedCookies);
		userMyDashboardPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Get 'Sony Xperia' cost");
		String priceInMobileMenu = userHomePage.getProductPriceByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click on 'Sony Xperia' detail");
		userHomePage.clickOnProductDetailByProducrName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_05: Verify cost in list of all mobiles is equals cost in detail page");
		Assert.assertEquals(priceInMobileMenu, userHomePage.getDetailProductPrice());
	}

	@Test
	public void TC_05_Verify_Discount_Coupon_Works_Correctly(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify discount coupon works correctly");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		userHomePage.setCookie(LiveGuru_User_Role.loggedCookies);
		userHomePage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to cart' in 'Sony Xperia' info");
		userShoppingCartPage = userHomePage.clickToAddToCartByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_04: Verify product added to cart success message is displayed");
		Assert.assertTrue(userShoppingCartPage.isProductAddedToCartSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_05: Enter to 'Discount Codes' textbox with value 'GURU50'");
		userShoppingCartPage.enterToDiscountCodesTextbox("GURU50");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Apply' button");
		userShoppingCartPage.clickToApplyButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_07: Verify coupon code was applied success message is displayed");
		Assert.assertTrue(userShoppingCartPage.isCouponCodeAppliedSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Verify 'Discount' value equals '-$5.00'");
		Assert.assertEquals(userShoppingCartPage.getDiscountPriceValueByColumnNumberAndRowName("2", "Discount"), "-$5.00");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Grand Total' value equals '$100.00'");
		Assert.assertEquals(userShoppingCartPage.getGrandTotalPrice(), "$100.00");
	}

	@Test
	public void TC_06_Verify_Can_Not_Add_More_Than_500_Items_Of_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify can't add more than 500 products");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		userShoppingCartPage.setCookie(LiveGuru_User_Role.loggedCookies);
		userShoppingCartPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to cart' in 'Sony Xperia' info");
		userShoppingCartPage = userHomePage.clickToAddToCartByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Enter to 'QTY' textbox with value is '501'");
		userShoppingCartPage.enterToQTYTextbox("501");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click to 'Update' button");
		userShoppingCartPage.clickToUpdateButton();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Verify error message is displayed");
		Assert.assertTrue(userShoppingCartPage.isErrorMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Verify error item message is displayed");
		Assert.assertTrue(userShoppingCartPage.isErrorItemMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Delete' button");
		userShoppingCartPage.clickToDeleteButton();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify empty shopping cart message is displayed");
		Assert.assertTrue(userShoppingCartPage.isEmptyShoppingCartMessageDisplayed());
	}

	@Test
	public void TC_07_Verify_Compare_Two_Products(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify compare two products");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		userShoppingCartPage.setCookie(LiveGuru_User_Role.loggedCookies);
		userShoppingCartPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		userHomePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to compare' in 'Sony Xperia' info");
		userHomePage.clickToAddToCompareLinkByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_04: Verify 'Sony Xperia' added to compare list success message is displayed");
		Assert.assertTrue(userHomePage.isProductAddToCompareSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click 'Add to compare' in 'Sony Xperia' info");
		userHomePage.clickToAddToCompareLinkByProductName("IPhone");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_06: Verify 'IPhone' added to compare list success message is displayed");
		Assert.assertTrue(userHomePage.isProductAddToCompareSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Click to 'Mobile' menu link");
		userHomePage.clickToCompareButton();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Switch to 'Compare Products' windows");
		userCompareProductsPage = userHomePage
				.switchToCompareProductWindowsByTitle("Products Comparison List - Magento Commerce");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Compare Products' title is displayed");
		Assert.assertTrue(userCompareProductsPage.isCompareProductsTitleDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Verify 'Sony Xperia' is displayed");
		Assert.assertTrue(userCompareProductsPage.isProductNameDisplayed("Sony Xperia"));

		ExtentTestManager.getTest().log(Status.INFO,"Verify_11: Verify 'Description' in 'Sony Xperia' details is equals 'this is Sony Xperia'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "Description"),"this is Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify 'Short Description' in 'Sony Xperia' details is equals 'Sony XperiaE'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "Short Description"),"Sony XperiaE");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'SKU' in 'Sony Xperia' details is equals 'MOB001'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "SKU"),"MOB001");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Verify 'Sony Xperia' price is equals '$100.00'");
		Assert.assertTrue(userCompareProductsPage.productPriceByProductName("Sony Xperia", "$100.00"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Verify 'IPhone' is displayed");
		Assert.assertTrue(userCompareProductsPage.isProductNameDisplayed("IPhone"));

		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Verify 'Description' in 'IPhone' details is equals 'IPhone is the one of the best mobile device in market'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "Description"),"IPhone is the one of the best mobile device in market");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify 'Short Description' in 'IPhone' details is equals 'best mobile device'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "Short Description"),"best mobile device");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'SKU' in 'IPhone' details is equals 'MOB0002'");
		Assert.assertEquals(userCompareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "SKU"),"MOB0002");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Verify 'IPhone' price is equals '$500.00'");
		Assert.assertTrue(userCompareProductsPage.productPriceByProductName("IPhone", "$500.00"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_15: Click To 'Close window' button");
		userHomePage = userCompareProductsPage.clickToCloseWindowButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_16: Switch to Home Page window");
		userHomePage.switchToHomePageWindowsByTitle("Mobile");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_17: Verify 'Compare Products' title is undisplayed");
		Assert.assertTrue(userHomePage.isCompareProductsUndisplayed());

	}
	
	@Test
	public void TC_08_Verify_Sharing_Wishlist_To_People_Using_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify sharing wishlist using email");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'My Account' link");
		userHomePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_02: Enter to 'Email Address' textbox with value is '" + email + "'");
		userHomePage.enterToTextboxByTextboxID("email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_03: Enter to 'Password' textbox with value is '" + LiveGuruData.UserData.PASSWORD + "'");
		userHomePage.enterToTextboxByTextboxID("pass", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click to 'Login' button");
		userMyDashboardPage = userHomePage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click to 'TV' menu link");
		userHomePage.clickToTVMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Add to wishlist' in 'LG LCD' info");
		userMyWishlistPage = userHomePage.clickToAddToWishlisttByProductName("LG LCD");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Verify 'LG LCD' added to wishlist success message is displayed");
		Assert.assertTrue(userMyWishlistPage.isProductAddedToWishlistSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Share Wishlist' button");
		userMyWishlistPage.clickToShareWishlistButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Enter to 'Email Address' textarea with value is 'afc999@gmail.com'");
		userMyWishlistPage.enterToTextareaByID("email_address", "afc999@gmail.com");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Enter to 'Message' textarea with value is 'Hi! Automation tester'");
		userMyWishlistPage.enterToTextareaByID("message", "Hi! Automation tester");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Click to 'Share Wishlist' button");
		userMyWishlistPage.clickToShareWishlistButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify wishlist shared success message is displayed");
		Assert.assertTrue(userMyWishlistPage.isWishlistSharedSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'LG LCD' is displayed");
		Assert.assertTrue(userMyWishlistPage.isProductDisplayedByProductName("LG LCD"));
		
	}
	
	@Test
	public void TC_09_Verify_Add_Review(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify add review");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'TV' menu link");
		userHomePage.clickToTVMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Samsung LCD' details");
		userHomePage.clickOnProductDetailByProducrName("Samsung LCD");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click to 'Add Your Review' link");
		userHomePage.clickToAddYourReviewLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click to 'Quality' radio button");
		userHomePage.checkToQualityRadioButtonByValue("5");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Enter to 'Let us know your thoughts' textarea with value is 'Hi!'");
		userHomePage.enterToTextareaByID("review_field", "Hi!");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Enter to 'Summary of Your Review' textarea with value is 'Automation'");
		userHomePage.enterToTextboxByTextboxID("summary_field", "Automation");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Enter to 'What's your nickname?' textarea with value is 'Tester!'");
		userHomePage.enterToTextboxByTextboxID("nickname_field", "Tester!");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Submit Review' button");
		userHomePage.clickToSubmitReviewButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Your review has been accepted for moderation.' message is displayed");
		Assert.assertTrue(userHomePage.isYourReviewAcceptedMessageDisplayed());
		
	}
	
	@Test
	public void TC_10_Verify_Purchase_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify purchase product");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'Account' menu");
		userHomePage.clickToAccountMenu();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'My Wishlist' in 'Account' menu");
		userHomePage.clickToWishlistInMyAccountMenu();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click to 'Add to Cart' button");
		userShoppingCartPage = userHomePage.clickToAddToCartButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Select '" + LiveGuruData.UserData.STATE + "'in 'State/Province' dropdown");
		userShoppingCartPage.selectItemInDropdownByDropdownID("region_id", LiveGuruData.UserData.STATE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Enter to 'ZIP' textbox with value is'" + LiveGuruData.UserData.ZIP + "'");
		userShoppingCartPage.enterToZipTextbox(LiveGuruData.UserData.ZIP);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Estimate' button");
		userShoppingCartPage.clickToEstimateButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Check to 'Flat rate' radio button");
		userShoppingCartPage.checkToFlatRateRadioButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Update total' button");
		userShoppingCartPage.clickToUpdateTotalButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Subtotal' value is equals '$615.00'");
		Assert.assertEquals(userShoppingCartPage.productPriceByColumnNumberAndRowName("2", "Subtotal"), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Verify 'Shipping & Handling (Flat Rate - Fixed)' value is equals '$0.00'");
		Assert.assertEquals(userShoppingCartPage.productPriceByColumnNumberAndRowName("2", "Shipping & Handling (Flat Rate - Fixed)"), "$0.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Verify 'Grand Total' value is equals '$615.00'");
		Assert.assertEquals(userShoppingCartPage.getGrandTotalPrice(), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Click to 'Proceed to Checkout' button");
		userCheckOutPage = userShoppingCartPage.clickToProceedToCheckoutButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Enter to 'Address' with value is '" + LiveGuruData.UserData.ADDRESS + "'");
		userCheckOutPage.enterToTextboxByTextboxID("billing:street1", LiveGuruData.UserData.ADDRESS);
		
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Enter to 'City' with value is '" + LiveGuruData.UserData.CITY + "'");
		userCheckOutPage.enterToTextboxByTextboxID("billing:city", LiveGuruData.UserData.CITY);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_15: Select '" + LiveGuruData.UserData.STATE + "'in 'State/Province' dropdown");
		userCheckOutPage.selectItemInDropdownByDropdownID("billing:region_id", LiveGuruData.UserData.STATE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_16: Enter to 'Zip' with value is '" + LiveGuruData.UserData.ZIP + "'");
		userCheckOutPage.enterToTextboxByTextboxID("billing:postcode", LiveGuruData.UserData.ZIP);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_17: Enter to 'Telephone' with value is '" + LiveGuruData.UserData.PHONE + "'");
		userCheckOutPage.enterToTextboxByTextboxID("billing:telephone", LiveGuruData.UserData.PHONE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_18: Check to 'Ship to this address' radio checkbox");
		userCheckOutPage.checkToRadioCheckBoxByLabel("Ship to this address");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_19: Click to 'Continue' button");
		userCheckOutPage.clickToContinueButton("billing-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_20: Verify 'Flat Rate' is displayed");
		Assert.assertTrue(userCheckOutPage.isFlatRateDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_21: Click to 'Continue' button");
		userCheckOutPage.clickToContinueButton("shipping-method-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_22: Check to 'Check / Money order' radio checkbox");
		userCheckOutPage.checkToRadioCheckBoxByLabel("Check / Money order ");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_23: Click to 'Continue' button");
		userCheckOutPage.clickToContinueButton("payment-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_24: Verify product name is 'LG LCD'");
		Assert.assertTrue(userCheckOutPage.isProductNameDisplayed("LG LCD"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_25: Verify 'Subtotal' value is '$615.00'");
		Assert.assertEquals(userCheckOutPage.productPriceByColumnNumberAndRowName("2", "Subtotal"), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_26: Verify 'Shipping & Handling (Flat Rate - Fixed)' value is '$0.00'");
		Assert.assertEquals(userCheckOutPage.productPriceByColumnNumberAndRowName("2", "Shipping & Handling (Flat Rate - Fixed)"), "$0.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_27: Verify 'Grand Total' value is '$615.00'");
		Assert.assertEquals(userCheckOutPage.productGrandTotalPrice(), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_28: Verify infomation in 'Billing Address'");
		Assert.assertTrue(userCheckOutPage.checkoutProgressInfoByProductInfo("Billing Address", LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_29: Verify infomation in 'Shipping Address'");
		Assert.assertTrue(userCheckOutPage.checkoutProgressInfoByProductInfo("Shipping Address", LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_30: Verify infomation in 'Shipping Method'");
		Assert.assertTrue(userCheckOutPage.checkoutProgressShippingMethod("Flat Rate - Fixed"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_31: Verify infomation in 'Payment Method'");
		Assert.assertTrue(userCheckOutPage.checkoutProgressPaymentMethod("Check / Money order"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_32: Click to 'Place Order' button");
		userHomePage = userCheckOutPage.clickToPlaceOrderButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_33: Verify order received success message is displayed");
		Assert.assertTrue(userHomePage.isReceivedSuccessMessageDisplayed());
		
	}
	
	@Test
	public void TC_11_Verify_Search_Function(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify search function");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		userHomePage.setCookie(LiveGuru_User_Role.loggedCookies);
		userHomePage.refreshToPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Advanced search' link");
		userAdvancedSearchPage = userHomePage.clickToAdvancedSearch();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Enter '0 - 150' to 'Range' textbox");
		userAdvancedSearchPage.enterToTextboxByID("price", "0");
		userAdvancedSearchPage.enterToTextboxByID("price_to", "150");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click to 'Search' button");
		userAdvancedSearchPage.clickToSearchButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Verify 2 products are displayed");
		Assert.assertEquals(userAdvancedSearchPage.productNumberDisplayed(), "2");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Back to 'Advanced Search' page");
		userAdvancedSearchPage.backToPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Enter '151 - 1000' to 'Range' textbox");
		userAdvancedSearchPage.enterToTextboxByID("price", "151");
		userAdvancedSearchPage.enterToTextboxByID("price_to", "1000");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Search' button");
		userAdvancedSearchPage.clickToSearchButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 3 products are displayed");
		Assert.assertEquals(userAdvancedSearchPage.productNumberDisplayed(), "3");
		
	}
	

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
