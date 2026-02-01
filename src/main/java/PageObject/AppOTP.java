package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Name;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class AppOTP {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppOTP(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_otp_1\"]")
	@CacheLookup
	WebElement otpField1;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_otp_2\"]")
	@CacheLookup
	WebElement otpField2;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_otp_3\"]")
	@CacheLookup
	WebElement otpField3;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_otp_4\"]")
	@CacheLookup
	WebElement otpField4;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_invalid\"]")
	@CacheLookup
	WebElement otpErrorMessage;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_submit\"]")
	WebElement otpSubmit;
	
	public void EnterField1(String otp1) {
	otpField1.sendKeys(otp1);
	}
		
	public void EnterField2(String otp2) {
	otpField2.sendKeys(otp2);
	}
			
	public void EnterField3(String otp3) {
	otpField3.sendKeys(otp3);
	}
	
	public void EnterField4(String otp4) {
	otpField4.sendKeys(otp4);
	}
	
	public void SubmitOTP() {
		otpSubmit.click();
		}
	
	public boolean isOTPErrorMessageDisplayed() {
		return otpErrorMessage.isDisplayed();
		}
	
	public String GetOTPErrorMessage() {
		return otpErrorMessage.getText();
        
    }
	
	public boolean isOTPPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(otpField1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public boolean isOTPSubmitDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(otpField1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
			
}

