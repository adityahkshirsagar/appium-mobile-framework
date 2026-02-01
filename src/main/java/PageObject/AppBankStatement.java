package PageObject;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppBankStatement {
	
	AppiumDriver ldriver;
	private WebDriverWait wait;

	public AppBankStatement(AppiumDriver rdriver)
	{
		ldriver=rdriver;
		this.wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(rdriver, Duration.ofSeconds(10)), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/et_select_doc_type\"]")
	@CacheLookup
	WebElement selectBankDropdown;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.example.app:id/searchbank\"]")
	@CacheLookup
	WebElement bankNameSearch;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id=\"com.example.app:id/checkBox\"]")
	@CacheLookup
	WebElement bankSelectCheckbox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_bankDone\"]")
	@CacheLookup
	WebElement bankStatementDoneBtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.example.app:id/ll_list_view\"]")
	@CacheLookup
	public WebElement selectedBankNameNextArrow;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_uploadImg\"]")
	@CacheLookup
	WebElement uploadManualBankStatement;
	
//	------------------------------------------------------------------------	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Files\"]")
	@CacheLookup
	WebElement fileOptionToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Camera\"]")	
	@CacheLookup
	WebElement cameraOptionToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/captureBtn\"]")
							//android.widget.Button[@resource-id="com.example.app:id/captureBtn"]
	@CacheLookup
	public WebElement cameraCaptureToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Flip Camera\"]")
	@CacheLookup
	WebElement cameraFlipToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/confirmImgBtn\"]")
	@CacheLookup
	WebElement confirmToCapturedImageToUpalodDoc;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/btn_done\"]")
	@CacheLookup
	WebElement confirmToCapturedImageToUpalodDoc2;
	
	
	
//	------------------------------------------------------------------------	
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Search\"]")
	@CacheLookup
	WebElement serachOptionInDeviceStorage;
	
	@AndroidFindBy(xpath="//android.widget.AutoCompleteTextView[@resource-id=\"com.google.android.documentsui:id/search_src_text\"]")
	@CacheLookup
	public WebElement SearchKeywardtoSearchDocument;
	
//	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id=\"com.google.android.documentsui:id/item_root\"]")
//	@CacheLookup
//	public WebElement bankStatementFromLocalStorage;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"]")
	@CacheLookup
	public WebElement bankStatementFromLocalStorage;
	
	@AndroidFindBy(id="com.google.android.documentsui:id/action_menu_select")
	@CacheLookup
	public WebElement bankStatementSelectFromLocalStorage;
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.example.app:id/bt_done\"]")
	@CacheLookup
	public WebElement bankStatementSubmit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/tv_addBank\"]")
	@CacheLookup
	public WebElement addMoreBanksOption;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.example.app:id/imageCount\"]")
	@CacheLookup
	public WebElement oneFileSelectedMessage;
	
	
	
	
	
	
//	----------------------------------------------
	
	public void ClickBankListDropdown()
	{
		selectBankDropdown.click();
	}
	
	public void EnterBankNameInSearchBox(String bankNameSearchText)
	{
		bankNameSearch.sendKeys(bankNameSearchText);
	}
	
	public void SelectSearchedBank()
	{
		bankSelectCheckbox.click();
	}
	
	public void ClickBankStatementDoneBtn()
	{
		bankStatementDoneBtn.click();
	}
	
	public void ClickSelectedBankNextArrow()
	{
		selectedBankNameNextArrow.click();
	}
	
	public void ClickUploadManualBankStatement()
	{
		uploadManualBankStatement.click();
	}
	
	
	
	
	
	
	
//	----------------------------------------------	
	
	public void SelectFileToUploadDoc()
	{
		fileOptionToUpalodDoc.click();
	}
	
	public void SelectCameraToUploadDoc()
	{
		cameraOptionToUpalodDoc.click();
	}
	
	public void ClickCameraCaptureForDoc()
	{
		cameraCaptureToUpalodDoc.click();
	}
	
	public void ClickCameraFlipForDoc()
	{
		cameraFlipToUpalodDoc.click();
	}
	
	public void ClickConfirmForClickedImage()
	{
		confirmToCapturedImageToUpalodDoc.click();
	}
	
	public void ClickConfirmForClickedImage2()
	{
		confirmToCapturedImageToUpalodDoc2.click();
	}
	
	
	
	
//	----------------------------------------------	
	
	public void SelectSearchOptioninDeviceStorage()
	{
		serachOptionInDeviceStorage.click();
	}
	
	public void EnterSearchKeywordForDocument(String serachDocument)
	{
		SearchKeywardtoSearchDocument.sendKeys(serachDocument);
	}
	
	public void SelectBankStatementFromLocalStorage() throws InterruptedException
	{
		bankStatementFromLocalStorage.click();
		Thread.sleep(2000);
		bankStatementSelectFromLocalStorage.click();
	}
	
	
	public void ClickBankStatementSubmit()
	{
		bankStatementSubmit.click();
	}
	
	public String getMessageForBankSateSelection() {
        return uploadManualBankStatement.getText();
    }
	
	public boolean isAppBankSatementPageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(selectBankDropdown)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
