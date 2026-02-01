package NegativeFlow;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import GenericUtilities.BaseClass;
import GenericUtilities.Databaseutil;
import GenericUtilities.ListenerClass;
import GenericUtilities.ThreadLocalClass;
import PageObject.AppBankStatement;
import PageObject.AppBusinessKYC;
import PageObject.AppDashboard;
import PageObject.AppDashboard_2;
import PageObject.AppOTP;
import PageObject.AppFormStep_1;
import PageObject.AppFormStep_2;
import PageObject.AppFormStep_3;
import PageObject.AppKYC;
import PageObject.AppLandingPage;
import PageObject.AppAmount;
import PageObject.AppApplicationDashScreen;
import PageObject.AppOfferScreen;
import PageObject.AppRequiredDocument;
import PageObject.AppResidentialAddressProof;
import net.bytebuddy.utility.nullability.AlwaysNull;

/**
 * @author : Aditya K.
 * @Description : This class verifies negative data field validation scenarios for mobile app pre-sanction journey.
 */

@Listeners(ListenerClass.class)
public class NegativeFlowForDataFields extends BaseClass {

//	String name = randome5string() + " " + randome5string(); // Enter name
	String firstName = faker1.name().firstName();
	String lastName = faker1.name().lastName();
	String name = firstName + " " + lastName; // Enter name
	String mobile_no = "6" + randome9Num(); // Enter Mobile number
	String amount = "3" + randome5Num(); // Enter amount
	String monthly_sales = "2" + randome5Num(); // Enter monthly sales
	String email_id = ((firstName + "." + lastName + randome4Num() + "@mail.com").replace(" ", ""));
//	String email_id = faker1.internet().emailAddress();// Enter email field
	String pan = randome3string() + "PQ"+randome4Num() + "A"; //
//	String scriptbusiAddBuild=randome4Num()+"-"+randome4string();
	String scriptbusiAddBuild = faker1.address().buildingNumber();// Enter Buss Address building (address_line_1)
//	String scriptResAddBuild=randome5Num()+"-"+randome5string(); // Enter Res Address building (address_line_1)
	String scriptResAddBuild = faker1.address().buildingNumber(); // Enter Res Address building (address_line_1)
//	String scriptbusiAddArea=randome5string(); // Enter Buss Address area (address_line_2)
	String scriptbusiAddArea = faker1.address().streetAddress(); // Enter Buss Address area (address_line_2)
//	String scriptResAddArea=randome5string(); // Enter Res Address area (address_line_2)
	String scriptResAddArea = faker1.address().streetAddress(); // Enter Res Address area (address_line_2)

	private String application_code;
	private String query_applicant_detail;
	private String query_application;
	private String query_business_detail;
	private String query_finance_detail;
	private String query_experian_transactions;
	private String query_ckyc_transactions;
	private String query_document_1;
	private String query_document_2;
	private String query_document_3;
	private String query_document_4;
	private String query_document_5;
//	private String query_otp_user;
	private String DocumentCategory;

//	String scriptBusiPincode = generateRandomPincode(faker1);
	String scriptBusiPincode = "400016";
//	String scriptResPincode = generateRandomPincode(faker1);
	String scriptResPincode = "411012";
	String scriptBusiName = "BALAMURUGAN STORE";
	String scriptBusiProduct = "Dairy";
	String scriptMonthlySales = "999999";
	WebDriverWait wait;
	SoftAssert softassert = new SoftAssert();
	

