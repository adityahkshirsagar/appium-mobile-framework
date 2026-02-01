package PositiveFlow;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import GenericUtilities.BaseClass;
import GenericUtilities.Databaseutil;
import GenericUtilities.ListenerClass;
import GenericUtilities.ThreadLocalClass;

@Listeners(ListenerClass.class)
public class DocumentUpload extends BaseClass{
	
	List<String> RequiredDocuments;
	Map<String, Runnable> docActionMap = new HashMap<>();
	
	@Test(priority=1)
	public void GetListOfDocuments() throws InterruptedException
	
	{	
		appLoanApplicationDashScreen.ClickDocumentsAddMore();
		appRequiredDocument.isRequiredDocumentTitleDisplayed();
		RequiredDocuments=appRequiredDocument.GetDocumentListAvailableToUpload();

		if (RequiredDocuments.isEmpty()) {
		    ThreadLocalClass.gettestlevel().log(Status.WARNING, "No documents were visible.");
		} else {
		    ThreadLocalClass.gettestlevel().log(Status.PASS, "Visible documents: " + RequiredDocuments);
		    System.out.println("Visible documents: " + RequiredDocuments);
		}
	}
	
	@Test(priority=2)
	public void UploadDocuments()
	{
		initDocumentActions();   
		handleVisibleDocuments();
	}
	
	public void initDocumentActions() {
        docActionMap.put("Business KYC", this::DocumentUploadBusinessKYC);
        docActionMap.put("Personal KYC", this::DocumentPersonalKYC);
        docActionMap.put("Residential Address Proof", this::DocumentResAddress);     
        docActionMap.put("Cibil", this::DocumentCibil); 
    }
	
	public void handleVisibleDocuments() {
        for (String doc : RequiredDocuments) {
            Runnable action = docActionMap.get(doc);
            if (action != null) {
                action.run();
            } else {
                System.out.println("No action defined for document: " + doc);
            }
        }
    }
	
