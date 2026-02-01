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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppBusinessKYC {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppBusinessKYC(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_select_doc_type\"]")
	@CacheLookup
	public WebElement businessKYCdocDropdown;
	
	@AndroidFindBy(xpath="(//android.widget.RadioButton[@resource-id=\"com.example.app:id/radio\"])[1]")
	@CacheLookup
	WebElement GSTcertificateCheckbox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_connect\"]")
	@CacheLookup
	WebElement GSTcertificateUpload;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_submit\"]")
	@CacheLookup
	WebElement submitForUploadedDoc;
	
	
//	-------------------------------------------------------------------
	
	public void SelectBusinessKYCDropdown()
	{
		businessKYCdocDropdown.click();
	}
	
	public void ClickGSTcertificateCheckbox()
	{
		GSTcertificateCheckbox.click();
	}
	
	public void ClickGSTcertificateUpload()
	{
		GSTcertificateUpload.click();
	}
	
	public void ClickSubmitForUploadedDocs()
	{
		submitForUploadedDoc.click();
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/document_head\" and @text=\"Shareholding Pattern\"]")
   	@CacheLookup
   	WebElement docBusinessKYCShareholdingPattern;

       public boolean isShareholdingPatternAvailableToUpload() {
    	   try {
				return docBusinessKYCShareholdingPattern.isDisplayed();
			}
			catch (NoSuchElementException e)
		
			{
				return false;
			}
       }
       
       
       @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/document_head\" and @text=\"Company PAN Card\"]")
   	@CacheLookup
   	WebElement docBusinessKYCCompanyPANCard;

       public boolean isCompanyPanCardAvailableToUpload() {
       	try {
   			return docBusinessKYCCompanyPANCard.isDisplayed();
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
	
    public List<String> GetDocumentListAvailableToUploadForBusinessKYC() {
        Map<String, Supplier<Boolean>> docVisibilityMap = new LinkedHashMap<>();

        docVisibilityMap.put("Business KYC/Company PAN card", this::isCompanyPanCardAvailableToUpload);
        docVisibilityMap.put("Business KYC/Sahreholding Pattern", this::isShareholdingPatternAvailableToUpload);
        docVisibilityMap.put("Business KYC/Default List (GST, Shop Establisment, Udyog Aadhar)", this::isANDtextDisplayed);

        List<String> RequiredDocListforBusinessKYC = new ArrayList<>();

        for (Map.Entry<String, Supplier<Boolean>> entry : docVisibilityMap.entrySet()) {
            try {
                if (entry.getValue().get()) {
                	RequiredDocListforBusinessKYC.add(entry.getKey());
                }
            } catch (Exception e) {
                // Optional: skip or log if element is not present
            }
        }

        return RequiredDocListforBusinessKYC;
    }

}
