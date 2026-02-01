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

public class AppFormStep_3 {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppFormStep_3(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Retailer\"]")
	@CacheLookup
	public WebElement natureOfBusinessRetailer;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Wholesaler\"]")
	@CacheLookup
	WebElement natureOfBusinessWholesaler;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Manufacturer\"]")
	@CacheLookup
	WebElement natureOfBusinessManufacturer;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Service Provider\"]")
	@CacheLookup
	WebElement natureOfBusinessServiceProvider;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tagTextView\" and @text=\"Wholesaler and Retailer\"]")
	@CacheLookup
	WebElement natureOfBusinessWholesalerandRetailer;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_selectProductList\"]")
	@CacheLookup
	WebElement productListfield;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_selectProductList\"]")
	@CacheLookup
	WebElement productListDropdown;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/searchbank\"]")
	@CacheLookup
	WebElement productListSearch;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/checkBox\"]")
	@CacheLookup
	WebElement checkSearchedProduct;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_productSelected\"]")
	@CacheLookup
	WebElement productSelectBtn;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_legal_name\"]")
	@CacheLookup
	WebElement businessNameField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_business_address_one\"]")
	@CacheLookup
	WebElement businessAddressLineOneField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_business_address_two\"]")
	@CacheLookup
	WebElement businessAddressLineTwoField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_business_pin_code\"]")
	@CacheLookup
	WebElement businessPincodeField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_business_city\"]")
	@CacheLookup
	WebElement businessCityField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_business_state\"]")
	@CacheLookup
	WebElement businessStateField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_resident_address_one\"]")
	@CacheLookup
	WebElement residentialAddressLineOneField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_resident_address_two\"]")
	@CacheLookup
	WebElement residentialAddressLineTwoField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_resident_pin_code\"]")
	@CacheLookup
	WebElement residentialPincodeField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_resident_city\"]")
	@CacheLookup
	WebElement residentialCityField;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_resident_state\"]")
	@CacheLookup
	public WebElement residentialStateField;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_next\"]")
	@CacheLookup
	WebElement formStep3NextBtn;
	
//	------------------------------------------
	
	public void SelectNatureBusinessRetailer(){
		natureOfBusinessRetailer.click();
	}
	
	public void SelectNatureBusinessWholesaler(){
		natureOfBusinessWholesaler.click();
	}
	
	public void SelectNatureBusinessManufacturer(){
		natureOfBusinessManufacturer.click();
	}
	
	public void SelectNatureBusinessServiceProvider(){
		natureOfBusinessServiceProvider.click();
	}
	
	public void SelectNatureBusinessWholesalerandRetailer(){
		natureOfBusinessWholesalerandRetailer.click();
	}
	
	public void SelectProductListField(String productsList){
		productListDropdown.sendKeys(productsList);
	}
	
	public void SelectProductListField(){
		productListDropdown.click();
	}
	
	public void SelectProductListSearchField(String searchText){
		productListSearch.sendKeys(searchText);
	}
	
	public void SelectMarkCheckboxForSearchedProduct(){
		checkSearchedProduct.click();
	}
	
	public void SubmitSelectedProduct(){
		productSelectBtn.click();
	}
	
	public void EnterBusinessName(String businessName){
		businessNameField.sendKeys(businessName);
	}
	
	public void EnterBusinessAddressLineOne(String businessAddress_1){
		businessAddressLineOneField.sendKeys(businessAddress_1);
	}
	
	public void EnterBusinessAddressLineTwo(String businessAddress_2){
		businessAddressLineTwoField.sendKeys(businessAddress_2);
	}
	
	public void EnterBusinessAddressPincode(String businessPincode){
		businessPincodeField.sendKeys(businessPincode);
	}
	
	public void EnterBusinessAddressCity(String businessCity){
		businessCityField.sendKeys(businessCity);
	}
	
	public void EnterBusinessAddressState(String businessState){
		businessStateField.sendKeys(businessState);
	}
	
	public void EnterResidentialAddressLineOne(String residentialAddress_1){
		residentialAddressLineOneField.sendKeys(residentialAddress_1);
	}
	
	public void EnterResidentialAddressLineTwo(String residentialAddress_2){
		residentialAddressLineTwoField.sendKeys(residentialAddress_2);
	}
	
	public void EnterResidentialAddressPincode(String residentialPincode){
		residentialPincodeField.sendKeys(residentialPincode);
	}
	
	public void EnterResidentialAddressCity(String residentialCity){
		residentialCityField.sendKeys(residentialCity);
	}
	
	public void EnterResidentialAddressState(String residentialState){
		residentialStateField.sendKeys(residentialState);
	}
	
	public void ClickFormStep3NextBtn() {
		formStep3NextBtn.click();	
	}
	
	public boolean isAppFormStep3PageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(natureOfBusinessRetailer)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
