package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppAmount {

	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppAmount(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}

	@AndroidFindBy(id="com.example.app:id/selectedValue")
	@CacheLookup
	WebElement amountField;


	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_proceed\"]")
	@CacheLookup
	WebElement CheckEligibilityBtn;

	public void ClickAmountField(){
		amountField.click();
	}

	public void EnterAmount(String amount) {
		amountField.sendKeys(amount);
	}

	public void EnterInvalidAmount(String amount) {
		amountField.clear();
		amountField.sendKeys(amount);
	}

	public void ClearInvalidAmount() {
		amountField.clear();
	}

	public void ClickCheckEligibilityBtn() {
		CheckEligibilityBtn.click();
	}

	public boolean IsCheckEligibilityBtnEnabled() {
		return CheckEligibilityBtn.isEnabled();
	}


	public boolean isAmountPageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(amountField)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