		private void testFieldValidation(String fieldToTest, Map<String, String> invalidData) throws InterruptedException {
			
			String currentMethodName = getCurrentMethodName();
	        
//			if(currentMethodName.equals("NegativeLandingPage"))
//			{
			if (fieldToTest.equals("Mobile_Number")) {
           
	        }
//			}
	        
			else if(fieldToTest.equals("First_Name"))
	        {
	        	form2.EnterLastNameField(lastName);
	        	form2.SelectMonthlySales();
	            form2.SelectMonthlySalesFourtoTen();
	            form2.SelectGSTYes();
	            
	        	form2.ClearFirstNameField();
				boolean isForm2SubmitBtnEnabled=form2.isForm2SubmitBtnEnabled();
				if(isForm2SubmitBtnEnabled)
				{
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button enabled without first name details");
//					System.out.println("Submit button enabled without name details");
				}
				else
				{
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled without first name details");
//					System.out.println("Submit button disabled without name details");
				}
				softassert.assertFalse(isForm2SubmitBtnEnabled,"Submit button enabled without first name details");
	        }
			else if(fieldToTest.equals("Last_Name"))
	        {	
				form2.ClearLastNameField();
				form2.ClearFirstNameField();
	        	form2.EnterFirstNameField(firstName);
//	        	form2.SelectMonthlySales();
//	            form2.SelectMonthlySalesFourtoTen();
//	            form2.SelectGSTYes();
	            
	        	form2.ClearLastNameField();
				boolean isForm2SubmitBtnEnabled=form2.isForm2SubmitBtnEnabled();
				boolean isLastNameErrorDisplayed =form2.isLastNameErrorDisplayed();
				if(isForm2SubmitBtnEnabled && !isLastNameErrorDisplayed)
				{
					ThreadLocalClass.gettestlevel().log(Status.PASS, "As the last name is non-madatory field <b>submit button enabled </b>without last name details and <b>error message not displayed</b>");
//					System.out.println("Submit button enabled without name details");
				}
				else
				{
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button disabled without last name details");
//					System.out.println("Submit button disabled without last name details");
				}
	        }
//	        else if(fieldToTest.equals("Email_ID")) {
//	        	form2.ClearNameField();
//	        	form2.EnterNameField(name);
//	        	
//	        	boolean isForm2SubmitBtnEnabled=form2.isForm2SubmitBtnEnabled();
//				if(isForm2SubmitBtnEnabled)
//				{	
//					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button enabled without email details");
//					System.out.println("Submit button enabled without email details");
//				}
//				else
//				{
//					ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled without email details");
//					System.out.println("Submit button disabled without email details");
//				}
//				softassert.assertFalse(isForm2SubmitBtnEnabled,"Submit button enabled without email details");
//	        	
//	        	
//	        }
	        else if(fieldToTest.equals("Amount")) {
//	        	appAmount.ClickAmountField();
	        	appAmount.ClearInvalidAmount();
	        	boolean IsCheckEligibilityBtnEnabled=appAmount.IsCheckEligibilityBtnEnabled();
				if(IsCheckEligibilityBtnEnabled)
				{
//					System.out.println("Submit button enabled without loan amount details");
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button enabled without amount details");
				}
				else
				{
//					System.out.println("Submit button disabled without loan amount details");
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled without amount details");
				}
				softassert.assertFalse(IsCheckEligibilityBtnEnabled, "Submit button enabled without amount details");
	        }
	        else if(fieldToTest.equals("Monthly_total_sales")) {
	        	formStep1.SelectGenderFemale();
	        	formStep1.SelectDOBField();
	        	int startX = (int) (formStep1.dobYearPicker.getLocation().getX()
						+ formStep1.dobYearPicker.getSize().getWidth() / 2);
	        	int startY = (int) (formStep1.dobYearPicker.getLocation().getY()
						+ formStep1.dobYearPicker.getSize().getHeight() * 0.60);
	        	String desiredValue = "1996"; // Change this to your desired value
	        	int maxInterations = 05;
	        	int iterationCount = 0;
	        	while (iterationCount < maxInterations) {
	        		int endX = startX; // Adjust the distance to scroll as needed
	        		int endY = startY + 300; // Adjust the distance to scroll as needed
	        		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX,startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(),endX, endY))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Collections.singletonList(sequence));
					iterationCount++;
					if (driver.getPageSource().contains(desiredValue)) {
						break;}
				}
				Thread.sleep(2000);
				formStep1.SelectDOB();
				
				formStep1.SelectBusinessAge2to5Years();
				formStep1.SelectCurrentAccountYes();
				formStep1.SelectGSTRegistrationYes();
				formStep1.SelectOccupationBusinessMan();
				
				formStep1.ClearMonthlySales();
				boolean IsFormStep1NextBtnEnabled=formStep1.IsFormStep1NextBtnEnabled();
				if(IsFormStep1NextBtnEnabled)
				{
//					System.out.println("Next button enabled without MTO details");
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled without MTO details");
				}
				else
				{
//					System.out.println("Next button disabled without MTO details");
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled without MTO details");
				}
				softassert.assertFalse(IsFormStep1NextBtnEnabled,"Next button enabled without MTO details");
				
				}
	        else if(fieldToTest.equals("Business_Pincode")) {
	        	
	        	formStep2.EnterResidentialPincode(scriptResPincode);
	        	formStep2.SelectResidentialOwnershipOwned();
	        	formStep2.SelectBusinessOwnershipOwned();
	        	formStep2.EnterPersonalPan(pan);
	        	formStep2.SelectLegalStatusProprietorship();
	        	formStep2.ClearBusinessPincode();
	        	boolean IsFormStep2NextBtnEnabled=formStep2.IsFormStep2NextBtnEnabled();
				if(IsFormStep2NextBtnEnabled)
				{
//					System.out.println("Next button enabled without Business Pincode details");
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled without Business Pincode details");
				}
				else
				{
//					System.out.println("Next button disabled without Business Pincode details");
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled without Business Pincode details");
				}
				softassert.assertFalse(IsFormStep2NextBtnEnabled,"Next button enabled without Business Pincode details");
	        }
	        
	        else if(fieldToTest.equals("Residential_Pincode")) {
	        	 
	        	formStep2.ClearResidentialPincode();
	        	boolean IsFormStep2NextBtnEnabled=formStep2.IsFormStep2NextBtnEnabled();
				if(IsFormStep2NextBtnEnabled)
				{
//					System.out.println("Next button enabled without Residential Pincode details");
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled without Residential Pincode details");
				}
				else
				{
//					System.out.println("Next button disabled without Residential Pincode details");
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled without Residential Pincode details");
				}
				softassert.assertFalse(IsFormStep2NextBtnEnabled,"Next button enabled without Residential Pincode details");
	        }
	        
	        else if(fieldToTest.equals("Personal_PAN")) {
	        	 
	        	formStep2.ClearPersonalPan();
	        	boolean IsFormStep2NextBtnEnabled=formStep2.IsFormStep2NextBtnEnabled();
				if(IsFormStep2NextBtnEnabled)
				{
//					System.out.println("Next button enabled without Personal PAN details");
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled without Personal PAN details");
				}
				else
				{
//					System.out.println("Next button disabled without Personal PAN details");
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled without Personal PAN details");
				}
				softassert.assertFalse(IsFormStep2NextBtnEnabled,"Next button enabled without Personal PAN details");
				
				formStep2.EnterPersonalPan("ABCPH5267");
				if (IsFormStep2NextBtnEnabled) {
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled for invalid personal PAN details - ABCPH5267");
//					System.out.println("Next button enabled for invalid personal PAN details");
				} else {
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled for invalid Personal PAN - ABCPH5267");
//					System.out.println("Next button disabled for invalid Personal PAN - ABCPH5267");
				}
				
				formStep2.ClearPersonalPan();
				
				formStep2.EnterPersonalPan("ABCPH267Y");
				if (IsFormStep2NextBtnEnabled) {
					ThreadLocalClass.gettestlevel().log(Status.FAIL, "Next button enabled for invalid personal PAN details - ABCPH267Y");
//					System.out.println("Next button enabled for invalid personal PAN details");
				} else {
					ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled for invalid Personal PAN - ABCPH267Y");
//					System.out.println("Next button disabled for invalid Personal PAN - ABCPH267Y");
				}
				
				formStep2.ClearPersonalPan();
	        }
	            
	//--------------------------//------------------------//---------------------//---------------------//-------------------//------------------//		
			
			for (Map.Entry<String, String> entry : invalidData.entrySet()) {
				String invalidValue = entry.getKey();
				String expectedError = entry.getValue();

				// Simulate entering invalid data into the specific field
				if (fieldToTest.equals("Mobile_Number")) {
					lp.EnterInvalidMobilefield(invalidValue);

					boolean isLandPageSubmitBtnEnabled = lp.isLandPageSubmitBtnEnabled();
//		            softassert.assertFalse(isSubmitBtnEnabled,expectedError + invalidValue);

					if (isLandPageSubmitBtnEnabled) {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue );
//						System.out.println(expectedError + " - " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled for invalid mobile number - " + invalidValue);
//						System.out.println("Submit button disabled for invalid mobile number - " + invalidValue);
					}
				} else if (fieldToTest.equals("First_Name")) {
										
					form2.EnterInvalidFirstNameField(invalidValue);
					
					boolean isFirstNameErrorDisplayed = form2.isFirstNameErrorDisplayed();
					if (isFirstNameErrorDisplayed) {
						ThreadLocalClass.gettestlevel().log(Status.PASS, expectedError + " - " + form2.GetFirstNameErrorMessage());
//						System.out.println(expectedError + " - " + invalidValue);
						ListenerClass listrner= new ListenerClass();
				    	String screenshotPath = listrner.captureScreenshotAndAttach(driver, "Error message screenshot -");
				    	ThreadLocalClass.gettestlevel().pass("<b>Screenshot for pass test</b>", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					} else {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, "Error message shown for first name - " + invalidValue);
//						System.out.println("Error message shown for first name - " + invalidValue);
					}
					
//					boolean isForm2SubmitBtnEnabled = form2.isForm2SubmitBtnEnabled();
//					if (isForm2SubmitBtnEnabled) {
//						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue );
////						System.out.println(expectedError + " - " + invalidValue);
//					} else {
//						ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled for invalid first name - " + invalidValue);
////						System.out.println("Submit button disabled for invalid first name - " + invalidValue);
//					}
				} else if (fieldToTest.equals("Last_Name")) {
					
						form2.EnterInvalidLastNameField(invalidValue);
						
						boolean isLastNameErrorDisplayed = form2.isLastNameErrorDisplayed();
						if (isLastNameErrorDisplayed) {
							ThreadLocalClass.gettestlevel().log(Status.PASS, expectedError + " - " + form2.GetLastNameErrorMessage());
//							System.out.println(expectedError + " - " + invalidValue);
							ListenerClass listrner= new ListenerClass();
					    	String screenshotPath = listrner.captureScreenshotAndAttach(driver, "Error message screenshot -");
					    	ThreadLocalClass.gettestlevel().pass("<b>Screenshot for pass test</b>", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
						} else {
							ThreadLocalClass.gettestlevel().log(Status.FAIL, "Error message displayed for - " + invalidValue);
//							System.out.println("Error message displayed for - " + invalidValue);
						}
						
//						boolean isForm2SubmitBtnEnabled = form2.isForm2SubmitBtnEnabled();
//						if (isForm2SubmitBtnEnabled) {
//							ThreadLocalClass.gettestlevel().log(Status.PASS, expectedError + " - " + invalidValue );
//							System.out.println(expectedError + " - " + invalidValue);
//						} else {
//							ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button disabled for invalid name - " + invalidValue);
//							System.out.println("Submit button disabled for invalid name - " + invalidValue);
//						}
				}								
//				} else if (fieldToTest.equals("Email_ID")) {
//		//				otp.EnterInvalidEmailfield(invalidValue);
//						form2.Form2Submit();
//		
//						boolean isForm2SubmitBtnEnabled = form2.isForm2SubmitBtnEnabled();
//					boolean isEmailFieldErrorMessageDisplayed = form2.isEmailFieldErrorMessageDisplayed();
//		            softassert.assertFalse(isSubmitBtnEnabled,expectedError + invalidValue);

//					if (isEmailFieldErrorMessageDisplayed) {
//						ThreadLocalClass.gettestlevel().log(Status.PASS, expectedError + " - " + invalidValue );
//						System.out.println(expectedError + " - " + invalidValue);
//					} else {
//						ThreadLocalClass.gettestlevel().log(Status.FAIL, "Error message not shown for invalid email -" + invalidValue);
//						System.out.println("Error message not shown for invalid email - " + invalidValue);
//					}

//				}
//				else if (fieldToTest.equals("OTP_Fields")) {
//				otp.EnterInvalidEmailfield(invalidValue);
//				otp.Form2Submit();
//
//				boolean isForm2SubmitBtnEnabled = otp.isForm2SubmitBtnEnabled();
//				boolean isEmailFieldErrorMessageDisplayed = otp.isEmailFieldErrorMessageDisplayed();
//	            softassert.assertFalse(isSubmitBtnEnabled,expectedError + invalidValue);
//
//				if (isEmailFieldErrorMessageDisplayed) {
//					System.out.println(expectedError + " - " + invalidValue);
//				} else {
//					System.out.println("Error message shown for invalid email - " + invalidValue);
//				}
//			}
				else if (fieldToTest.equals("Amount")) {
					appAmount.EnterInvalidAmount(invalidValue);
					boolean IsCheckEligibilityBtnEnabled = appAmount.IsCheckEligibilityBtnEnabled();
//		            softassert.assertFalse(isSubmitBtnEnabled,expectedError + invalidValue);

					if (IsCheckEligibilityBtnEnabled) {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue );
//						System.out.println(expectedError + " - " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled for invalid amount -" + invalidValue);
//						System.out.println("Submit button disabled for invalid loan amount -" + invalidValue);
					}

				} else if (fieldToTest.equals("Monthly_total_sales")) {
					formStep1.EditMonthlySales(invalidValue);
					boolean IsFormStep1NextBtnEnabled = formStep1.IsFormStep1NextBtnEnabled();
//		            softassert.assertFalse(isSubmitBtnEnabled,expectedError + invalidValue);

					if (IsFormStep1NextBtnEnabled) {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue );
//						System.out.println(expectedError + " - " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button disabled for invalid MTO " + invalidValue);
//						System.out.println("Next button disabled for invalid MTO " + invalidValue);
					}
				}
				
				else if (fieldToTest.equals("Business_Pincode")) {
					formStep2.EnterBusinessPincode(invalidValue);
					boolean IsFormStep2NextBtnEnabled = formStep2.IsFormStep2NextBtnEnabled();
//		            softassert.assertFalse(IsFormStep2NextBtnEnabled,expectedError + invalidValue);

					if (IsFormStep2NextBtnEnabled) {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue );
//						System.out.println(expectedError + " - " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button enabled for invalid Business Pincode " + invalidValue);
//						System.out.println("Next button enabled for invalid Business Pincode " + invalidValue);
					}
					
				}else if (fieldToTest.equals("Residential_Pincode")) {
					formStep2.EnterResidentialPincode(invalidValue);
					boolean IsFormStep2NextBtnEnabled = formStep2.IsFormStep2NextBtnEnabled();
//		            softassert.assertFalse(IsFormStep2NextBtnEnabled,expectedError + invalidValue);

					if (IsFormStep2NextBtnEnabled) {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue);
//						System.out.println(expectedError + " - " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Next button enabled for invalid Residential Pincode " + invalidValue);
//						System.out.println("Next button enabled for invalid Residential Pincode " + invalidValue);
					}
					
				}else if (fieldToTest.equals("Personal_PAN")) {
					
					formStep2.EnterPersonalPan(invalidValue);
					boolean IsPersonalPANErrorMessageDisplayed = formStep2.IsPersonalPANErrorMessageDisplayed();
//		            softassert.assertFalse(IsFormStep2NextBtnEnabled,expectedError + invalidValue);

					if (IsPersonalPANErrorMessageDisplayed) {
						ThreadLocalClass.gettestlevel().log(Status.PASS, "Error message displayed for invalid Personal PAN " + invalidValue);
//						System.out.println("Error message displayed for invalid Personal PAN " + invalidValue);
					} else {
						ThreadLocalClass.gettestlevel().log(Status.FAIL, expectedError + " - " + invalidValue);
//						System.out.println(expectedError + " - " + invalidValue);
					}										
				}								
			}
		} 

		
		
		//-------------------//---------------------------//--------------------------//-------------------------------
		
		
		@Test (priority = 1)
		public void Negative_LandingPage() throws InterruptedException {
			
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on Landing Page");
			
			boolean isLandPageSubmitBtnEnabled=lp.isLandPageSubmitBtnEnabled();
			softassert.assertFalse(isLandPageSubmitBtnEnabled,"Submit button enabled without mobile number details");

			LinkedHashMap<String, String> invalidDataForMobileNumber = new LinkedHashMap<>();
//			invalidDataForMobileNumber.put(randome1Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome2Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome3Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome4Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome5Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome6Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome7Num(), "Submit button enabled for invalid mobile number - ");
//			invalidDataForMobileNumber.put(randome8Num(), "Submit button enabled for invalid mobile number - ");
			invalidDataForMobileNumber.put(randome9Num(), "Submit button enabled for invalid mobile number - ");
			
//			invalidDataForMobileNumber.put("0" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '0' ");
//			invalidDataForMobileNumber.put("1" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '1' ");
//			invalidDataForMobileNumber.put("2" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '2' ");
//			invalidDataForMobileNumber.put("3" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '3' ");
//			invalidDataForMobileNumber.put("4" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '4' ");
			invalidDataForMobileNumber.put("5" + randome9Num(), "Submit button enabled for invalid mobile_no starts with '5' ");
			
			testFieldValidation("Mobile_Number", invalidDataForMobileNumber);
			
			lp.EnterInvalidMobilefield("67896789123");			
			String mobileNumberValue = lp.MobileNumberFieldValue();	        
	        int MobiledigitCount = lp.MobileNumberValueCount(mobileNumberValue);
	        if (MobiledigitCount == 10) {
//	            System.out.println("The mobile number field accepts exactly 10 digits.");
	            ThreadLocalClass.gettestlevel().log(Status.PASS, "The mobile number field accepts exactly 10 digits.");
	        } else {
//	            System.out.println("The mobile number accepts more than 10 digits. It contains " + MobiledigitCount + " digits.");
	            ThreadLocalClass.gettestlevel().log(Status.FAIL, "The mobile number accepts more than 10 digits. It contains " + MobiledigitCount + " digits.");
	        }
	        
			lp.EnterInvalidMobilefield(mobile_no);
			ThreadLocalClass.gettestlevel().log(Status.INFO, "Mobile No used for lead creation - "+ mobile_no);
			System.out.println(mobile_no);
			lp.ClickGetOTPCTA();
			
			boolean isOTPPageDisplayed = otp.isOTPPageDisplayed();
			if (!isOTPPageDisplayed) {
//				System.out.println("Flow not redirected to otp screen");
				ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to otp screen");
				Assert.assertFalse(isOTPPageDisplayed,"Flow not redirected to otp screen");
	        }
			else
			{
//				System.out.println("Flow successfully redirected to otp screen");
				ThreadLocalClass.gettestlevel().log(Status.PASS, "Flow successfully redirected to otp screen");
			}
			
//			softassert.assertAll();
		}	
		
		@Test (priority = 2)
		public void Negative_OTPPage() throws InterruptedException
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on Form 2");
			
		
//			LinkedHashMap<String, String> invalidDataForEmail = new LinkedHashMap<>();
//			invalidDataForEmail.put("test data@mail.com", "Error message shown for invalid email ");
//			invalidDataForEmail.put("testdatamail.com", "Error message shown for invalid email ");
//			invalidDataForEmail.put("testdata@mailcom", "Error message shown for invalid email ");
//			invalidDataForEmail.put("testdatamail.com", "Error message shown for invalid email ");
//			
//			testFieldValidation("Email_ID", invalidDataForEmail);
			
