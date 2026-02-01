package PositiveFlow;

import java.time.Duration;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import GenericUtilities.*;
import PageObject.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author : Aditya K.
 * @Description : This class runs positive end to end flow for mobile app pre-sanction journey.
 */

@Listeners(ListenerClass.class)
public class PositiveFlow extends BaseClass {
	
	String firstName=faker1.name().firstName();
	String lastName=faker1.name().lastName();
	String name = firstName+" "+lastName; // Enter name
	String mobile_no = "6" + randome9Num(); // Enter Mobile number
	String amount = "3" + randome5Num(); // Enter amount
	String monthly_sales = "2" + randome5Num(); // Enter monthly sales
	String email_id = ((firstName.toLowerCase()+"."+lastName.toLowerCase() + randome4Num() + "@mail.com").replace(" ", ""));
	String pan = randome3string() + "PQ"+randome4Num() + "A"; // Enter pan number
	String scriptbusiAddBuild=faker1.address().buildingNumber();// Enter Buss Address building (address_line_1)
	String scriptResAddBuild=faker1.address().buildingNumber(); // Enter Res Address building (address_line_1)
	String scriptbusiAddArea=faker1.address().streetAddress(); // Enter Buss Address area (address_line_2)
	String scriptResAddArea=faker1.address().streetAddress(); // Enter Res Address area (address_line_2)

	
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


	String scriptBusiPincode = "400016";
	String scriptResPincode = "411012";
	String scriptBusiName = "BALAMURUGAN STORE";
	String scriptBusiProduct = "Dairy";
	String scriptMonthlySales = "999999";

	
	@Test(priority = 0)
	public void LandingPage() {
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on app landing page<br><b>Page Data Fields</b> - Mobile No");

		lp.EnterMobilefield(mobile_no);
		lp.ClickGetOTPCTA();
		
		boolean isOTPPageDisplayed = otp.isOTPPageDisplayed();
		if (!isOTPPageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to OTP validation screen");
			Assert.fail("OTP redirection failed");
        }
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to form2 screen");
		}
	}
	
	@Test(priority = 2)
	public void OTPPage() {
//		reportUtil.intialLogForTest(TCID);		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on app otp page<br><b>Page Data Fields</b> - OTP Validation");
		
		otp.EnterField1("1");
		otp.EnterField2("1");
		otp.EnterField3("1");
		otp.EnterField4("1");
		otp.SubmitOTP();
		
	
		boolean isForm2PageDisplayed = form2.isForm2PageDisplayed();
		if (!isForm2PageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Form 2 (Name, MTO and GST) screen");
			Assert.fail("Form 2 redirection failed");
        }
		else
		{	
			ThreadLocalClass.gettestlevel().log(Status.PASS,"OTP validated successfully and Flow successfully redirected to Form 2 (Name, MTO and GST) screen");
		}
		
	}

	@Test(dependsOnMethods = "OTPPage")
	public void Form2() throws InterruptedException {
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on app Form 2 screen and lead creation <br><b>Page Data Fields</b> - Name, MTO and GST");		
		
		form2.EnterFirstNameField(firstName);		
		form2.EnterLastNameField(lastName);
		
		System.out.println(name);
		form2.SelectMonthlySales();
		form2.SelectMonthlySalesFourtoTen();
//		form2.SelectMonthlySalesTwotoFour();
//		form2.SelectMonthlySalesLessThanTwo();
		form2.SelectGSTYes();
//		form2.SelectGSTNo();
		form2.Form2Submit();
		
		boolean isDashboardPageDisplayed = dashboard.isDashboardPageDisplayed();
		if (!isDashboardPageDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to dashboard screen");
			Assert.fail("Dashboard redirection failed");
        }
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to dashboard screen");
		}


//		Thread.sleep(5000);
		
		query_applicant_detail = Databaseutil.getQuery(TCID + "_1", mobile_no);
		application_code = databaseutil.returnValueByColumnname(query_applicant_detail, "loan_code");
		String lead_code = databaseutil.returnValueByColumnname(query_applicant_detail, "customer_code");

		String mobile = databaseutil.returnValueByColumnname(query_applicant_detail, "mobile_no");
		boolean isMobileMatching = mobile.equals(mobile_no);
		
////	Name Field deprecated under MA-28
//		String name = databaseutil.returnValueByColumnname(query_applicant_detail, "name");
//		boolean isNameMatching = name.equals(name);
		
		String full_name = databaseutil.returnValueByColumnname(query_applicant_detail, "name");
		boolean isFullNameMatching = full_name.equals(name);

		query_business_detail = Databaseutil.getQuery(TCID + "_2", application_code);
		String has_gst_registration = databaseutil.returnValueByColumnname(query_business_detail,
				"has_gst_registration");
		boolean isGSTRegiMatching = has_gst_registration.equals("1");

		query_finance_detail = Databaseutil.getQuery(TCID + "_3", application_code);
		String monthly_sales = databaseutil.returnValueByColumnname(query_finance_detail, "monthly_total_sales");
		boolean ismonthly_salesMatching1 = monthly_sales.equals(scriptMonthlySales);
		
