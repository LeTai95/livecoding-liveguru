package commons;

import java.io.File;

public class GlobalConstants {
		public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
		public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";
		public static final String PROJECT_PATH = System.getProperty("user.dir");
		public static final String JAVA_VERSION = System.getProperty("java.version");
		public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
		public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
		public static final String DOWNLOAD_PATH = "C:\\Users\\leduc\\Downloads";
		public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
		public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "drapDropHTML5";
		public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
		
		public static final long LONG_TIME_OUT = 30;
		public static final long SHORT_TIME_OUT = 5;
		public static final long RETRY_TEST_FAIL = 3;
		
		
		public static final String LIVE_TECHPANDA_USER = "http://live.techpanda.org/index.php/";
		public static final String LIVE_TECHPANDA_ADMIN = "http://live.techpanda.org/index.php/backendlogin/customer/index/key/4f71d3e80afb75c1f8b439af2ec7fb5a/";
		public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
		
		
}