//			otp.ClearEmailfield();
//        	otp.EnterNameField(email_id);
			
			otp.EnterField1("2");
			
			boolean isOTPSubmitDisplayed=otp.isOTPSubmitDisplayed();
        	
        	if (isOTPSubmitDisplayed) {
//				System.out.println("Submit button disabled for incomplete OTP");
				ThreadLocalClass.gettestlevel().log(Status.PASS, "Submit button disabled for incomplete OTP");
	        }
			else
			{
//				System.out.println("Submit button enabled for incomplete OTP");
				ThreadLocalClass.gettestlevel().log(Status.FAIL, "Submit button enabled for incomplete OTP");
			}
        	otp.EnterField2("5");
        	otp.EnterField3("6");
        	otp.EnterField4("8");
        	form2.Form2Submit();
        	
        	boolean isOTPErrorMessageDisplayed=otp.isOTPErrorMessageDisplayed();
        	
        	if (isOTPErrorMessageDisplayed) {
//				System.out.println("Error message disaplayed for invalid OTP");
				ThreadLocalClass.gettestlevel().log(Status.PASS, "Error message disaplayed for invalid OTP as" + otp.GetOTPErrorMessage());
	        }
			else
			{
//				System.out.println("Error message not shown OTP");
				ThreadLocalClass.gettestlevel().log(Status.FAIL, "Error message not shown OTP");
			}
        	
        	otp.EnterField1("1");
        	otp.EnterField2("1");
        	otp.EnterField3("1");
        	otp.EnterField4("1");
        	otp.SubmitOTP();
        	
        	boolean isForm2PageDisplayed = form2.isForm2PageDisplayed();
    		if (!isForm2PageDisplayed) {
//    			System.out.println("Flow not redirected to Form 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Form 2 screen");
    			Assert.assertFalse(isForm2PageDisplayed,"Flow not redirected to Form 2 screen");
            }
    		else
    		{	
//    			System.out.println("OTP validated successfully and Flow successfully redirected to Form 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.PASS, "OTP validated successfully and Flow successfully redirected to Form 2 screen");
    		}
			