//		query_otp_user = Databaseutil.getQuery(TCID + "_4", mobile_no);
//		String otp = databaseutil.returnValueByColumnname(query_otp_user, "otp");
		
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>New lead created successfully</b> - <br> application code - "
				+ application_code + "<br>" + "lead_code - " + lead_code);
		
		if (isMobileMatching  && isFullNameMatching && ismonthly_salesMatching1) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"The values from the Database matches the data provided from UI for fields -<br><b>Mobile No</b> - "+mobile+"<br><b>Name -</b> "+full_name+"<br><b>Monthly sales -</b> "+monthly_sales);
		} else {

			StringBuilder failureReason = new StringBuilder(
					"The values from the Database does <b>NOT</b> match with the data provided from UI for fields: ");

			boolean isFirstCondition = true;

			if (!isMobileMatching) {

				failureReason.append("<b>Mobile No -</b> "+ mobile);

				isFirstCondition = false;
			}
			if (!isFullNameMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<b>Full Name -</b>" +full_name);

				isFirstCondition = false;
			}
			if (!ismonthly_salesMatching1) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<b>Monthly Sales -</b>"+monthly_sales);
			}
			ThreadLocalClass.gettestlevel().log(Status.WARNING, failureReason.toString());
		}
		
		if (isGSTRegiMatching) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"Flag updated correctly for <b>GST registration -</b> "+ has_gst_registration);
		} else {

			ThreadLocalClass.gettestlevel().log(Status.WARNING,
					"Flag not updated correctly in database for <b>GST registration</b> -"+ has_gst_registration);
		}			
	}	

	@Test(dependsOnMethods = "Form2")
	public void Dashboard() throws InterruptedException {
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on app Dashboard screen");

		// Below if else array handle whatsapp popup
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
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Permissions popup not disaplayed");
		}
		
		boolean isAmountScreenDisplayed = appAmount.isAmountPageDisplayed();
		if (!isAmountScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Amount screen");
			Assert.fail("Amount screen redirection failed");
        }
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to Amount screen");
		}		
	}

	@Test(dependsOnMethods = "Dashboard")
	public void AmountScreen() throws InterruptedException {

		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on app Amount screen<br><b>Page Data Fields</b> - Amount");

		appAmount.EnterAmount(amount);
		appAmount.ClickCheckEligibilityBtn();
		
//		Thread.sleep(5000);
		boolean isAppFormStep1ScreenDisplayed = formStep1.isAppFormStep1PageDisplayed();
		if (!isAppFormStep1ScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to FormStep_1 screen");
			Assert.fail("FormStep_1 screen redirection failed");
        }
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to FormStep_1 screen");
		}

		query_application = Databaseutil.getQuery(TCID + "_1", application_code);
		String lead_amount = databaseutil.returnValueByColumnname(query_application, "amount");
		String lead_platform = databaseutil.returnValueByColumnname(query_application, "platform");
		String lead_CraetedBy = databaseutil.returnValueByColumnname(query_application, "created_by");
		String lead_PartnerCode = databaseutil.returnValueByColumnname(query_application, "partner_code");
		String lead_UnifiedClient = databaseutil.returnValueByColumnname(query_application, "unified_client");
		String lead_AppStatus = databaseutil.returnValueByColumnname(query_application, "application_status");

		if (lead_amount.equals(amount + ".0")) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"<b>Amount</b> value stored correctly in database - <b>" + lead_amount+"</b>");
		} else {
			ThreadLocalClass.gettestlevel().log(Status.WARNING,
					"<b>Amount</b> value not stored correctly in database");
		}
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>Lead Platform</b> value stored in database is - <b>" +lead_platform+"</b>");
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>Lead Created By</b> value stored in database is - <b>" + lead_CraetedBy+"</b>");
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>Partner code</b> tagged for lead in database is - <b>" + lead_PartnerCode+"</b>");
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>Unifield Client</b> tagged for lead in database is - <b>" + lead_UnifiedClient+"</b>");
		ThreadLocalClass.gettestlevel().log(Status.INFO, "<b>Application status</b> shown in database is - <b>" + lead_AppStatus+"</b>");
	}

	@Test(dependsOnMethods = "AmountScreen")
	public void FormStep_1() throws InterruptedException {		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on Application Form:step-1 <br><b>Page Data Fields</b> - Gender, Date of Birth, Monthly Sales(Repeated), Business Vintage<br>QA Current Account, QA GST Registration (Repeated), QA Occupation");
		
		String gender = databaseutil.returnValueByColumnname(query_applicant_detail, "gender");
		if (gender==null || gender.isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Gender details not fetched by Experian");
			formStep1.SelectGenderMale();
			
		}
		
		else {
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Gender details fetched by experian service as  - "+ gender);
			
		}
		
//		formStep1.SelectGenderFemale();
//		formStep1.SelectGenderOther();
		
//		String existingExperianDOB=formStep2.GetPanNoValue();
		
		String ExperianDOB = databaseutil.returnValueByColumnname(query_applicant_detail, "dob");
		if (ExperianDOB==null)
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"DOB details not fetched by Experian");
			formStep1.SelectDOBField();
			int startX = (int) (formStep1.dobYearPicker.getLocation().getX()
					+ formStep1.dobYearPicker.getSize().getWidth() / 2);
			int startY = (int) (formStep1.dobYearPicker.getLocation().getY()
					+ formStep1.dobYearPicker.getSize().getHeight() * 0.60);
			String desiredValue = "1996"; // Change this to your desired value
			int maxInterations = 06;
			int iterationCount = 0;
			while (iterationCount < maxInterations) {
				int endX = startX; // Adjust the distance to scroll as needed
				int endY = startY + 300; // Adjust the distance to scroll as needed
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200))).addAction(finger1
								.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Collections.singletonList(sequence));
				iterationCount++;
				if (driver.getPageSource().contains(desiredValue)) {
					break;
				}
			}
			Thread.sleep(2000);

			formStep1.SelectDOB();
		
		}
		
		else {
			ThreadLocalClass.gettestlevel().log(Status.INFO,"DOB details fetched by experian");
		}
		
		
