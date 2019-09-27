package com.client;

import org.openqa.selenium.WebDriver;

import com.client.IBrowserFactory;

public interface IBrowserFactory {
//	public static BrowserFactory getInstance();
	void launchBrowser(String browserType) throws Exception;
	public WebDriver getDriver();
	public void quitDriver();
	
}
