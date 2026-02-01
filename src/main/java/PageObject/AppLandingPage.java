package PageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppLandingPage {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppLandingPage(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(id="com.example.app:id/et_mobile_number") 
	@CacheLookup
	WebElement mobileNumberField;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_submit\"]")
	@CacheLookup
	WebElement GetOTPCTA;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/cb_agree_wa_update\"]")
	@CacheLookup
	WebElement whatsppCheckbox;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/cb_agree_wa_update\"]")
	@CacheLookup
	WebElement terms;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/cb_agree_wa_update\"]")
	@CacheLookup
	WebElement privacyPolicy;
	
	public void EnterMobilefield(String mob) {
		mobileNumberField.click();
		mobileNumberField.sendKeys(mob);
	}
	
	public void EnterInvalidMobilefield(String mob) {
		mobileNumberField.clear();
		
		mobileNumberField.sendKeys(mob);
	}
	
	public String MobileNumberFieldValue()
	{
		return mobileNumberField.getAttribute("text");
	}
	
	public int MobileNumberValueCount(String value)
	{
		return value.length();
	}
	
	public boolean isLandPageSubmitBtnEnabled() {
		return GetOTPCTA.isEnabled();
		
	}
	
	
	public void ClickGetOTPCTA() {
		GetOTPCTA.click();
	}
	
}