//			softassert.assertAll();
			
		}
		
		@Test (priority = 3)
		public void Negative_Form2Page() throws InterruptedException
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on Form 2");
			LinkedHashMap<String, String> invalidDataForFirstName = new LinkedHashMap<>();
			invalidDataForFirstName.put("", "Error message disaplayed as");
			invalidDataForFirstName.put("Test526", "Error message disaplayed as");
			invalidDataForFirstName.put("Test%@*", "Error message disaplayed as");
			invalidDataForFirstName.put("6737364", "Error message disaplayed as");
			invalidDataForFirstName.put("&*()%@*", "Error message disaplayed as");
			invalidDataForFirstName.put("Test 653","Error message disaplayed as");
			invalidDataForFirstName.put("Test &*(","Error message disaplayed as");
			invalidDataForFirstName.put("PADPY7368I","Error message disaplayed as");
			testFieldValidation("First_Name", invalidDataForFirstName);
			
			Thread.sleep(3000);
			
			LinkedHashMap<String, String> invalidDataForLastName = new LinkedHashMap<>();
			invalidDataForLastName.put("Test526", "Error message disaplayed as");
			invalidDataForLastName.put("Test%@*", "Error message disaplayed as");
			invalidDataForLastName.put("6737364", "Error message disaplayed as");
			invalidDataForLastName.put("&*()%@*", "Error message disaplayed as");
			invalidDataForLastName.put("Test 653", "Error message disaplayed as");
			invalidDataForLastName.put("Test &*(", "Error message disaplayed as");
			invalidDataForLastName.put("PADPY7368I", "Error message disaplayed as");
				
			testFieldValidation("Last_Name", invalidDataForLastName);
			
			form2.ClearLastNameField();
			form2.EnterInvalidLastNameField(lastName);
			form2.Form2Submit();
			
        	boolean isDashboardPageDisplayed = dashboard.isDashboardPageDisplayed();
    		if (!isDashboardPageDisplayed) {
//    			System.out.println("Flow not redirected to Dashboard screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Dashboard screen");
    			
            }
    		else
    		{	
//    			System.out.println("OTP validated successfully and Flow successfully redirected to Dashboard screen");
    			ThreadLocalClass.gettestlevel().log(Status.PASS, "OTP validated successfully and Flow successfully redirected to Dashboard screen");
    		}
    		
    		
    		if (dashboard.isWhatsappPopupDisplayed()) {
    			dashboard.ClickWhatsappSkip();
    			dashboard.ClickDashboardApplyNow();
    		}

    		else {
    			ThreadLocalClass.gettestlevel().log(Status.INFO,"Whatsapp popup not disaplayed");
    			dashboard.ClickDashboardApplyNow();

    		}
    		
    		// Below if else array handle permission1 popup
    		if (dashboard.isPermission1PopupDisplayed()) {
    			dashboard.ClickPermissionWhileUsingApp1();
    			dashboard.ClickPermissionWhileUsingApp2();
    		}
    
    		else {
    			System.out.println("Dashboard done");
    		}
    		
    		
    		boolean isAmountScreenDisplayed = appAmount.isAmountPageDisplayed();
    		if (!isAmountScreenDisplayed) {
//    			System.out.println("Flow not redirected to Amount screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Amount screen");
            }
    		else
    		{
//    			System.out.println("Flow successfully redirected to Loan Amount screen");
    			ThreadLocalClass.gettestlevel().log(Status.PASS, "Flow successfully redirected to Amount screen");
    		}
			
