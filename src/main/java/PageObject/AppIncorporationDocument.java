package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppIncorporationDocument {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppIncorporationDocument(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	

	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_connect\"]")
	@CacheLookup
	public WebElement certificateOfIncorporationUpload;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Camera\"]")
	@CacheLookup
	public WebElement cameraOptionToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Flip Camera\"]")
	@CacheLookup
	public WebElement cameraFlipOption;
	
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
	
	public void ClickApplicantCibilUpload()
	{
		certificateOfIncorporationUpload.click();
	}
	
	public void ClickCameraOptionToUpload()
	{
		cameraOptionToUpalodDoc.click();
	}
	
	public void ClickCameraFlip()
	{
		cameraFlipOption.click();
	}
	
	public void ClickCameraCapture()
	{
		cameraCaptureToUpalodDoc.click();
	}
	
	public void ClickCinfirmCapturedImage1()
	{
		confirmToCapturedImageToUpalodDoc.click();
	}
	
	public void ClickCinfirmCapturedImage2()
	{
		confirmToCapturedImageToUpalodDoc2.click();
	}
	
	public void ClickSubmitToUploadDoc()
	{
		submitForUploadedDoc.click();
	}

}
