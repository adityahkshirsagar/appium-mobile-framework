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

public class AppRequiredDocument {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppRequiredDocument(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//*[@text='Required Documents']")
	@CacheLookup
	WebElement requiredDocTitle;
	
	public boolean isRequiredDocumentTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(requiredDocTitle)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_loan_offer\" and @text=\"Business KYC\"]")
	@CacheLookup
	WebElement docBusinessKYC;
	
	public boolean isBusinessKYCDocAvailableToUpload() {
		try {
			return docBusinessKYC.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_loan_offer\" and @text=\"KYC\"]")
	@CacheLookup
	WebElement docKYC;

    public boolean isKYCDocAvailableToUpload() {
    	try {
			return docKYC.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_loan_offer\" and @text=\"Residential Address Proof\"]")
	@CacheLookup
	WebElement docResidentialAddressProof;

    public boolean isResAddressProofAvailableToUpload() {
    	try {
			return docResidentialAddressProof.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_loan_offer\" and @text=\"Incorporation Documents\"]")
	@CacheLookup
	WebElement docIncorporationDocument;

    public boolean isIncorporationDocumnetAvailableToUpload() {
    	try {
			return docIncorporationDocument.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_loan_offer\" and @text=\"CIBIL\"]")
	@CacheLookup
	WebElement docCibil;

    public boolean isCibilDocumnetAvailableToUpload() {
    	try {
			return docCibil.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
    }

    
  
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/status\" and @text=\"Received\"]")
	@CacheLookup
	public WebElement receivedMessageForBusinessKYC;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/status\"])[2]")
	@CacheLookup
	public WebElement receivedMessageForKYC;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/status\"])[3]")
	@CacheLookup
	public WebElement receivedMessageForResidentialAddressProof;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Residential Address Proof']/following-sibling::android.widget.TextView")
	@CacheLookup
	public WebElement statusTextForResAddressProof;
	
	public boolean isResiAddressProofStatusDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(statusTextForResAddressProof)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Business KYC']/following-sibling::android.widget.TextView")
	@CacheLookup
	public WebElement statusTextForBusinessKYC;
	
	public boolean isBusKYCStatusDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(statusTextForBusinessKYC)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='KYC']/following-sibling::android.widget.TextView")
	@CacheLookup
	public WebElement statusTextForPersonalKYC;
	
	public boolean isPersonalKYCStatusDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(statusTextForPersonalKYC)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cibil']/following-sibling::android.widget.TextView")
	@CacheLookup
	public WebElement statusTextForCibil;
	
	public boolean isCibilStatusDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(statusTextForCibil)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	
	
	
//	-------------------------------------------------------------------
	
	public void ClickBusinessKYC()
	{
		docBusinessKYC.click();
	}
	
	public void ClickKYC()
	{
		docKYC.click();
	}
	
	public void ClickResidentialAddressProof()
	{
		docResidentialAddressProof.click();
	}
	
	public void ClickIncorporationDocument()
	{
		docIncorporationDocument.click();
	}
	
	public void ClickCibilDocument()
	{
		docCibil.click();
	}
	
	public boolean isBusinessKYCReceivedMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(receivedMessageForBusinessKYC)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public boolean isPersonalKYCReceivedMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(receivedMessageForKYC)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public boolean isResAddressProofReceivedMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(receivedMessageForResidentialAddressProof)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public String getStatusForResAddressProofDocument() {
        return statusTextForResAddressProof.getText();
    }
	
	public String getStatusForBusinessKYCDocument() {
        return statusTextForBusinessKYC.getText();
    }
	
	public String getStatusForPersonalKYCDocument() {
        return statusTextForPersonalKYC.getText();
    }
	
	public String getStatusForCibilDocument() {
        return statusTextForCibil.getText();
    }
		

    public List<String> GetDocumentListAvailableToUpload() {
        Map<String, Supplier<Boolean>> docVisibilityMap = new LinkedHashMap<>();

        docVisibilityMap.put("Business KYC", this::isBusinessKYCDocAvailableToUpload);
        docVisibilityMap.put("Personal KYC", this::isKYCDocAvailableToUpload);
        docVisibilityMap.put("Residential Address Proof", this::isResAddressProofAvailableToUpload);
        docVisibilityMap.put("Incorporation Document", this::isIncorporationDocumnetAvailableToUpload);
        docVisibilityMap.put("Cibil", this::isCibilDocumnetAvailableToUpload);

        List<String> RequiredDocList = new ArrayList<>();

        for (Map.Entry<String, Supplier<Boolean>> entry : docVisibilityMap.entrySet()) {
            try {
                if (entry.getValue().get()) {
                	RequiredDocList.add(entry.getKey());
                }
            } catch (Exception e) {
                // Optional: skip or log if element is not present
            }
        }

        return RequiredDocList;
    }
}