//			softassert.assertAll();
			
		}
			
//		@Test(priority = 4)
		public void Negative_LoanAmount() throws InterruptedException
		{	
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on Loan Amount");
			Thread.sleep(5000);
			LinkedHashMap<String, String> invalidDataForLoanAmount = new LinkedHashMap<>();
			invalidDataForLoanAmount.put("49999", "Submit button enabled for invalid loan amount -");
			invalidDataForLoanAmount.put("10000001", "Submit button enabled for invalid loan amount -");
			invalidDataForLoanAmount.put("0", "Submit button enabled for invalid loan amount -");
			
			testFieldValidation("Loan_Amount", invalidDataForLoanAmount);
			
			appAmount.EnterAmount(amount);
			appAmount.ClickCheckEligibilityBtn();
			
			boolean isAppFormStep1PageDisplayed = formStep1.isAppFormStep1PageDisplayed();
    		if (!isAppFormStep1PageDisplayed) {
//    			System.out.println("Flow not redirected to Form step 1 screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Form step 1 screen");
            }
    		else
    		{
//    			System.out.println("Flow successfully redirected to Form step 1 screen");
    			ThreadLocalClass.gettestlevel().log(Status.PASS, "Flow successfully redirected to Form step 1 screen");
    		}
			
//			softassert.assertAll();
			
		}
		
