package com.client;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.frameworkConstant.FrameworkConstant;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumClient2 implements IBrowserFactory{
	
	private static SeleniumClient2 instance = null;
	private WebDriver driver;
	
	public static synchronized SeleniumClient2 getInstance() {

		if (null == instance) {
			instance = new SeleniumClient2();
		}
		return instance;
	}

	public WebDriver getSelenium() {
		return driver;
	}
	
	private enum browsers {
		IE, CHROME, FIREFOX
	};

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	@SuppressWarnings("deprecation")
	private void launchBrowser() {
		switch (browsers.valueOf(FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase())) {
		case IE:
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities = getIECapabilities(ieCapabilities);
			driver = new InternetExplorerDriver(ieCapabilities);
			break;
		case CHROME:
			DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			chromeCapabilities = getChromeCapabilities(chromeCapabilities);
			driver = new ChromeDriver(chromeCapabilities);
			break;
		case FIREFOX:
			DesiredCapabilities fireFoxcapabilities = DesiredCapabilities.firefox();
			fireFoxcapabilities = getFireFoxCapabilities(fireFoxcapabilities);
			driver = new FirefoxDriver(fireFoxcapabilities);
			break;
		default:
			ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities = getIECapabilities(ieCapabilities);
			driver = new InternetExplorerDriver(ieCapabilities);
			break;
		}
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private void setGridDriver() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (browsers.valueOf(FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase())) {
		case IE:
			capabilities = getIECapabilities(capabilities);
			break;

		case CHROME:
			capabilities = getChromeCapabilities(capabilities);
			capabilities.setPlatform(Platform.VISTA);
			break;

		case FIREFOX:
			capabilities = getFireFoxCapabilities(capabilities);
			break;

		default:
			capabilities = getIECapabilities(capabilities);
			break;

		}
		driver = new RemoteWebDriver(new URL(FrameworkConstant.GLOBALCONFIG.get("REMOTE_URL")), capabilities);
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getIECapabilities(DesiredCapabilities ieCapabilities) {
		WebDriverManager.iedriver().setup();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());

		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);

		ieCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		// Accept unexpected alerts
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setCapability("enablePersistentHover", false);
		ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
		ieCapabilities.setCapability("disable-popup-blocking", true);
		ieCapabilities.setJavascriptEnabled(true);

		return ieCapabilities;
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getChromeCapabilities(DesiredCapabilities chromeCapabilities) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-plugins");
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		return chromeCapabilities;
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getFireFoxCapabilities(DesiredCapabilities fireFoxcapabilities) {
		WebDriverManager.firefoxdriver().setup();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);

		fireFoxcapabilities.setBrowserName("firefox");
		fireFoxcapabilities.setCapability("marionette", true);
		// System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,
		// "false");
		fireFoxcapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		fireFoxcapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		profile.setPreference("browser.private.browsing.autostart", true);
		fireFoxcapabilities.setCapability(FirefoxDriver.PROFILE, profile);
		
		return fireFoxcapabilities;

	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private void setDriverParameters() {
	
		driver.manage().timeouts().implicitlyWait(FrameworkConstant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(FrameworkConstant.PAGELOADTIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
	}
	
	@Override
	public WebDriver getDriver() {		
		return driver;
	}
	
	@Override
	public void quitDriver() {
		driver.quit();
	}
///
	@Override
	public void launchBrowser(String browserType) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
