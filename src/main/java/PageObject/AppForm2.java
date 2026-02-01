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

public class AppForm2 {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppForm2(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_monthly_sales\"]")
	@CacheLookup
	WebElement monthlySalesField;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"less than 2 Lakhs\"]")
	@CacheLookup
	WebElement monthlySalesLessThanTwo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"2 - 4 Lakhs\"]")
	@CacheLookup
	WebElement monthlySalesTwotoFour;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"4 - 10 Lakhs\"]")
	@CacheLookup
	WebElement monthlySalesFourtoTen;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"10 Lakhs +\"]")
	@CacheLookup
	WebElement monthlySalesMoreThanTen;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_gst_yes\"]")
	@CacheLookup
	WebElement gstYesButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_gst_no\"]")
	@CacheLookup
	WebElement gstNoButton;
	
//	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.example.app:id/et_name\"]")
//	@CacheLookup
//	WebElement nameField;
	
	@AndroidFindBy(id = "com.example.app:id/et_first_name")
	@CacheLookup
	WebElement firstnameField;
	
	@AndroidFindBy(id = "com.example.app:id/et_last_name")
	@CacheLookup
	WebElement lastnameField;
	
	@AndroidFindBy(id = "com.example.app:id/tv_invalid_first_name")
	@CacheLookup
	WebElement firstNameError;
	
	@AndroidFindBy(id = "com.example.app:id/tv_invalid_last_name")
	@CacheLookup
	WebElement lastNameError;
	
	
	
	


//	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_email\"]")
//	@CacheLookup
//	WebElement emailField;
//	
//	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_email_exist\"]")
//	@CacheLookup
//	WebElement emailErrorMessage;
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_submit\"]")
	WebElement form2submit;
	
	
	public void SelectMonthlySales() {
		monthlySalesField.click();
	}
	
	public void SelectMonthlySales1(String mto) {
		monthlySalesField.sendKeys(mto);
	}
	
	public String GetSelectedMTO() {
		return monthlySalesField.getText();
	}
	
	public void SelectMonthlySalesLessThanTwo() {
		monthlySalesLessThanTwo.click();
	}
	
	public void SelectMonthlySalesTwotoFour() {
		monthlySalesTwotoFour.click();
	}
	
	public void SelectMonthlySalesFourtoTen() {
		monthlySalesFourtoTen.click();
	}
	
	public void SelectMonthlySalesMoreThanTen() {
		monthlySalesMoreThanTen.click();
	}
	
	public void SelectGSTYes() {
		gstYesButton.click();
	}
	
	public boolean isGST_YESSelected() {
		return gstYesButton.isSelected();
		
	}
	
	public void SelectGSTNo() {
		gstNoButton.click();
	}
	
	public boolean isGST_NOSelected() {
		return gstNoButton.isSelected();
		
	}
	
	public void Form2Submit() {
		form2submit.click();
	}
	
	public void EnterFirstNameField(String name) {
		
		firstnameField.sendKeys(name);
		}
	
	public void EnterLastNameField(String name) {
		lastnameField.sendKeys(name);
		}
		
		public void ClearFirstNameField() {
			firstnameField.clear();
			}
		
		public void ClearLastNameField() {
			lastnameField.clear();
			}
		
		public void EnterInvalidFirstNameField(String name) {
			firstnameField.clear();
			
			firstnameField.sendKeys(name);
			}
		
		public void EnterInvalidLastNameField(String name) {
			lastnameField.clear();
			
			lastnameField.sendKeys(name);
			}
		
		
		public boolean isFirstNameErrorDisplayed() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(firstNameError));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
		
		public String GetFirstNameErrorMessage() {
			return firstNameError.getText();
	        
	    }
		
		public String GetLastNameErrorMessage() {
			return lastNameError.getText();

	    }
		
		public boolean isLastNameErrorDisplayed() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(lastNameError));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
					
			public boolean isForm2SubmitBtnEnabled() {
				return form2submit.isEnabled();
				}

			public boolean isForm2PageDisplayed() {
		        try {
		            wait.until(ExpectedConditions.visibilityOf(firstnameField));
		            return true;
		        } catch (Exception e) {
		            return false;
		        }
		    }

}
