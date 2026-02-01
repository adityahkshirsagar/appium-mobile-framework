package GenericUtilities;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.github.javafaker.Faker;

import PageObject.AppBankStatement;
import PageObject.AppBusinessKYC;
import PageObject.AppDashboard;
import PageObject.AppDashboard_2;
import PageObject.AppForm2;
import PageObject.AppFormStep_1;
import PageObject.AppFormStep_2;
import PageObject.AppFormStep_3;
import PageObject.AppKYC;
import PageObject.AppLandingPage;
import PageObject.AppAmount;
import PageObject.AppApplicationDashScreen;
import PageObject.AppOTP;
import PageObject.AppOfferScreen;
import PageObject.AppRequiredDocument;
import PageObject.AppResidentialAddressProof;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import propertyfileConfig.ObjectReader;
import propertyfileConfig.PropertyFileReader;

/**
 * @author Aditya K.
 */
public class BaseClass {
	
	public Databaseutil databaseutil=new Databaseutil();
	public ReportUtility reportUtil=new ReportUtility();
	public PropertyFileReader propfile=new PropertyFileReader();
	public ExcelUtil excelUtil=new ExcelUtil();
	protected static MongoDBUtil mongoDBUtil;
	protected static AppiumDriver driver;
	public AppiumDriverLocalService service;
	protected Faker faker=new Faker();
	protected static Faker faker1 = new Faker(new Locale("en", "IND"));
	protected static AppLandingPage lp;
	protected static AppOTP otp;
	protected static AppForm2 form2;
	protected static AppAmount appAmount;
	protected static AppDashboard dashboard;
	public static AppFormStep_1 formStep1;
	public static AppFormStep_2 formStep2;
	public static AppFormStep_3 formStep3;
	public static AppOfferScreen offerscreen;
	public static AppApplicationDashScreen appApplicationDashScreen;
	public static AppDashboard_2 appdashboard_2;
	public static AppBankStatement appBankStatement;
	public static AppRequiredDocument appRequiredDocument;
	public static AppBusinessKYC appBusinessKYC;
	public static AppResidentialAddressProof appResidentialAddressProof;
	public static AppKYC appkyc;
	protected String TCID;
	protected WebDriverWait wait;
	public String application_code;
	protected static AppiumServerManager serverManager;

	private static final String PLATFORM_PROP = "platform";
	private static final String PLATFORM_ENV = "PLATFORM";

