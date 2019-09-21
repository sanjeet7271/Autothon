package client;

import org.openqa.selenium.WebDriver;

import client.IBrowserFactory;

public interface IBrowserFactory {
//	public static BrowserFactory getInstance();
	void launchBrowser(String browserType) throws Exception;
	public WebDriver getDriver();
	public void quitDriver();
	
}
