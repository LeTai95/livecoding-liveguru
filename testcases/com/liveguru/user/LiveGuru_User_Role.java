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
	UserHomePO homePage;
	UserRegisterPO registerPage;
	UserMyDashboardPO myDashboardPage;
	UserShoppingCartPO shoppingCartPage;
	UserCompareProductsPO compareProductsPage;
	UserMyWishlistPO myWishlistPage;
	UserCheckOutPO checkOutPage;

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

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_04: Verify product added to cart success message is displayed");
		Assert.assertTrue(shoppingCartPage.isProductAddedToCartSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_05: Enter to 'Discount Codes' textbox with value 'GURU50'");
		shoppingCartPage.enterToDiscountCodesTextbox("GURU50");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Apply' button");
		shoppingCartPage.clickToApplyButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_07: Verify coupon code was applied success message is displayed");
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

	@Test
	public void TC_07_Verify_Compare_Two_Products(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify compare two products");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Set cookie and reload page");
		shoppingCartPage.setCookie(LiveGuru_User_Role.loggedCookies);
		shoppingCartPage.refreshToPage();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Mobile' menu link");
		homePage.clickToMobileMenuLink();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click 'Add to compare' in 'Sony Xperia' info");
		homePage.clickToAddToCompareLinkByProductName("Sony Xperia");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_04: Verify 'Sony Xperia' added to compare list success message is displayed");
		Assert.assertTrue(homePage.isProductAddToCompareSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click 'Add to compare' in 'Sony Xperia' info");
		homePage.clickToAddToCompareLinkByProductName("IPhone");

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_06: Verify 'IPhone' added to compare list success message is displayed");
		Assert.assertTrue(homePage.isProductAddToCompareSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Click to 'Mobile' menu link");
		homePage.clickToCompareButton();

		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Switch to 'Compare Products' windows");
		compareProductsPage = homePage
				.switchToCompareProductWindowsByTitle("Products Comparison List - Magento Commerce");

		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Compare Products' title is displayed");
		Assert.assertTrue(compareProductsPage.isCompareProductsTitleDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Verify 'Sony Xperia' is displayed");
		Assert.assertTrue(compareProductsPage.isProductNameDisplayed("Sony Xperia"));

		ExtentTestManager.getTest().log(Status.INFO,"Verify_11: Verify 'Description' in 'Sony Xperia' details is equals 'this is Sony Xperia'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "Description"),"this is Sony Xperia");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify 'Short Description' in 'Sony Xperia' details is equals 'Sony XperiaE'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "Short Description"),"Sony XperiaE");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'SKU' in 'Sony Xperia' details is equals 'MOB001'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("Sony Xperia", "1", "SKU"),"MOB001");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Verify 'Sony Xperia' price is equals '$100.00'");
		Assert.assertTrue(compareProductsPage.productPriceByProductName("Sony Xperia", "$100.00"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Verify 'IPhone' is displayed");
		Assert.assertTrue(compareProductsPage.isProductNameDisplayed("IPhone"));

		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Verify 'Description' in 'IPhone' details is equals 'IPhone is the one of the best mobile device in market'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "Description"),"IPhone is the one of the best mobile device in market");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify 'Short Description' in 'IPhone' details is equals 'best mobile device'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "Short Description"),"best mobile device");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'SKU' in 'IPhone' details is equals 'MOB0002'");
		Assert.assertEquals(compareProductsPage.productInfoByColumnNumberAndRowNumber("IPhone", "2", "SKU"),"MOB0002");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Verify 'IPhone' price is equals '$500.00'");
		Assert.assertTrue(compareProductsPage.productPriceByProductName("IPhone", "$500.00"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_15: Click To 'Close window' button");
		homePage = compareProductsPage.clickToCloseWindowButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_16: Switch to Home Page window");
		homePage.switchToHomePageWindowsByTitle("Mobile");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_17: Verify 'Compare Products' title is undisplayed");
		Assert.assertTrue(homePage.isCompareProductsUndisplayed());

	}
	
	@Test
	public void TC_08_Verify_Sharing_Wishlist_To_People_Using_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify sharing wishlist using email");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'My Account' link");
		homePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_02: Enter to 'Email Address' textbox with value is '" + email + "'");
		homePage.enterToTextboxByTextboxID("email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Verify_03: Enter to 'Password' textbox with value is '" + LiveGuruData.UserData.PASSWORD + "'");
		homePage.enterToTextboxByTextboxID("pass", LiveGuruData.UserData.PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click to 'Login' button");
		myDashboardPage = homePage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click to 'TV' menu link");
		homePage.clickToTVMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Click to 'Add to wishlist' in 'LG LCD' info");
		myWishlistPage = homePage.clickToAddToWishlisttByProductName("LG LCD");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Verify 'LG LCD' added to wishlist success message is displayed");
		Assert.assertTrue(myWishlistPage.isProductAddedToWishlistSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Share Wishlist' button");
		myWishlistPage.clickToShareWishlistButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Enter to 'Email Address' textarea with value is 'afc999@gmail.com'");
		myWishlistPage.enterToTextareaByID("email_address", "afc999@gmail.com");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Enter to 'Message' textarea with value is 'Hi! Automation tester'");
		myWishlistPage.enterToTextareaByID("message", "Hi! Automation tester");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Click to 'Share Wishlist' button");
		myWishlistPage.clickToShareWishlistButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Verify wishlist shared success message is displayed");
		Assert.assertTrue(myWishlistPage.isWishlistSharedSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Verify 'LG LCD' is displayed");
		Assert.assertTrue(myWishlistPage.isProductDisplayedByProductName("LG LCD"));
		
	}
	
	@Test
	public void TC_09_Verify_Add_Review(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify add review");
		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'TV' menu link");
		homePage.clickToTVMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Samsung LCD' details");
		homePage.clickOnProductDetailByProducrName("Samsung LCD");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click to 'Add Your Review' link");
		homePage.clickToAddYourReviewLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Click to 'Quality' radio button");
		homePage.clickToQualityRadioButtonByValue("5");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Enter to 'Let us know your thoughts' textarea with value is 'Hi!'");
		homePage.enterToTextareaByID("review_field", "Hi!");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Enter to 'Summary of Your Review' textarea with value is 'Automation'");
		homePage.enterToTextboxByTextboxID("summary_field", "Automation");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Enter to 'What's your nickname?' textarea with value is 'Tester!'");
		homePage.enterToTextboxByTextboxID("nickname_field", "Tester!");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Click to 'Submit Review' button");
		homePage.clickToSubmitReviewButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Verify 'Your review has been accepted for moderation.' message is displayed");
		Assert.assertTrue(homePage.isYourReviewAcceptedMessageDisplayed());
		
	}
	
	@Test
	public void TC_10_Verify_Purchase_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify purchase product");
//		ExtentTestManager.getTest().log(Status.INFO, "Verify_01: Click to 'Go to Wishlist' link");
//		homePage.clickToGoToWishlist();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_02: Click to 'Account' menu");
		homePage.clickToAccountMenu();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click to 'My Wishlist' in 'Account' menu");
		homePage.clickToWishlistInMyAccountMenu();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_03: Click to 'Add to Cart' button");
		shoppingCartPage = homePage.clickToAddToCartButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_04: Select '" + LiveGuruData.UserData.STATE + "'in 'State/Province' dropdown");
		shoppingCartPage.selectItemInDropdownByDropdownID("region_id", LiveGuruData.UserData.STATE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Enter to 'ZIP' textbox with value is'" + LiveGuruData.UserData.ZIP + "'");
		shoppingCartPage.enterToZipTextbox(LiveGuruData.UserData.ZIP);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_05: Click to 'Estimate' button");
		shoppingCartPage.clickToEstimateButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_06: Check to 'Flat rate' radio button");
		shoppingCartPage.checkToFlatRateRadioButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_07: Click to 'Update total' button");
		shoppingCartPage.clickToUpdateTotalButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Verify 'Subtotal' value is equals '$615.00'");
		Assert.assertEquals(shoppingCartPage.productPriceByColumnNumberAndRowName("2", "Subtotal"), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_08: Verify 'Shipping & Handling (Flat Rate - Fixed)' value is equals '$0.00'");
		Assert.assertEquals(shoppingCartPage.productPriceByColumnNumberAndRowName("2", "Shipping & Handling (Flat Rate - Fixed)"), "$0.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_09: Verify 'Grand Total' value is equals '$615.00'");
		Assert.assertEquals(shoppingCartPage.getGrandTotalPrice(), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_10: Click to 'Proceed to Checkout' button");
		checkOutPage = shoppingCartPage.clickToProceedToCheckoutButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_11: Enter to 'Address' with value is '" + LiveGuruData.UserData.ADDRESS + "'");
		checkOutPage.enterToTextboxByTextboxID("billing:street1", LiveGuruData.UserData.ADDRESS);
		
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_12: Enter to 'City' with value is '" + LiveGuruData.UserData.CITY + "'");
		checkOutPage.enterToTextboxByTextboxID("billing:city", LiveGuruData.UserData.CITY);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_13: Select '" + LiveGuruData.UserData.STATE + "'in 'State/Province' dropdown");
		checkOutPage.selectItemInDropdownByDropdownID("billing:region_id", LiveGuruData.UserData.STATE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_14: Enter to 'Zip' with value is '" + LiveGuruData.UserData.ZIP + "'");
		checkOutPage.enterToTextboxByTextboxID("billing:postcode", LiveGuruData.UserData.ZIP);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_16: Enter to 'Telephone' with value is '" + LiveGuruData.UserData.PHONE + "'");
		checkOutPage.enterToTextboxByTextboxID("billing:telephone", LiveGuruData.UserData.PHONE);
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_17: Check to 'Ship to this address' radio checkbox");
		checkOutPage.checkToRadioCheckBoxByLabel("Ship to this address");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_18: Click to 'Continue' button");
		checkOutPage.clickToContinueButton("billing-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_19: Verify 'Flat Rate' is displayed");
		Assert.assertTrue(checkOutPage.isFlatRateDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_20: Click to 'Continue' button");
		checkOutPage.clickToContinueButton("shipping-method-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_21: Check to 'Check / Money order' radio checkbox");
		checkOutPage.checkToRadioCheckBoxByLabel("Check / Money order ");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_22: Click to 'Continue' button");
		checkOutPage.clickToContinueButton("payment-buttons-container");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_23: Verify product name is 'LG LCD'");
		Assert.assertTrue(checkOutPage.isProductNameDisplayed("LG LCD"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_24: Verify 'Subtotal' value is '$615.00'");
		Assert.assertEquals(checkOutPage.productPriceByColumnNumberAndRowName("2", "Subtotal"), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_25: Verify 'Shipping & Handling (Flat Rate - Fixed)' value is '$0.00'");
		Assert.assertEquals(checkOutPage.productPriceByColumnNumberAndRowName("2", "Shipping & Handling (Flat Rate - Fixed)"), "$0.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_26: Verify 'Grand Total' value is '$615.00'");
		Assert.assertEquals(checkOutPage.productGrandTotalPrice(), "$615.00");
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_27: Verify infomation in 'Billing Address'");
		Assert.assertTrue(checkOutPage.checkoutProgressInfoByProductInfo("Billing Address", LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_28: Verify infomation in 'Shipping Address'");
		Assert.assertTrue(checkOutPage.checkoutProgressInfoByProductInfo("Shipping Address", LiveGuruData.UserData.FIRST_NAME + " " + LiveGuruData.UserData.LAST_NAME));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_29: Verify infomation in 'Shipping Method'");
		Assert.assertTrue(checkOutPage.checkoutProgressShippingMethod("Flat Rate - Fixed"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_30: Verify infomation in 'Payment Method'");
		Assert.assertTrue(checkOutPage.checkoutProgressPaymentMethod("Check / Money order"));
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_31: Click to 'Place Order' button");
		homePage = checkOutPage.clickToPlaceOrderButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Verify_32: Verify order received success message is displayed");
		Assert.assertTrue(homePage.isReceivedSuccessMessageDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