	@BeforeSuite(alwaysRun = true)
	public void setup() throws Throwable {
		serverManager = new AppiumServerManager();
		if (!serverManager.isServerRunning()) {
			serverManager.startServer();
		} else {
			System.out.println("⚠️ Appium server already running.");
		}

		String platform = System.getProperty(PLATFORM_PROP, System.getenv().getOrDefault(PLATFORM_ENV, "android")).trim().toLowerCase();
		URL serverUrl = new URL(serverManager.getServerUrl());

		try {
			if ("ios".equals(platform)) {
				driver = createIOSDriver(serverUrl);
			} else {
				driver = createAndroidDriver(serverUrl);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		ThreadLocalClass.setexcelUtil(excelUtil);
		excelUtil.openExcelFile(FilePaths.APPIUM_DDT_EXCEL);
		databaseutil.connectdatabase();
	}

	private AppiumDriver createAndroidDriver(URL serverUrl) throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setPlatformVersion(System.getProperty("platformVersion", System.getenv().getOrDefault("PLATFORM_VERSION", "12")));
		options.setDeviceName(System.getProperty("deviceName", System.getenv().getOrDefault("DEVICE_NAME", "android_device")));
		options.setUdid(System.getProperty("udid", System.getenv().getOrDefault("DEVICE_UDID", "")));
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.example.app");
		options.setAppActivity("com.example.app.Activity.Registration.SplashActivity");
		options.setEnsureWebviewsHavePages(true);
		options.setNativeWebScreenshot(true);
		options.setCapability("appWaitActivity", "*");
		options.setCapability("autoGrantPermissions", true);
		return new AndroidDriver(serverUrl, options);
	}

	private AppiumDriver createIOSDriver(URL serverUrl) throws MalformedURLException {
		XCUITestOptions options = new XCUITestOptions();
		options.setPlatformName("iOS");
		options.setPlatformVersion(System.getProperty("platformVersion", System.getenv().getOrDefault("PLATFORM_VERSION", "16")));
		options.setDeviceName(System.getProperty("deviceName", System.getenv().getOrDefault("DEVICE_NAME", "iPhone")));
		options.setUdid(System.getProperty("udid", System.getenv().getOrDefault("DEVICE_UDID", "")));
		options.setAutomationName("XCUITest");
		options.setBundleId(System.getProperty("bundleId", System.getenv().getOrDefault("BUNDLE_ID", "com.example.app")));
		options.setCapability("autoAcceptAlerts", true);
		options.setCapability("autoDismissAlerts", true);
		return new IOSDriver(serverUrl, options);
	}
	
		@BeforeMethod(alwaysRun = true)
		public void ConnectToDatabase() throws Exception {
			ObjectReader.reader.OpenPropertyfile("qa");
			excelUtil.openExcelFile(FilePaths.APPIUM_DDT_EXCEL);
			
			ITestContext context=org.testng.Reporter.getCurrentTestResult().getTestContext();
			context.setAttribute("driver", driver);
			
			lp = new AppLandingPage(driver);
			otp = new AppOTP(driver);
			form2= new AppForm2(driver);
			appAmount=new AppAmount(driver);
			dashboard=new AppDashboard(driver);
			formStep1=new AppFormStep_1(driver);
			formStep2=new AppFormStep_2(driver);
			formStep3=new AppFormStep_3(driver);
			appApplicationDashScreen=new AppApplicationDashScreen(driver);
			appBankStatement = new AppBankStatement(driver);
			appRequiredDocument = new AppRequiredDocument(driver);
			appBusinessKYC = new AppBusinessKYC(driver);
			appResidentialAddressProof = new AppResidentialAddressProof(driver);
			appkyc = new AppKYC(driver);
			offerscreen = new AppOfferScreen(driver);
			appdashboard_2 = new AppDashboard_2(driver);		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void checkingToken(Method mtd) throws Exception {
		 TCID=mtd.getDeclaringClass().getSimpleName()+"_"+mtd.getName();
		 }
	
	public String randome10string() {
		String generatedstring = RandomStringUtils.randomAlphabetic(10);
		return (generatedstring);
	}
	
	public String randome4string() {
		String generatedstring1 = RandomStringUtils.randomAlphabetic(4);
		return (generatedstring1);
	}

	public String randome5string() {
		String generatedstring2 = RandomStringUtils.randomAlphabetic(5);
		return (generatedstring2);
	}
	
	public String randome7string() {
		String generatedstring3 = RandomStringUtils.randomAlphabetic(7);
		return (generatedstring3);
	}
	
	public String randome6string() {
		String generatedstring4 = RandomStringUtils.randomAlphabetic(6);
		return (generatedstring4);
	}
	
	public String randome2string() {
		String generatedstring5 = RandomStringUtils.randomAlphabetic(2);
		return (generatedstring5);
	}
	
	public String randome3string() {
		String generatedstring6 = RandomStringUtils.randomAlphabetic(3);
		return (generatedstring6);
	}

	public static String randome4Num() {
		String generatedString5 = RandomStringUtils.randomNumeric(4);
		return (generatedString5);
	}

	public static String randome9Num() {
		String generatedString6 = RandomStringUtils.randomNumeric(9);
		return (generatedString6);
	}

	public static String randome6Num() {
		String generatedString7 = RandomStringUtils.randomNumeric(6);
		return (generatedString7);
	}
	
	public static String randome5Num() {
		String generatedString8 = RandomStringUtils.randomNumeric(5);
		return (generatedString8);
	}
	
	public static String randome1Num() {
		String generatedString9 = RandomStringUtils.randomNumeric(1);
		return (generatedString9);
	}
	
	public static String randome2Num() {
		String generatedString10 = RandomStringUtils.randomNumeric(2);
		return (generatedString10);
	}
	
	public static String randome3Num() {
		String generatedString11 = RandomStringUtils.randomNumeric(3);
		return (generatedString11);
	}
	
	public static String randome7Num() {
		String generatedString12 = RandomStringUtils.randomNumeric(7);
		return (generatedString12);
	}
	
	public static String randome8Num() {
		String generatedString13 = RandomStringUtils.randomNumeric(8);
		return (generatedString13);
	}
	
	// Generate a random 6-digit pincode within the range of Indian pin codes
	protected static String generateRandomPincode(Faker faker) {
        int minPincode = 110001;
        int maxPincode = 999999;
        return String.format("%06d", faker1.number().numberBetween(minPincode, maxPincode + 1));
    }
	
	@AfterSuite
    public void tearDownSuite() {
        if (serverManager != null && serverManager.isServerRunning()) {
            serverManager.stopServer();
        }
    }
	
	
}