//		@Test(priority = 5)
		public void Negative_AppFormStep1() throws InterruptedException
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on App Form Step 1");
			String ExistingMTOvalue=formStep1.MTOFieldValue();
			int ExistingMTOCount= formStep1.MTOFieldValueCount(ExistingMTOvalue);
			
			if(ExistingMTOCount>0)
			{
//				System.out.println("Earlier provided MTO value already replicate in MTO Field -"+ExistingMTOvalue);
				ThreadLocalClass.gettestlevel().log(Status.INFO, "Earlier provided MTO value already replicate in MTO Field -"+ExistingMTOvalue);
			}
			else
			{
//				System.out.println("Earlier provided MTO value does not replicate in MTO Field");
				ThreadLocalClass.gettestlevel().log(Status.FAIL, "Earlier provided MTO value does not replicate in MTO Field");
			}
			
			formStep1.ClearMonthlySales();			
			
			formStep1.ClearMonthlySales();
			LinkedHashMap<String, String> invalidDataForMTODataField = new LinkedHashMap<>();
			invalidDataForMTODataField.put("0", "Next button enabled for invalid value");						
			testFieldValidation("Monthly_total_sales", invalidDataForMTODataField);
			
			formStep1.ClearMonthlySales();
			formStep1.EditMonthlySales("67896789123");			
			String MTOFieldValue = formStep1.MTOFieldValue();	        
	        int MTOValueCount = formStep1.MTOFieldValueCount(MTOFieldValue);
	        if (MTOValueCount == 13) {
//	            System.out.println("The MTO field accepts exactly 13 digits.");
	            ThreadLocalClass.gettestlevel().log(Status.PASS, "The MTO field accepts exactly 13 digits.");
	        } else {
//	            System.out.println("The MTO accepts more than 13 digits. It contains " + MTOValueCount + " digits.");
	            ThreadLocalClass.gettestlevel().log(Status.FAIL, "The MTO accepts more than 13 digits. It contains " + MTOValueCount + " digits.");
	        }
			
	        formStep1.ClearMonthlySales();
			formStep1.EditMonthlySales("500000");
			formStep1.ClickFormStep1NextBtn();
			
			boolean isAppFormStep2PageDisplayed = formStep2.isAppFormStep2PageDisplayed();
    		if (!isAppFormStep2PageDisplayed) {
//    			System.out.println("Flow not redirected to Form step 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Form step 2 screen");
            }
    		else
    		{
//    			System.out.println("Flow successfully redirected to Form step 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.PASS, "Flow successfully redirected to Form step 2 screen");
    		}
			
