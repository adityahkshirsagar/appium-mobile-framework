package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppResidentialAddressProof {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppResidentialAddressProof(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_select_doc_type\"]")
	@CacheLookup
	public WebElement residentialAddProofdocDropdown;
	
	@AndroidFindBy(xpath="(//android.widget.RadioButton[@resource-id=\"com.example.app:id/radio\"])[1]")
	@CacheLookup
	public WebElement aadharCheckbox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_connect\"]")
	@CacheLookup
	public WebElement aadharUpload;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Camera\"]")
	@CacheLookup
	public WebElement cameraOptionToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/captureBtn\"]")
	@CacheLookup
	public WebElement cameraCaptureToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/confirmImgBtn\"]")
	@CacheLookup
	public WebElement confirmToCapturedImageToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_done\"]")
	@CacheLookup
	public WebElement confirmToCapturedImageToUpalodDoc2;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_submit\"]")
	@CacheLookup
	public WebElement submitForUploadedDoc;
	
//	-------------------------------------------------
	
//	public void ClickBankListDropdown()
//	{
//		selectBankDropdown.click();
//	}

}
