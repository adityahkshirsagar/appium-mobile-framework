package GenericUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

public class ScreenCapture {
	
	public static AppiumDriver driver;
	
	public String captureScreen(AppiumDriver driver,String picname) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String target = (FilePaths.SCREENSHOTS + picname + ".png");

		File destination = new File(target);

		FileUtils.copyFile(source, destination);

		System.out.println("Screenshot Taken");

		return target;
	}

}