//			softassert.assertAll();
			
		}
		
//		@Test(priority = 6)
		public void Negative_AppFormStep2() throws InterruptedException
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Negative<br><b>Description</b> - Test case validates negative scenarios for data fields on App Form Step 2");
						
			formStep2.ClearBusinessPincode();
			LinkedHashMap<String, String> invalidDataForBussPincodeField = new LinkedHashMap<>();
//			invalidDataForBussPincodeField.put("4", "Next button enabled for invalid business pincode");
//			invalidDataForBussPincodeField.put("41", "Next button enabled for invalid business pincode");
//			invalidDataForBussPincodeField.put("411", "Next button enabled for invalid business pincode");
//			invalidDataForBussPincodeField.put("4110", "Next button enabled for invalid business pincode");
			invalidDataForBussPincodeField.put("41101", "Next button enabled for invalid business pincode");
			invalidDataForBussPincodeField.put("000000", "Next button enabled for invalid business pincode");
			testFieldValidation("Business_Pincode", invalidDataForBussPincodeField);
			
			formStep2.EnterBusinessPincode(scriptBusiPincode);
			
			LinkedHashMap<String, String> invalidDataForResiPincodeField = new LinkedHashMap<>();
//			invalidDataForResiPincodeField.put("4", "Next button enabled for invalid business pincode");
//			invalidDataForResiPincodeField.put("41", "Next button enabled for invalid business pincode");
//			invalidDataForResiPincodeField.put("411", "Next button enabled for invalid business pincode");
//			invalidDataForResiPincodeField.put("4110", "Next button enabled for invalid business pincode");
			invalidDataForResiPincodeField.put("41101", "Next button enabled for invalid business pincode");
			invalidDataForResiPincodeField.put("000000", "Next button enabled for invalid business pincode");
			testFieldValidation("Residential_Pincode", invalidDataForResiPincodeField);
			
			formStep2.EnterResidentialPincode(scriptResPincode);
			
			LinkedHashMap<String, String> invalidDataForPersonalPANField = new LinkedHashMap<>();
			invalidDataForPersonalPANField.put("ABCPE83909", "Error message not displayed for invalid personal pan");
			invalidDataForPersonalPANField.put("ABCPE839NU", "Error message not displayed for invalid personal pan");
			invalidDataForPersonalPANField.put("ABCPEL390U", "Error message not displayed for invalid personal pan");
			invalidDataForPersonalPANField.put("3BCP88390U", "Error message not displayed for invalid personal pan");
			invalidDataForPersonalPANField.put("ABCP%8390U", "Error message not displayed for invalid personal pan");
			invalidDataForPersonalPANField.put("ABCPE839*U", "Error message not displayed for invalid personal pan");
			
			testFieldValidation("Personal_PAN", invalidDataForPersonalPANField);
			
			formStep2.EnterPersonalPan(pan);
			formStep2.ClickFormStep2NextBtn();
			
			boolean isAppFormStep3PageDisplayed = formStep3.isAppFormStep3PageDisplayed();

    		if (!isAppFormStep3PageDisplayed) {
//    			System.out.println("Flow not redirected to Form step 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow not redirected to Form step 3 screen");
            }
    		else
    		{
//    			System.out.println("Flow successfully redirected to Form step 2 screen");
    			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Flow successfully redirected to Form step 3 screen");
    		}
		}
		
		
		
		private String getCurrentMethodName() {
	        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	        return stackTrace[2].getMethodName();
	    }
}

