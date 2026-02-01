package PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppKYC {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppKYC(AppiumDriver rdriver)
	{
		ldriver=rdriver;		
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_select_doc_type\"]")
	@CacheLookup
	WebElement kycdocDropdown;
	
	@AndroidFindBy(xpath="(//android.widget.RadioButton[@resource-id=\"com.example.app:id/radio\"])[1]")
	@CacheLookup
	WebElement PANcheckbox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_connect\"]")
	@CacheLookup
	WebElement PANUpload;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Camera\"]")
	@CacheLookup
	WebElement cameraOptionToUpalodDoc;
	
	public boolean isCameraOptionToUploadDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(cameraOptionToUpalodDoc)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/captureBtn\"]")
	@CacheLookup
	WebElement cameraCaptureToUpalodDoc;
	
	
	public boolean isCameraCaptureBtnDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(cameraCaptureToUpalodDoc)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/confirmImgBtn\"]")
	@CacheLookup
	WebElement confirmToCapturedImageToUpalodDoc;
	
	public void ClickConfirmToCapturedImageToUpalodDoc()
	{
		confirmToCapturedImageToUpalodDoc.click();
	}
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_done\"]")
	@CacheLookup
	WebElement confirmToCapturedImageToUpalodDoc2;
	
	public void ClickConfirmToCapturedImageToUpalodDoc2()
	{
		confirmToCapturedImageToUpalodDoc2.click();
	}
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_submit\"]")
	@CacheLookup
	public WebElement submitForUploadedDoc;
	
	public void ClickSubmitForUploadedDoc()
	{
		submitForUploadedDoc.click();
	}
	
	
	
	
	
	
	
	
	
//  //android.widget.TextView[@resource-id="com.example.app:id/tv_rejectReason"]	
//  ---------------------------------------
	
	
	public void SelectKYCDropdown()
	{
		kycdocDropdown.click();
	}
	
	public void SelectPANCheckbox()
	{
		PANcheckbox.click();
	}
	
	public void SelectPANUpload()
	{
		PANUpload.click();
	}
	
	public void SelectCameraOptionToUpload()
	{
		cameraOptionToUpalodDoc.click();
	}

	
	public void ClickCameraCapture()
	{
		cameraCaptureToUpalodDoc.click();
	}
	
    @AndroidFindBy(xpath="//*[@text='Pan Card (Father/ Husband)']")
	@CacheLookup
	WebElement docKYCFatherHusbandPanCard;

    public boolean isFatherHusbandPanCardAvailableToUpload() {
    	try {
			return docKYCFatherHusbandPanCard.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }
    
    @AndroidFindBy(xpath="//*[@text='AND']")
  	@CacheLookup
  	WebElement ANDTextForMultiDoc;

      public boolean isANDtextDisplayed() {
   			try {
   				return
   						ANDTextForMultiDoc.isDisplayed();
   			}
   			catch (NoSuchElementException e)
   		
   			{
   				return false;
   			}
   		}

    public List<String> GetDocumentListAvailableToUploadForPersonalKYC() {
        Map<String, Supplier<Boolean>> docVisibilityMap = new LinkedHashMap<>();

        docVisibilityMap.put("Personal KYC/Father/Husband PAN card", this::isFatherHusbandPanCardAvailableToUpload);
        docVisibilityMap.put("Personal KYC/Default List (PAN Card/Digilocker ePan Card)", this::isANDtextDisplayed);

        List<String> RequiredDocListforPersonalKYC = new ArrayList<>();

        for (Map.Entry<String, Supplier<Boolean>> entry : docVisibilityMap.entrySet()) {
            try {
                if (entry.getValue().get()) {
                	RequiredDocListforPersonalKYC.add(entry.getKey());
                }
            } catch (Exception e) {
                // Optional: skip or log if element is not present
            }
        }

        return RequiredDocListforPersonalKYC;
    }
}
