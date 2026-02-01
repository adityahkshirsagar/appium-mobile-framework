package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppApplicationDashScreen {

	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppApplicationDashScreen(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(15));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Preview\"]")
	@CacheLookup
	WebElement applicationFormPreview;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_upload_statment\"]")
	@CacheLookup
	WebElement bankStatementUpload;

	@AndroidFindBy(xpath="(//android.widget.TextView[@text=\"Add More\"])[2]")
	@CacheLookup
	WebElement documentAddMore;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id=\"com.example.app:id/iv_banking_complete\"]")
	@CacheLookup
	WebElement bankingCompleteTick;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id=\"com.example.app:id/iv_banking_complete\"]")
	@CacheLookup
	public WebElement documentCompleteTick;

	public void ClickApplicationFormPreview()
	{
		applicationFormPreview.click();
	}

	public void ClickBankStatementUpload()
	{
		bankStatementUpload.click();
	}

	public void ClickDocumentsAddMore()
	{
		documentAddMore.click();
	}

	public boolean isAppDashScreenDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(applicationFormPreview)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


	public boolean isBankStateUploadCompleteTickDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(bankingCompleteTick)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public boolean isDocUploadCompleteTickDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(documentCompleteTick)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
