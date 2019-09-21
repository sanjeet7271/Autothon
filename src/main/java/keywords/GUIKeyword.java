package keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import client.BrowserFactory;

import client.IBrowserFactory;
import reportLogger.ReportFactory;

public class GUIKeyword {

	public String getScreenshot(String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destination = null;
        try {
        	File src= ((TakesScreenshot)new BrowserFactory().getDriver()).getScreenshotAs(OutputType.FILE);
        	destination = ReportFactory.reportFolder+"\\"+screenshotName+dateName+".png";
        	FileUtils.copyFile(src, new File(destination));
			return destination;
		} catch (IOException e) {
			ReportFactory.getInstance().info("Error in taking Screen shots");
		}
		return destination;
        
   	}
}
