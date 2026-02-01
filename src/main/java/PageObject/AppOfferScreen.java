package PageObject;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppOfferScreen {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppOfferScreen(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_proceed\"]")
	@CacheLookup
	
	WebElement offerAcceptBtn;
	
	
	public boolean ifOfferDisplayed() {
		try {
			return
					offerAcceptBtn.isDisplayed();
		}
		catch (NoSuchElementException e)
	
		{
			return false;
		}
	}	
	
	public void SelectAcceptOfferBtn(){
		offerAcceptBtn.click();
	}
	
	public boolean isAppOfferPageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(offerAcceptBtn)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	

}