//		formStep1.SelectDOBField();
//		int startX = (int) (formStep1.dobYearPicker.getLocation().getX()
//				+ formStep1.dobYearPicker.getSize().getWidth() / 2);
//		int startY = (int) (formStep1.dobYearPicker.getLocation().getY()
//				+ formStep1.dobYearPicker.getSize().getHeight() * 0.60);
//		String desiredValue = "1996"; // Change this to your desired value
//		int maxInterations = 06;
//		int iterationCount = 0;
//		while (iterationCount < maxInterations) {
//			int endX = startX; // Adjust the distance to scroll as needed
//			int endY = startY + 300; // Adjust the distance to scroll as needed
//			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
//			Sequence sequence = new Sequence(finger1, 1)
//					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
//					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
//					.addAction(new Pause(finger1, Duration.ofMillis(200))).addAction(finger1
//							.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
//					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//			driver.perform(Collections.singletonList(sequence));
//			iterationCount++;
//			if (driver.getPageSource().contains(desiredValue)) {
//				break;
//			}
//		}
//		Thread.sleep(2000);
//
//		formStep1.SelectDOB();
		
		String existingMTOfromForm2=formStep1.GetMonthlySalesValue();
		
		if (existingMTOfromForm2==null || existingMTOfromForm2.isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Form Step-1 MTO field is not pre-filled from Form-2 dropdown selection");
			formStep1.EditMonthlySales("1200000");
			
		}
		
		else {
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Form Step-1 MTO field is pre-filled from Form-2 dropdown selection - "+existingMTOfromForm2);
			
		}
		
		formStep1.SelectBusinessAgeMoreThan5Years();
		
		formStep1.SelectCurrentAccountYes();
//		formStep1.SelectCurrentAccountNo();

		formStep1.SelectGSTRegistrationYes();	
//		formStep1.SelectGSTRegistrationNo();

		formStep1.SelectOccupationBusinessMan();
//		formStep1.SelectOccupationSalaried();

		formStep1.ClickFormStep1NextBtn();

//		Thread.sleep(5000);
		
		boolean isAppFormStep2ScreenDisplayed = formStep2.isAppFormStep2PageDisplayed();
		if (!isAppFormStep2ScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to FormStep_2 screen");
			Assert.fail("FormStep_2 screen redirection failed");
        }
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to FormStep_2 screen");
		}

		String DBDOB = databaseutil.returnValueByColumnname(query_applicant_detail, "dob");
		ThreadLocalClass.gettestlevel().log(Status.INFO, "DOB stored in database - " + DBDOB);

		String DBgender = databaseutil.returnValueByColumnname(query_applicant_detail, "gender");
		boolean isGenderMatching = DBgender.equals("Male")|| DBgender.equals("Female")|| DBgender.equals("Other");

		String business_vintage = databaseutil.returnValueByColumnname(query_business_detail, "business_vintage");
		boolean isbusinessVintageMatching = business_vintage.equals("More than 5 years");

//			String businessVintage=databaseutil.returnValueByColumnname(query_business_detail, "business_vintage");
		String CurrentAccountAvailable = databaseutil.returnValueByColumnname(query_applicant_detail,
				"is_current_account_available");
		boolean isCurrentAccountMatching = CurrentAccountAvailable.equals("1");

		String occupation = databaseutil.returnValueByColumnname(query_applicant_detail, "is_salaried");
		boolean isOccupationMatching = occupation.equals("0");

		if (isGenderMatching && isbusinessVintageMatching && isOccupationMatching && isCurrentAccountMatching) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"The values from the Database matches the data provided from UI for fields -<br><b>Gender - </b>"+gender+"<br><b>Business Vintage - </b>"+business_vintage+"<br><b>QA Current Account - </b>"+CurrentAccountAvailable+"<br><b>QA Occupation is_salaried - </b>"+occupation);
		} else {

			StringBuilder failureReason = new StringBuilder(
					"The values from the Database does <b>NOT</b> match with the data provided from UI for fields:<br>");

			boolean isFirstCondition = true;

			if (!isGenderMatching) {

				failureReason.append("<b>Gender -</b>"+ gender);

				isFirstCondition = false;
			}
			if (!isbusinessVintageMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Business Vintage -</b>"+business_vintage);

				isFirstCondition = false;
			}
			if (!isCurrentAccountMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>QA Current Account -</b>"+CurrentAccountAvailable);

				isFirstCondition = false;
			}
			if (!isOccupationMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>QA Occupation -</b>"+occupation);
			}
			ThreadLocalClass.gettestlevel().log(Status.FAIL, failureReason.toString());
		}
	}

	@Test(dependsOnMethods = "FormStep_1")
	public void FormStep_2() throws InterruptedException {
//		reportUtil.intialLogForTest(TCID);
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on Application Form:step-2 <br><b>Page Data Fields</b> - Business Pincode, Residential Pincode, QA Business Oenership, QA Residential Ownership<br>Personal PAN No, Legal Status");

		formStep2.EnterBusinessPincode(scriptBusiPincode);
		
		String ExperianResPincode=formStep2.GetResidentialPincodeValue();
		if(ExperianResPincode==null || ExperianResPincode.isEmpty())
		{	
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Residential pincode details not fetched by Experian");
			formStep2.EnterResidentialPincode(scriptResPincode);
		}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Residential pincode details fetched by Experian");
		}

		formStep2.SelectResidentialOwnershipOwned();
