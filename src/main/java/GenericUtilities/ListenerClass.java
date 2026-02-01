package GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;

/**
 * @author:Aditya K.
 * @description: This method is used to generate extent report with screenshot.
 */

public class ListenerClass implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReports/ExtentReports" + dtf.format(now) + ".html");
        spark.config().setDocumentTitle("Test Automation Results");
        spark.config().setReportName("Flexi MobileApp Positive Flow Test Execution Results");
        spark.config().setTheme(Theme.STANDARD);
        
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Mobile Device", "Samsung Galaxy M21");
        extent.setSystemInfo("Environment", "Staging.apk");
        extent.setSystemInfo("Platform Name", "Android");
        extent.setSystemInfo("Platform Version", "12");
        extent.setSystemInfo("Author", "Aditya K.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        ThreadLocalClass.settestlevel(test); // Set current test in ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	ThreadLocalClass.gettestlevel().log(Status.PASS, MarkupHelper.createLabel("PASSED -"+ result.getName(), ExtentColor.GREEN));
    	extent.flush();
        ThreadLocalClass.settestlevel(null); // Unload current test from ThreadLocal after use
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	ThreadLocalClass.gettestlevel().log(Status.FAIL, MarkupHelper.createLabel("FAILED -"+ result.getName(), ExtentColor.RED));
        AppiumDriver driver = (AppiumDriver) result.getTestContext().getAttribute("driver");
        String screenshotPath = captureScreenshotAndAttach(driver, result.getName());
        ThreadLocalClass.gettestlevel().fail("Screenshot of failed test", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        ThreadLocalClass.gettestlevel().fail(result.getThrowable());
        extent.flush();
        ThreadLocalClass.settestlevel(null); // Unload current test from ThreadLocal after use
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ThreadLocalClass.gettestlevel().log(Status.SKIP, MarkupHelper.createLabel("SKIPPED -"+ result.getName(), ExtentColor.TEAL));
        ThreadLocalClass.gettestlevel().info(result.getThrowable());
        extent.flush();
        ThreadLocalClass.settestlevel(null);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public String captureScreenshotAndAttach(AppiumDriver driver, String screenshotName) {
        String dateName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return destination;
    }
}
