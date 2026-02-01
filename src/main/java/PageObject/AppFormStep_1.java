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

public class AppFormStep_1 {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppFormStep_1(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Male\"]")
	@CacheLookup
	WebElement genderSelectionMale;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Female\"]")
	@CacheLookup
	WebElement genderSelectionFemale;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Others\"]")
	@CacheLookup
	WebElement genderSelectionOther;
	
	@AndroidFindBy(id="com.example.app:id/et_dob")
	@CacheLookup
	WebElement dob;
	
	@AndroidFindBy(id="com.example.app:id/btn_confirm")
	@CacheLookup
	WebElement dobSelectBtn;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id=\"com.example.app:id/picker_day\"]")
	@CacheLookup
	WebElement dobDayPicker;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id=\"com.example.app:id/picker_month\"]")
	@CacheLookup
	WebElement dobMonthPicker;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id=\"com.example.app:id/picker_year\"]")
	@CacheLookup
	public WebElement dobYearPicker;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_sales\"]")
	@CacheLookup
	WebElement MonthlySales;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"More than 5 years\"]")
	@CacheLookup
	WebElement BusinessAgeMorethan5years;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"2 to 5 years\"]")
	@CacheLookup
	WebElement BusinessAge2to5years;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"1 to 2 years\"]")
	@CacheLookup
	WebElement businessAge1to2WebElement;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Less than 1 year\"]")
	@CacheLookup
	WebElement lessThan1year;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\"])[5]")
	@CacheLookup
	WebElement currentAccountYes;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\"])[6]")
	@CacheLookup
	WebElement currentAccountNo;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\"])[7]")
	@CacheLookup
	WebElement gstRegistrationYes;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\"])[8]")
	@CacheLookup
	WebElement gstRegistrationNo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"I run a business\"]")
	@CacheLookup
	WebElement occupationBusinessman;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"I am a salaried employee\"]")
	@CacheLookup
	WebElement occupationSalaried;
	
	@AndroidFindBy(id="com.example.app:id/bt_next")
	@CacheLookup
	WebElement formStep1NextBtn;
	
	
	public void SelectGenderMale(){
		genderSelectionMale.click();
	}
	
	public void SelectGenderFemale(){
		genderSelectionFemale.click();
	}
	
	public void SelectGenderOther(){
		genderSelectionOther.click();
	}
	
	
	public void SelectDOBField() {
		dob.click();
	}
	
	public void SelectDOBField1(String dob1) {
		dob.sendKeys(dob1);
	}
	
	public void EnterDOB(String DOB) {
		dob.sendKeys(DOB);
	}
	
	public void SelectDOB() {
		dobSelectBtn.click();
	}
	
	public void SelectDayPicker() {
		dobDayPicker.click();
	}
	
	public void SelectMonthPicker(String month) {
		dobMonthPicker.sendKeys(month);
	}
	
	public void SelectYearPicker(String year) {
		dobYearPicker.sendKeys(year);
	}
	
	public void EditMonthlySales(String monthlySales) {
		MonthlySales.clear();
		MonthlySales.sendKeys(monthlySales);
	}
	
	public String GetMonthlySalesValue() {
		return MonthlySales.getText();
	}
	
	public void ClearMonthlySales() {
		MonthlySales.clear();
	}
	
	public String MTOFieldValue()
	{
		return MonthlySales.getAttribute("text");
	}
	
	public int MTOFieldValueCount(String value)
	{
		return value.length();
	}
	
	public void SelectBusinessAgeMoreThan5Years(){
		BusinessAgeMorethan5years.click();
	}
	
	public void SelectBusinessAge2to5Years(){
		BusinessAge2to5years.click();
	}
	
	public void SelectBusinessAge1to2Years(){
		businessAge1to2WebElement.click();
	}
	
	public void SelectBusinessAgeLessThan1Year(){
		lessThan1year.click();
	}
	
	public void SelectCurrentAccountYes(){
		currentAccountYes.click();
	}
	
	public void SelectCurrentAccountNo(){
		currentAccountNo.click();
	}
	
	public void SelectGSTRegistrationYes(){
		gstRegistrationYes.click();
	}
	
	public void SelectGSTRegistrationNo(){
		gstRegistrationNo.click();
	}
	
	public void SelectOccupationBusinessMan(){
		occupationBusinessman.click();
	}
	
	public void SelectOccupationSalaried(){
		occupationSalaried.click();
	}
	
	public void ClickFormStep1NextBtn() {
		formStep1NextBtn.click();
		
	}
	
	public boolean IsFormStep1NextBtnEnabled() {
		return formStep1NextBtn.isEnabled();
		
	}
	
	public boolean isAppFormStep1PageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(genderSelectionMale)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