//		formStep2.SelectResidentialOwnershipRented();

		formStep2.SelectBusinessOwnershipOwned();
//		formStep2.SelectBusinessOwnershipRented();
		
		String existingExperianPan=formStep2.GetPanNoValue();
		
		if (existingExperianPan==null || existingExperianPan.isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.INFO,"Pan details not fetched by Experian");
			formStep2.EnterPersonalPan(pan);
		}
		
		else {
			ThreadLocalClass.gettestlevel().log(Status.INFO,"PAN No details fetched by experian");
		}
		
		
		formStep2.SelectLegalStatusProprietorship();

		formStep2.ClickFormStep2NextBtn();

		Thread.sleep(7000);
		
		boolean isAppFormStep3ScreenDisplayed = formStep3.isAppFormStep3PageDisplayed();
		if (!isAppFormStep3ScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to FormStep_3 screen");
			Assert.fail("FormStep_3 screen redirection failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to FormStep_3 screen");
		}
		
		String BusiPincode = databaseutil.returnValueByColumnname(query_business_detail, "address_pin_code");
		boolean isBusiPincodeMatching = BusiPincode.equals(scriptBusiPincode);

		String DBResPincode = databaseutil.returnValueByColumnname(query_applicant_detail, "address_pincode");
		boolean isResPincodeMatching = DBResPincode.equals(scriptResPincode) || DBResPincode.equals(ExperianResPincode) ;

		String ResOwnership = databaseutil.returnValueByColumnname(query_applicant_detail,
				"address_ownership_status");
		boolean isResOwnershipMatching = ResOwnership.equals("Owned");

		String BusiOwnership = databaseutil.returnValueByColumnname(query_business_detail,"address_ownership_status");
		boolean isBusiOwnershipMatching = BusiOwnership.equals("Owned");

		String PersonalPAN = databaseutil.returnValueByColumnname(query_applicant_detail,"pan_no");;
		boolean isPersonalPANMatching = PersonalPAN.equals(pan.toUpperCase()) || PersonalPAN.equals(existingExperianPan.toUpperCase());

		String BusiLegalStatus = databaseutil.returnValueByColumnname(query_business_detail,"legal_status");
		boolean isBusiLegalStatusMatching = BusiLegalStatus.equals("Proprietorship");

		if (isBusiPincodeMatching && isResPincodeMatching && isResOwnershipMatching && isBusiOwnershipMatching
				&& isPersonalPANMatching && isBusiLegalStatusMatching) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"The values from the Database matches the data provided from UI for fields : <br><b>Business Pincode - </b>"+BusiPincode+"<br><b>Residential Pincode - </b>"+DBResPincode+"<br><b>Business Ownership - </b>"+BusiOwnership+"<br><b>Residential Ownership - </b>"+ResOwnership+"<br><b>Personal PAN - </b>"+PersonalPAN+"<br><b>Business Legal Status - </b>"+BusiLegalStatus);
		} else {
			StringBuilder failureReason = new StringBuilder
					("The values from the Database NOT matching with the data provided from UI for fields: ");

			boolean isFirstCondition = true;

			if (!isBusiPincodeMatching) {

				failureReason.append("<br><b>Business pincode - </b>"+BusiPincode);

				isFirstCondition = false;
			}
			if (!isResPincodeMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<br><b>Residential Pincode - </b>"+DBResPincode);

				isFirstCondition = false;
			}
			if (!isResOwnershipMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<br><b>Residential Ownership Status - </b>"+ResOwnership);

				isFirstCondition = false;
			}
			if (!isBusiOwnershipMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<br><b>Business Ownership Status - </b>"+BusiOwnership);

				isFirstCondition = false;
			}
			if (!isPersonalPANMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<br><b>Personal PAN No. - </b>"+PersonalPAN);

				isFirstCondition = false;
			}

			if (!isBusiLegalStatusMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<br><b>Business Legal Status - </b>"+BusiLegalStatus);
			}

			ThreadLocalClass.gettestlevel().log(Status.WARNING, failureReason.toString());		
		}
		
		String lead_AppNumber = databaseutil.returnValueByColumnname(query_application, "number");
		String lead_UpdatedLeadStatus = databaseutil.returnValueByColumnname(query_application, "application_status");
		
		if(lead_AppNumber==null || lead_AppNumber.trim().isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.WARNING, "Application Number is not generated for the lead");
		}
		
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS, "Application Number is get generated for the lead -<b>"+lead_AppNumber+"</b>");
		}
		
		if(lead_UpdatedLeadStatus==null || lead_UpdatedLeadStatus.trim().isEmpty() || lead_UpdatedLeadStatus.contains("IP_FRESH"))
		{
			ThreadLocalClass.gettestlevel().log(Status.WARNING, "Application status is not updated for the lead");
		}
		
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS, "Application status get updated to - <b>"+lead_UpdatedLeadStatus+"</b>");
		}	
		
	}

	@Test(dependsOnMethods = "FormStep_2")
	public void FormStep_3() throws InterruptedException {
//		reportUtil.intialLogForTest(TCID);
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on Application Form:step-3 <br><b>Page Data Fields</b> - Nature of Business, Business Product, Business Name, Business Address building<br>Business Address Area,Residential Address Building, Residential Address Area");

		AppFormStep_3 formStep3 = new AppFormStep_3(driver);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		AppOfferScreen offerscreen = new AppOfferScreen(driver);

		wait.until(ExpectedConditions.visibilityOf(formStep3.natureOfBusinessRetailer));
		Thread.sleep(10);
//		formStep3.SelectNatureBusinessRetailer();
//		formStep3.SelectNatureBusinessManufacturer();
//		formStep3.SelectNatureBusinessServiceProvider();
//		formStep3.SelectNatureBusinessWholesaler();
		formStep3.SelectNatureBusinessWholesalerandRetailer();

		formStep3.SelectProductListField();
		formStep3.SelectProductListSearchField(scriptBusiProduct);
		formStep3.SelectMarkCheckboxForSearchedProduct();
		formStep3.SubmitSelectedProduct();
		formStep3.EnterBusinessName(scriptBusiName);
		formStep3.EnterBusinessAddressLineOne(scriptbusiAddBuild);
		formStep3.EnterBusinessAddressLineTwo(scriptbusiAddArea);

		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = size.height / 2;
		int maxInterations = 1;
		int iterationCount = 0;
		while (iterationCount < maxInterations) {
			int endY = size.height / 4;
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200))).addAction(finger1
							.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));
			iterationCount++;
		}
		formStep3.EnterResidentialAddressLineOne(scriptResAddBuild);
		formStep3.EnterResidentialAddressLineTwo(scriptResAddArea);
		formStep3.ClickFormStep3NextBtn();
		
		
		boolean isAppOfferScreenDisplayed = offerscreen.isAppOfferPageDisplayed();
		if (!isAppOfferScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Offer screen");
			Assert.fail("Offer screen redirection failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to Offer screen");
		}

		String BusiNature = databaseutil.returnValueByColumnname(query_business_detail, "nature_of_business");
		boolean isBusiNatureMatching = BusiNature.equals("Wholesaler and Retailer");		
		String BusiProductJson = databaseutil.returnValueByColumnname(query_business_detail, "product_service");
		
            JSONArray jsonArray = new JSONArray(BusiProductJson);
            JSONObject firstObject = jsonArray.getJSONObject(0);
            JSONArray productsArray = firstObject.getJSONArray("products");
            JSONObject productObject = productsArray.getJSONObject(0);
            String BusinessProduct = productObject.getString("product_service");
		
		boolean isBusiProductMatching = BusinessProduct.contains(scriptBusiProduct);
		String BusiName = databaseutil.returnValueByColumnname(query_business_detail, "business_name");
		boolean isBusiNameMatching = BusiName.equals(scriptBusiName);

		String BusiAddressBuilding = databaseutil.returnValueByColumnname(query_business_detail,"address_building");
		boolean isBusiAddressBuildingMatching = BusiAddressBuilding.equals(scriptbusiAddBuild);
		String BusiAddressArea = databaseutil.returnValueByColumnname(query_business_detail,"address_area");
		boolean isBusiAddressAreaMatching = BusiAddressArea.equals(scriptbusiAddArea);
		
		String ResAddressBuilding = databaseutil.returnValueByColumnname(query_applicant_detail,"address_building");
		boolean isResAddressBuildingMatching = ResAddressBuilding.equals(scriptResAddBuild);
		
		String ResAddressArea = databaseutil.returnValueByColumnname(query_applicant_detail,"address_area");
		boolean isResAddressAreaMatching = ResAddressArea.equals(scriptResAddArea);
		
		if (isBusiNatureMatching && isBusiProductMatching && isBusiNameMatching && isBusiAddressBuildingMatching
				&& isBusiAddressAreaMatching && isResAddressBuildingMatching && isResAddressAreaMatching) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"The values from the Database matches the data provided from UI for fields : <br><b>Nature of Business - </b>"+BusiNature+"<br><b>Business Product - </b>"+BusinessProduct+"<br><b>Business Name - </b>"+BusiName+"<br><b>Business Building - </b>"+BusiAddressBuilding+"<br><b>Business Area - </b>"+BusiAddressArea+"<br><b>Resident Building - </b>"+ResAddressBuilding+"<br><b>Resident Area - </b>"+ResAddressArea);
		} else {

			StringBuilder failureReason = new StringBuilder(
					"The values from the Database NOT matching with the data provided from UI for fields: ");

			boolean isFirstCondition = true;

			if (!isBusiNatureMatching) {

				failureReason.append("<b>Nature of Business - </b>"+BusiNature);

				isFirstCondition = false;
			}
			if (!isBusiProductMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Business Product - </b>"+BusinessProduct);

				isFirstCondition = false;
			}
			if (!isBusiNameMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Business Name - </b>"+BusiName);

				isFirstCondition = false;
			}
			if (!isBusiAddressBuildingMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Business Building - </b>"+BusiAddressBuilding);

				isFirstCondition = false;
			}
			if (!isBusiAddressAreaMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Business Area - </b>"+BusiAddressArea);

				isFirstCondition = false;
			}
			if (!isResAddressBuildingMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Residential Building - </b>"+BusiAddressBuilding);

				isFirstCondition = false;
			}

			if (!isResAddressAreaMatching) {

				if (!isFirstCondition) {

					failureReason.append("<br>");
				}
				failureReason.append("<b>Residential Area - </b>"+ResAddressArea);
			}
			ThreadLocalClass.gettestlevel().log(Status.WARNING, failureReason.toString());
		}
	}

	@Test(dependsOnMethods = "FormStep_3")
	public void OfferScreen() throws InterruptedException {
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user actions on Offer screen");

		if (offerscreen.ifOfferDisplayed()) {
			Thread.sleep(4000);
			offerscreen.SelectAcceptOfferBtn();
		}

		else if (appdashboard_2.ifDashboardDisplayed()) {
			System.out.println("Offer screen not displayed");
			appdashboard_2.ClickApplicationTab();
		}
		
		boolean isAppDashScreenDisplayed = appApplicationDashScreen.isAppDashScreenDisplayed();
		if (!isAppDashScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Application form dashboard screen");
			Assert.fail("Application form dashboard screen redirection failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to Application form dashboard screen");
		}
	}

