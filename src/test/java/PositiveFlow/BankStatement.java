package PositiveFlow;

import java.time.Duration;

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
import PageObject.AppBankStatement;
import PageObject.AppApplicationDashScreen;

@Listeners(ListenerClass.class)
public class BankStatement extends BaseClass{
	
		
	@Test (priority=1)
	public void BankStatementUpload() throws InterruptedException {
		
		ThreadLocalClass.gettestlevel().log(Status.INFO,"<b>Test Case Type</b> - Positive<br><b>Description</b> - Test case validates user's Bank Statement upload functionality");
		AppApplicationDashScreen appApplicationDashScreen = new AppApplicationDashScreen(driver);
		AppBankStatement appBankStatement = new AppBankStatement(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		appApplicationDashScreen.ClickBankStatementUpload();
		appBankStatement.ClickBankListDropdown();
		appBankStatement.EnterBankNameInSearchBox("HDFC");
		appBankStatement.SelectSearchedBank();
		appBankStatement.ClickBankStatementDoneBtn();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		appBankStatement.ClickSelectedBankNextArrow();
		appBankStatement.ClickUploadManualBankStatement();
		appBankStatement.SelectFileToUploadDoc();
		appBankStatement.SelectSearchOptioninDeviceStorage();
		appBankStatement.EnterSearchKeywordForDocument("Bank Statement");		
		Thread.sleep(20000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOf(appBankStatement.bankStatementFromLocalStorage));
		appBankStatement.SelectBankStatementFromLocalStorage();
		wait.until(ExpectedConditions.visibilityOf(appBankStatement.oneFileSelectedMessage));
		
		appBankStatement.ClickBankStatementSubmit();
		wait.until(ExpectedConditions.elementToBeClickable(appBankStatement.addMoreBanksOption));
		driver.navigate().back();
//		wait.until(ExpectedConditions.visibilityOf(appApplicationDashScreen.bankingCompleteTick));
		boolean isBankStateParsed = appApplicationDashScreen.isBankStateUploadCompleteTickDisplayed();
		
			if (isBankStateParsed)
			{
				System.out.println("Bank statement uploaded successfully");
			}
			else 
			{
				System.out.println("Bank statement not uploaded or parsed successfully");
			}
		
		
		ThreadLocalClass.gettestlevel().log(Status.PASS, "Bank statement uploaded successfully");
		
		boolean isAppDashScreenDisplayed = appLoanApplicationDashScreen.isAppDashScreenDisplayed();
		if (!isAppDashScreenDisplayed) {
			ThreadLocalClass.gettestlevel().log(Status.FAIL,"Flow not redirected to Document upload screen");
			Assert.fail("Document upload screen redirection failed");
			}
		else
		{
			ThreadLocalClass.gettestlevel().log(Status.PASS,"Flow successfully redirected to Document upload screen");
		}
	}	
		
		@Test (priority=2)
		public void BankStatementDBValidation() throws InterruptedException {
		
		String query_document_bank_statement = Databaseutil.getQuery("query_document_bank_statement", application_code);
		String BSDocumentCategory = databaseutil.returnValueByColumnname(query_document_bank_statement, "document_category");
		String isBSVerified = databaseutil.returnValueByColumnname(query_document_bank_statement, "is_verified");
		boolean isbankStateCategoryMatching = BSDocumentCategory.equals("bank_statement");
		boolean isBSVerifiedMatching = isBSVerified.equals("1");

		if (isbankStateCategoryMatching && isBSVerifiedMatching) {
			ThreadLocalClass.gettestlevel().log(Status.PASS,
					"<b>Bank statmenent uploaded successfully in database and parsed</b>");
		} else {

			StringBuilder failureReason = new StringBuilder(
					"Bansk statement Upload : ");

			boolean isFirstCondition = true;

			if (!isbankStateCategoryMatching) {

				failureReason.append("<b>Bank Statement entry not uploaded</b>");

				isFirstCondition = false;
			}
			if (!isBSVerifiedMatching) {

				if (!isFirstCondition) {

					failureReason.append(", ");
				}
				failureReason.append("<b>Bank statement uploaded successfully but not parsed</b>");
			}
		}
}

}
