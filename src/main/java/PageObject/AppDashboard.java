package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppDashboard {

	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppDashboard(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(5));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/pet_whatsApp_number\"]")
	@CacheLookup
	WebElement whatsappNo;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/cb_no_whatsApp_number\"]")
	@CacheLookup
	WebElement IdoNotHaveWhatsapp;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_skip\"]")
	@CacheLookup
	WebElement whatsappSkip;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_submit\"]")
	@CacheLookup
	WebElement whatsappSubmit;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_apply_now\"]")
	@CacheLookup
	public
	WebElement dashboardApplyNow;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	@CacheLookup
	WebElement permissionWhileUsingApp1;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	@CacheLookup
	WebElement permissionWhileUsingApp2;
	
	public boolean isPermission1PopupDisplayed()
	{
		try {
			return
					permissionWhileUsingApp1.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
	}
	
	
	public boolean isWhatsappPopupDisplayed() {
		try {
			return
					whatsappSkip.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
		
	}
	
	
	public void EnterWhatsappNo(String mobile) {
		whatsappNo.sendKeys(mobile);
	}
	
	public void ClickIdoNotHaveWhatsApp() {
		IdoNotHaveWhatsapp.click();
	}
	
	// To check popup is displayed 
	
	public void ClickWhatsappSkip() {
		whatsappSkip.click();
	}
	
	public void ClickWhatsappSubmit() {
		whatsappSubmit.click();
	}
	
	public void ClickDashboardApplyNow(){
		dashboardApplyNow.click();
	}
	
	public void ClickPermissionWhileUsingApp1() {
		permissionWhileUsingApp1.click();
	}
	
	public void ClickPermissionWhileUsingApp2() {
		permissionWhileUsingApp2.click();
	}
	
	public boolean isDashboardPageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(whatsappSkip),
                    ExpectedConditions.visibilityOf(dashboardApplyNow)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
}