//	@Test(dependsOnMethods = "OfferScreen")
//	public void BankStatementUpload() throws InterruptedException {
//		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user's Bank Statement upload functionality");
//		AppApplicationDashScreen appApplicationDashScreen = new AppApplicationDashScreen(driver);
//		AppBankStatement appBankStatement = new AppBankStatement(driver);
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		appApplicationDashScreen.ClickBankStatementUpload();
//		appBankStatement.ClickBankListDropdown();
//		appBankStatement.EnterBankNameInSearchBox("HDFC");
//		appBankStatement.SelectSearchedBank();
//		appBankStatement.ClickBankStatementDoneBtn();
//		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		appBankStatement.ClickSelectedBankNextArrow();
//		appBankStatement.ClickUploadManualBankStatement();
//		appBankStatement.SelectFileToUploadDoc();
//		appBankStatement.SelectSearchOptioninDeviceStorage();
//		appBankStatement.EnterSearchKeywordForDocument("Bank Statement");		
//		Thread.sleep(20000);
//		driver.navigate().back();
//		wait.until(ExpectedConditions.visibilityOf(appBankStatement.bankStatementFromLocalStorage));
//		appBankStatement.SelectBankStatementFromLocalStorage();
//		wait.until(ExpectedConditions.visibilityOf(appBankStatement.oneFileSelectedMessage));
//		appBankStatement.ClickBankStatementSubmit();
//		wait.until(ExpectedConditions.elementToBeClickable(appBankStatement.addMoreBanksOption));
//		driver.navigate().back();
//		
//		boolean isBankStateParsed = appApplicationDashScreen.isBankStateUploadCompleteTickDisplayed();
//		
//			if (isBankStateParsed)
//			{
//				System.out.println("Bank statement uploaded successfully");
//			}
//			else 
//			{
//				System.out.println("Bank statement not uploaded or parsed successfully");
//			}
//		
//		
//		ThreadLocalClass.gettestlevel().log(Status.PASS, "Bank statement uploaded successfully");
//		
//		boolean isAppDashScreenDisplayed = appApplicationDashScreen.isAppDashScreenDisplayed();
//		if (!isAppDashScreenDisplayed) {
//			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Document upload screen");
//			Assert.fail("Document upload screen redirection failed");
//			}
//		else
//		{
//			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to Document upload screen");
//		}
//		
//		
//		query_document_1 = Databaseutil.getQuery(TCID + "_1", application_code);
//		DocumentCategory = databaseutil.returnValueByColumnname(query_document_1, "document_category");
//		System.out.println("Doc category -"+ DocumentCategory);
//		boolean isbankStateCategoryMatching = DocumentCategory.contains("bank_statement");
//		
//		query_document_2 = Databaseutil.getQuery(TCID + "_2", application_code);
//		query_document_3 = Databaseutil.getQuery(TCID + "_3", application_code);
//		query_document_4 = Databaseutil.getQuery(TCID + "_4", application_code);
//		query_document_5 = Databaseutil.getQuery(TCID + "_5", application_code);
//		String isBSVerified = databaseutil.returnValueByColumnname(query_document_2, "is_verified");
//		boolean isBSVerifiedMatching = isBSVerified.equals("1");
//		
//		if (isbankStateCategoryMatching && isBSVerifiedMatching) {
//			ThreadLocalClass.gettestlevel().log(Status.PASS,
//					"<b>Bank statmenent entry created in database and successfully verified</b>");
//		} else {
//
//			StringBuilder failureReason = new StringBuilder(
//					"Bansk statement Upload : ");
//
//			boolean isFirstCondition = true;
//
//			if (!isbankStateCategoryMatching) {
//
//				failureReason.append("<b>Bank Statement entry not created in database</b>");
//
//				isFirstCondition = false;
//			}
//			if (!isBSVerifiedMatching) {
//
//				if (!isFirstCondition) {
//
//					failureReason.append(", ");
//				}
//				failureReason.append("<b>Bank statement uploaded successfully but not verified</b>");
//			}
//		}
//	}