	public void DocumentUploadBusinessKYC(){
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user's Document Upload Functionality -<br> Business KYC, Personal KYC, Residential Address Proof");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		try {	
		appRequiredDocument.isBusinessKYCDocAvailableToUpload();
		appRequiredDocument.ClickBusinessKYC();
		wait.until(ExpectedConditions.visibilityOf(appBusinessKYC.businessKYCdocDropdown));
		appBusinessKYC.SelectBusinessKYCDropdown();
		appBusinessKYC.ClickGSTcertificateCheckbox();
		appBusinessKYC.ClickGSTcertificateUpload();
		appBankStatement.SelectCameraToUploadDoc();
		appBankStatement.ClickCameraFlipForDoc();
		appBankStatement.ClickCameraCaptureForDoc();
		appBankStatement.ClickConfirmForClickedImage();
		appBankStatement.ClickConfirmForClickedImage2();
		appBusinessKYC.ClickSubmitForUploadedDocs();
		Thread.sleep(3000);	
	} catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }
		
		appRequiredDocument.isBusKYCStatusDisplayed();
		
		boolean isBusinessKYCReceivedMessageDisplayed = appRequiredDocument.isBusinessKYCReceivedMessageDisplayed();
		if (!isBusinessKYCReceivedMessageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Business KYC document not uploaded");
			Assert.fail("Business KYC document upload failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Business KYC document uploaded successfully");
		}
		ThreadLocalClass.gettestlevel().log(Status.INFO, "Business KYC Uploaded successfully : Document Type - business_kyc");
		
	}
		
		public void DocumentPersonalKYC()
		{
		appRequiredDocument.isKYCDocAvailableToUpload();
		
		String PersonalKYCDocumentCurrentstatus = appRequiredDocument.getStatusForPersonalKYCDocument();
		if (PersonalKYCDocumentCurrentstatus.equals("Completed"))
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Personal KYC document already present and verified");
			driver.navigate().back();
			driver.navigate().back();
		}
		else
		{
		appRequiredDocument.ClickKYC();
		appkyc.SelectKYCDropdown();
		appkyc.SelectPANCheckbox();
		appkyc.SelectPANUpload();
		appkyc.SelectCameraOptionToUpload();
		appkyc.isCameraCaptureBtnDisplayed();
		appkyc.ClickCameraCapture();
		appkyc.ClickConfirmToCapturedImageToUpalodDoc();
		appkyc.ClickConfirmToCapturedImageToUpalodDoc2();
		appkyc.ClickSubmitForUploadedDoc();
		
		appRequiredDocument.isPersonalKYCStatusDisplayed();
		
		boolean isPersonalKYCReceivedMessageDisplayed = appRequiredDocument.isPersonalKYCReceivedMessageDisplayed();
		if (!isPersonalKYCReceivedMessageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Personal KYC document not uploaded");
			Assert.fail("Personal KYC document upload failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Personal KYC document uploaded successfully");
		}
		
		}
	
		ThreadLocalClass.gettestlevel().log(Status.INFO, "Personal KYC Uploaded successfully : Document Type - kyc");

	}
	
	
	public void DocumentResAddress() {
		
		appRequiredDocument.isResiAddressProofStatusDisplayed();
		
		String ResAddressProofDocumentCurrentstatus = appRequiredDocument.getStatusForResAddressProofDocument();
		
		if (ResAddressProofDocumentCurrentstatus.equals("Completed"))
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Residential address proof document already present and verified");
			driver.navigate().back();
			driver.navigate().back();
			
		}
		
		else
		{
		appRequiredDocument.ClickResidentialAddressProof();
		appResidentialAddressProof.residentialAddProofdocDropdown.click();
		appResidentialAddressProof.aadharCheckbox.click();
		appResidentialAddressProof.aadharUpload.click();
		appResidentialAddressProof.cameraOptionToUpalodDoc.click();
//		wait.until(ExpectedConditions.elementToBeClickable(appResidentialAddressProof.cameraCaptureToUpalodDoc));
		appResidentialAddressProof.cameraCaptureToUpalodDoc.click();
		appResidentialAddressProof.confirmToCapturedImageToUpalodDoc.click();
		appResidentialAddressProof.confirmToCapturedImageToUpalodDoc2.click();
		appResidentialAddressProof.submitForUploadedDoc.click();
		
		appRequiredDocument.isResAddressProofReceivedMessageDisplayed();

		boolean isResAddressProofReceivedMessageDisplayed = appRequiredDocument.isResAddressProofReceivedMessageDisplayed();
		if (!isResAddressProofReceivedMessageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Residential address proof document not uploaded");
			Assert.fail("Residential address proof document upload failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Residential address proof document uploaded successfully");
		}
	}
		
		ThreadLocalClass.gettestlevel().log(Status.INFO, "Residential Address Proof Uploaded successfully : Document Type - res_address_proof");
				
		driver.navigate().back();
		
		boolean isDocUploadCompleteTickDisplayed = appLoanApplicationDashScreen.isDocUploadCompleteTickDisplayed();
		if (!isDocUploadCompleteTickDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"All documents not uploaded successfully");
			Assert.fail("Document upload failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"All documents uploaded successfully");
		}

		driver.navigate().back();
		
}			
	public void DocumentCibil() {
	
	appRequiredDocument.isCibilDocumnetAvailableToUpload();
	
	appRequiredDocument.ClickResidentialAddressProof();
	appResidentialAddressProof.residentialAddProofdocDropdown.click();
	appResidentialAddressProof.aadharCheckbox.click();
	appResidentialAddressProof.aadharUpload.click();
	appResidentialAddressProof.cameraOptionToUpalodDoc.click();
//	wait.until(ExpectedConditions.elementToBeClickable(appResidentialAddressProof.cameraCaptureToUpalodDoc));
	appResidentialAddressProof.cameraCaptureToUpalodDoc.click();
	appResidentialAddressProof.confirmToCapturedImageToUpalodDoc.click();
	appResidentialAddressProof.confirmToCapturedImageToUpalodDoc2.click();
	appResidentialAddressProof.submitForUploadedDoc.click();
	
	appRequiredDocument.isResAddressProofReceivedMessageDisplayed();

	boolean isResAddressProofReceivedMessageDisplayed = appRequiredDocument.isResAddressProofReceivedMessageDisplayed();
	if (!isResAddressProofReceivedMessageDisplayed) {
		ThreadLocalClass.gettestlevel().log(Status.FAIL,"Residential address proof document not uploaded");
		Assert.fail("Residential address proof document upload failed");
		}
	else
	{
		ThreadLocalClass.gettestlevel().log(Status.PASS,"Residential address proof document uploaded successfully");
	}
	
	ThreadLocalClass.gettestlevel().log(Status.INFO, "Residential Address Proof Uploaded successfully : Document Type - res_address_proof");
			
	driver.navigate().back();
	
	boolean isDocUploadCompleteTickDisplayed = appLoanApplicationDashScreen.isDocUploadCompleteTickDisplayed();
	if (!isDocUploadCompleteTickDisplayed) {
		ThreadLocalClass.gettestlevel().log(Status.FAIL,"All documents not uploaded successfully");
		Assert.fail("Document upload failed");
		}
	else
	{
		ThreadLocalClass.gettestlevel().log(Status.PASS,"All documents uploaded successfully");
	}

	driver.navigate().back();
	
}
		@Test(priority=3)
		public void DocumentUploadDBValidation() throws InterruptedException {

		String query_document = Databaseutil.getQuery("query_document", application_code);

		String DBDocumentCategory = databaseutil.returnValueByColumnname(query_document, "document_category");
				
		if (DBDocumentCategory!=null) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"Document entry successfully created in database for documents : <b>"+DBDocumentCategory+"</b>");
		} else {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Documents not get uploaded and shown in database");
		}
	}
}


