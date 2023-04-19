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

import commons.BaseTest;
import commons.PageGeneraterManager;
import reportConfig.ExtentTestManager;
import userPageObject.UserHomePO;
import userPageObject.UserMyDashboardPO;
import userPageObject.UserRegisterPO;

@Listeners(commons.MethodListener.class)
public class LiveGuru_User_Role extends BaseTest {
	WebDriver driver;
	private String email;
	UserHomePO homePage;
	UserRegisterPO registerPage;
	UserMyDashboardPO myDashboardPage;

	@Parameters({ "browser", "enviroment" })
	@BeforeClass
	public void beforeClass(String browserName, String enviromentName, Method method) {
		driver = getBrowserDriver(browserName, enviromentName);
		homePage = PageGeneraterManager.getUserHomePage(driver);
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
		Assert.assertEquals(myDashboardPage.isSuccessMessageDisplayed(), "Thank you for registering with Main Website Store");

	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