//	@Test(dependsOnMethods = "BankStatementUpload")
//	public void DocumentUpload() throws InterruptedException {
//		reportUtil.intialLogForTest(TCID);		
//		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user's Document Upload Functionality -<br> Business KYC, Personal KYC, Residential Address Proof");
//		AppApplicationDashScreen appApplicationDashScreen = new AppApplicationDashScreen(driver);
//		AppRequiredDocument appRequiredDocument = new AppRequiredDocument(driver);
//		AppBankStatement appBankStatement = new AppBankStatement(driver);
//		AppBusinessKYC appBusinessKYC = new AppBusinessKYC(driver);
//		AppResidentialAddressProof appResidentialAddressProof = new AppResidentialAddressProof(driver);
//		AppKYC appkyc = new AppKYC(driver);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		
//		appApplicationDashScreen.ClickDocumentsAddMore();
//		appRequiredDocument.ClickBusinessKYC();
//		wait.until(ExpectedConditions.visibilityOf(appBusinessKYC.businessKYCdocDropdown));
//		appBusinessKYC.SelectBusinessKYCDropdown();
//		appBusinessKYC.ClickGSTcertificateCheckbox();
//		appBusinessKYC.ClickGSTcertificateUpload();
//		appBankStatement.SelectCameraToUploadDoc();
//		appBankStatement.ClickCameraFlipForDoc();
//		appBankStatement.ClickCameraCaptureForDoc();
//		appBankStatement.ClickConfirmForClickedImage();
//		appBankStatement.ClickConfirmForClickedImage2();
//		appBusinessKYC.ClickSubmitForUploadedDocs();
//		
//		boolean isBusinessKYCReceivedMessageDisplayed = appRequiredDocument.isBusinessKYCReceivedMessageDisplayed();
//		if (!isBusinessKYCReceivedMessageDisplayed) {
//			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Business KYC document not uploaded");
//			Assert.fail("Business KYC document upload failed");
//			}
//		else
//		{
//			ThreadLocalClass.gettestlevel().log(Status.PASS,"Business KYC document uploaded successfully");
//		}
//		
//		String BussKYCDocType = databaseutil.returnValueByColumnname(query_document_3, "document_type");
//		ThreadLocalClass.gettestlevel().log(Status.INFO, "Business KYC Uploaded successfully : Document Type - " + BussKYCDocType);
//		
////			-----------------------------------------------------------------------------
//
//		appRequiredDocument.isKYCDocAvailableToUpload();
//		
//		String PersonalKYCDocumentCurrentstatus = appRequiredDocument.getStatusForPersonalKYCDocument();
//		if (PersonalKYCDocumentCurrentstatus.equals("Completed"))
//		{
//			ThreadLocalClass.gettestlevel().log(Status.INFO,"Personal KYC document already present and verified");
//			driver.navigate().back();
//			driver.navigate().back();
//		}
//		else
//		{
//		appRequiredDocument.ClickKYC();
//		appkyc.SelectKYCDropdown();
//		appkyc.SelectPANCheckbox();
//		appkyc.SelectPANUpload();
//		appkyc.SelectCameraOptionToUpload();
//		appkyc.isCameraOptionToUploadDisplayed();
//		appkyc.ClickCameraCapture();
//		appkyc.ClickConfirmToCapturedImageToUpalodDoc();
//		appkyc.ClickConfirmToCapturedImageToUpalodDoc2();
//		appkyc.submitForUploadedDoc.click();
//		wait.until(ExpectedConditions.visibilityOf(appRequiredDocument.receivedMessageForKYC));
//		boolean isPersonalKYCReceivedMessageDisplayed = appRequiredDocument.isPersonalKYCReceivedMessageDisplayed();
//		if (!isPersonalKYCReceivedMessageDisplayed) {
//			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Personal KYC document not uploaded");
//			Assert.fail("Personal KYC document upload failed");
//			}
//		else
//		{
//			ThreadLocalClass.gettestlevel().log(Status.PASS,"Personal KYC document uploaded successfully");
//		}
//		
//		}
//		String PersonalKYCDocType = databaseutil.returnValueByColumnname(query_document_4, "document_type");		
//		ThreadLocalClass.gettestlevel().log(Status.INFO, "Personal KYC Uploaded successfully : Document Type - " + PersonalKYCDocType);

