package PageObject;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppDashboard_2 {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppDashboard_2(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.example.app:id/tab_application\"]")
	@CacheLookup
	WebElement dashboardApplication;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.example.app:id/tab_loan_offer\"]")
	@CacheLookup
	WebElement dashboardOffer;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.example.app:id/tab_post_loan_offer\"]")
	@CacheLookup
	WebElement dashboardFormalities;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.example.app:id/tab_disbursment\"]")
	@CacheLookup
	WebElement dashboardDisbursement;

	public boolean ifDashboardDisplayed() {
		try {
			return
					dashboardApplication.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
	}
	
	
	
	public void ClickApplicationTab()
	{
		dashboardApplication.click();
	}

	public void ClickOfferTab()
	{
		dashboardOffer.click();
	}

	public void ClickFormalitiesTab()
	{
		dashboardFormalities.click();
	}

	public void ClickDisbursementTab()
	{
		dashboardDisbursement.click();
	}
	
}
