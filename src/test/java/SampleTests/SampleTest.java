package SampleTests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class SampleTest {
	
	public static void main(String[] args) throws InterruptedException {
		
	

	DesiredCapabilities cap=new DesiredCapabilities();
	cap.setCapability("platformName", "Android");
	cap.setCapability("appium:platformVersion", "13");
	cap.setCapability("appium:deviceName", "Pixel 2 XL API 33");
	cap.setCapability("appium:app", "/path/to/your/app.apk");
	cap.setCapability("appium:automationName", "UiAutomator2");
	cap.setCapability("appium:ensureWebviewsHavePages", true);
	cap.setCapability("appium:nativeWebScreenshot", true);
	cap.setCapability("appium:newCommandTimeout", 3600);
	cap.setCapability("appium:connectHardwareKeyboard", true);
	
	AndroidDriver driver=null;
	
	try
	{
		driver=new AndroidDriver((new URL("http://127.0.0.1:4723")),cap);
	}
	catch (MalformedURLException e) {
        e.printStackTrace();
      }
	
	WebElement mobile_no=driver.findElement(By.id("com.example.app:id/et_mobile_number"));
	mobile_no.click();
	mobile_no.sendKeys("6789678990");
	Thread.sleep(1000);

	WebElement monthly_sales=driver.findElement(By.id("com.example.app:id/tv_monthly_sales"));

	monthly_sales.click();

	WebElement select_monthly_sales = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"2 - 4 Lakhs\"]"));

	select_monthly_sales.click();

	WebElement gst=driver.findElement(By.id("com.example.app:id/tv_gst_yes"));

	gst.click();

	WebElement submit = driver.findElement(By.id("com.example.app:id/bt_submit"));
	
	submit.click();
	
}
	
}