//			-----------------------------------------------------------------------	
		
//		wait.until(ExpectedConditions.visibilityOf(appRequiredDocument.statusTextForResAddressProof));
//		
//		String ResAddressProofDocumentCurrentstatus = appRequiredDocument.getStatusForResAddressProofDocument();
//		
//		if (ResAddressProofDocumentCurrentstatus.equals("Completed"))
//		{
//			ThreadLocalClass.gettestlevel().log(Status.INFO,"Residential address proof document already present and verified");
//			driver.navigate().back();
//			driver.navigate().back();
//			
//		}
//		
//		else
//		{
//		appRequiredDocument.ClickResidentialAddressProof();
//		appResidentialAddressProof.residentialAddProofdocDropdown.click();
//		appResidentialAddressProof.aadharCheckbox.click();
//		appResidentialAddressProof.aadharUpload.click();
//		appResidentialAddressProof.cameraOptionToUpalodDoc.click();
////		wait.until(ExpectedConditions.elementToBeClickable(appResidentialAddressProof.cameraCaptureToUpalodDoc));
//		appResidentialAddressProof.cameraCaptureToUpalodDoc.click();
//		appResidentialAddressProof.confirmToCapturedImageToUpalodDoc.click();
//		appResidentialAddressProof.confirmToCapturedImageToUpalodDoc2.click();
//		appResidentialAddressProof.submitForUploadedDoc.click();
//
//		boolean isResAddressProofReceivedMessageDisplayed = appRequiredDocument.isResAddressProofReceivedMessageDisplayed();
//		if (!isResAddressProofReceivedMessageDisplayed) {
//			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Residential address proof document not uploaded");
//			Assert.fail("Residential address proof document upload failed");
//			}
//		else
//		{
//			ThreadLocalClass.gettestlevel().log(Status.PASS,"Residential address proof document uploaded successfully");
//		}
//	}
//		
//		String ResAddressProofDocType = databaseutil.returnValueByColumnname(query_document_5, "document_type");		
//		ThreadLocalClass.gettestlevel().log(Status.INFO, "Personal KYC Uploaded successfully : Document Type - " + ResAddressProofDocType);
//
////			-------------------------------------------------------------------------			
//
//		driver.navigate().back();
//
//		
//		boolean isDocUploadCompleteTickDisplayed = appApplicationDashScreen.isDocUploadCompleteTickDisplayed();
//		if (!isDocUploadCompleteTickDisplayed) {
//			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Documents not uploaded successfully");
//			Assert.fail("Document upload failed");
//			}
//		else
//		{
//			ThreadLocalClass.gettestlevel().log(Status.PASS,"Documents uploaded successfully");
//		}
//
//		driver.navigate().back();
//		
//		boolean isResidentialAddressProofMatching = DocumentCategory.contains("res_address_proof");
//		boolean isKYCMatching = DocumentCategory.contains("kyc");
//		boolean isBusiKYCMatching = DocumentCategory.contains("company_kyc");
//		
//		if (isResidentialAddressProofMatching && isKYCMatching && isBusiKYCMatching) {
//			ThreadLocalClass.gettestlevel().log(Status.PASS,
//					"Document entry successfully created in database<b> Residential Address Proof, Personal KYC, Business KYC.</b>");
//		} else {
//
//			StringBuilder failureReason = new StringBuilder(
//					"Failed to create entry in database for documents : ");
//
//			boolean isFirstCondition = true;
//
//			if (!isResidentialAddressProofMatching) {
//
//				failureReason.append("<b>Residential Address Proof</b>");
//
//				isFirstCondition = false;
//			}
//			if (!isKYCMatching) {
//
//				if (!isFirstCondition) {
//
//					failureReason.append(", ");
//				}
//				failureReason.append("<b>Personal KYC</b>");
//				
//				isFirstCondition = false;
//			}
//			if (!isKYCMatching) {
//
//				if (!isFirstCondition) {
//
//					failureReason.append(", ");
//				}
//				failureReason.append("<b>Business KYC</b>");
//			}
//		}
//		System.out.println("--------<<<< Loan Application Completed Successfully >>>>-----------");
//	}
//	
//	// This method used to validate third party service triggers
	@Test(dependsOnMethods = "FormStep_2", alwaysRun = true)
	public void ServiceTriggers() throws InterruptedException {
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates third party service triggers for the lead -<br> Experian, CKYC, GSTIN");
		
		query_experian_transactions = Databaseutil.getQuery(TCID + "_1", mobile_no);
		String ExperianTransactionId = databaseutil.returnValueByColumnname(query_experian_transactions, "experian_transaction_id");
		
		if(ExperianTransactionId==null || ExperianTransactionId.trim().isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.WARNING, "Experian service not triggered for the lead");
		}
		
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS, "<b>Experian</b> service triggered successfully -<br><b>Experian Transaction Id -</b>"+ExperianTransactionId);
		}
		
		query_ckyc_transactions=Databaseutil.getQuery(TCID + "_2", pan);
		String CKYCTransactionId = databaseutil.returnValueByColumnname(query_ckyc_transactions, "ckyc_transaction_id");
		String CKYCSearchKeyType = databaseutil.returnValueByColumnname(query_ckyc_transactions, "search_key_type");
		String CKYCStatus = databaseutil.returnValueByColumnname(query_ckyc_transactions, "status");
		
		if(CKYCTransactionId==null || CKYCTransactionId.trim().isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.WARNING, "<b>CKYC service not triggered for the lead</b>");
		}
		
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS, "<b>CKYC</b> service triggered successfully -<br><b>CKYC Transaction Id -</b>"+CKYCTransactionId+"<br><b>CKYC Search Key Type -</b>"+CKYCSearchKeyType);
		}
		
		String GSTFoundFlag  = databaseutil.returnValueByColumnname(query_business_detail, "gst_found_flag");

		if(GSTFoundFlag==null || GSTFoundFlag.trim().isEmpty())
		{
			ThreadLocalClass.gettestlevel().log(Status.WARNING, "GSTIN service not triggered for the lead");
		}
		
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS, "<b>GSTIN</b> service triggered successfully for the lead");
		}
	}
}
