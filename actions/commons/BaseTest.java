package commons;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;

	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserList == BrowserList.SAFARI) {
			driver = new SafariDriver();
		}
		  else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("-headless");
			option.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(option);
		} else if (browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("-headless");
			option.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(option);
		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserList == BrowserList.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = WebDriverManager.operadriver().create();
		} else if (browserList == BrowserList.COCCOC) {
			// coccoc version = chrome - 5 đến 6 version
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(option);
		} else if (browserList == BrowserList.BRAVE) {
			// Brave browser version nào dùng chrome driver version đó
			WebDriverManager.chromedriver().driverVersion("108.0.5359.71").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(option);
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String enviromentName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if (browserList == BrowserList.SAFARI) {
			driver = new SafariDriver();
		}else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("-headless");
			option.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(option);
		} else if (browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("-headless");
			option.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(option);
		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserList == BrowserList.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = WebDriverManager.operadriver().create();
		} else if (browserList == BrowserList.COCCOC) {
			// coccoc version = chrome - 5 đến 6 version
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(option);
		} else if (browserList == BrowserList.BRAVE) {
			// Brave browser version nào dùng chrome driver version đó
			WebDriverManager.chromedriver().driverVersion("108.0.5359.71").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(option);
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getEnvironmentUrl(enviromentName));
		return driver;

	}

	public WebDriver getDriverInstance() {

		return this.driver;
	}

	protected String getEnvironmentUrl(String serverName) {
		String envUrl = null;
		EnviromentList enviroment = EnviromentList.valueOf(serverName.toUpperCase());
		
		switch (enviroment) {
		case DEV:
			envUrl = "";
			break;
		case TESTING:
			envUrl = "";
			break;
		case PROD:
			envUrl = "";
			break;
		case RE_PROD:
			envUrl = "";
			break;
		case STAGING:
			envUrl = "";
			break;
		case USER:
			envUrl = "http://live.techpanda.org/";
			break;
			
		case ADMIN:
			envUrl = "http://live.techpanda.org/index.php/backendlogin";
			break;
		default:
			envUrl = null;
			break;
		}
		return envUrl;
	}
	
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public static long getRandomNumberByDateTime() {
		return  Calendar.getInstance().getTimeInMillis() % 100000;
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	// Hàm xóa dữ liệu thư mục allure-json
	public void deleteAllureReport() {
		try {

			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
 
	// Lấy ngày tháng năm hiện tại bằng Joda
//	protected String getCurrentDate() {
//		DateTime nowUTC = new DateTime();
//		int day = nowUTC.getDayOfMonth();
//		if (day < 10) {
//			String dayValue = "0" + day;
//			return dayValue;
//		}
//		return String.valueOf(day);
//	}
//
//	protected String getCurrentMonth() {
//		DateTime now = new DateTime();
//		int month = now.getMonthOfYear();
//		if (month < 10) {
//			String monthValue = "0" + month;
//			return monthValue;
//		}
//		return String.valueOf(month);
//	}
//
//	protected String getCurrentYear() {
//		DateTime now = new DateTime();
//		return now.getYear() + "";
//	}
//
//	protected String getCurrentDay() {
//		return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
//	}
	// Lấy ngày tháng năm hiện tại (dạng 16/2/2023)
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MMMMMMMMMMM dd, yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		return currentDate;
	}

}
