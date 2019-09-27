package com.keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.client.BrowserFactory;
import com.client.SeleniumClient2;
import com.frameworkConstant.FrameworkConstant;
import com.reportLogger.ReportFactory;

public class GUIKeyword {
	public static Logger logger = Logger.getLogger(GUIKeyword.class);
	WebDriver driver = SeleniumClient2.getInstance().getSelenium();
	private WebDriverWait wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
	
	
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
	/**
	 * 
	 * @param fluent wait
	 */
	protected void fluentWaitForElement(final By elementLocator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(FrameworkConstant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
		wait.withTimeout(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class); // We need to ignore this
														// exception.

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(elementLocator);
				if (element != null) {
					System.out.println("A new dynamic object is found.");
				}
				return element;
			}
		};

		wait.until(function);
	}


	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//The presenceOfElementLocated just checks the DOM to see if it can locate an element no matter its visibility.
	
	protected boolean presenceOfElementLocated(By elementLocator) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param text
	 * @return
	 */
	//An expectation for checking if the given text is present in the specified element.
	protected boolean textToBePresentInElementValue(By elementLocator, String text) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.textToBePresentInElementValue(elementLocator, text));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//Switch to frame using WebElement
	protected boolean frameToBeAvailableAndSwitchToIt(By elementLocator) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param frameIndex
	 * @return
	 */
	//Switch to frame using Frame Index
	protected boolean frameToBeAvailableAndSwitchToIt(int frameIndex) {
		logger.debug("Looking for element locator..." + frameIndex);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			logger.debug("Elemnt locator found..." + frameIndex);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + frameIndex);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param frameName
	 * @return
	 */
	//Switch to frame using frame Name
	protected boolean frameToBeAvailableAndSwitchToIt(String frameName) {
		logger.debug("Looking for element locator..." + frameName);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
			logger.debug("Elemnt locator found..." + frameName);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + frameName);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//Wait until an element is no longer attached to the DOM.
	//or  If WebElement present, Expectedconditions.stalenessOf will wait until Element is deleted.
	protected boolean stalenessOf(By elementLocator) {
		wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.stalenessOf(driver.findElement(elementLocator)));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param time
	 * @return
	 */
	
	protected boolean stalenessOfIntime(By elementLocator, int time) {
		wait = new WebDriverWait(driver, time);
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.stalenessOf(driver.findElement(elementLocator)));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param text
	 * @return
	 */
	//An expectation for checking WebElement with given locator has specific text
	protected boolean textToBe(By elementLocator, String text) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.textToBe(elementLocator, text));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param noOfElement
	 * @return
	 */
	//An expectation for checking number of WebElements with given locator
	protected boolean numberOfElementsToBeMoreThan(By elementLocator, int noOfElement) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(elementLocator, noOfElement));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param noOfElement
	 * @return
	 */
	//An expectation for checking number of WebElements with given locator being less than defined number
	protected boolean numberOfElementsToBeLessThan(By elementLocator, int noOfElement) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeLessThan(elementLocator, noOfElement));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param noOfElement
	 * @return
	 */
	//An expectation for checking number of WebElements with given locator
	protected boolean numberOfElementsToBe(By elementLocator, int noOfElement) {
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.numberOfElementsToBe(elementLocator, noOfElement));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	//
	protected boolean alertIsPresent() {
		logger.debug("Looking for element locator ");
		wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			logger.debug("Elemnt locator found...");
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found...");
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param time
	 * @return
	 */
	protected boolean alertIsPresentinTime(int time) {
		logger.debug("Looking for element locator  for time..." + time);
		wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			logger.debug("Elemnt locator found...");
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found...");
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//An expectation for checking that an element is present on the DOM of a page and visible.
	protected boolean waitForElement(By elementLocator) {
		wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param time
	 * @return
	 */
	//An expectation for checking that an element is present on the DOM of a page and visible with given amount of time
	protected boolean isElementPresentinTime(By elementLocator, int time) {
		logger.debug("Looking for element locator " + elementLocator + " for time..." + time);
		wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//An expectation for checking an element is visible and enabled such that you can click it
	protected boolean elementToClickable(By elementLocator) {
		logger.debug("Looking for element locator to clickable " + elementLocator);
		wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			return true;
		} catch (Exception e) {
			logger.error("Element is not clickable" + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param time
	 * @return
	 */
	protected boolean isElementClickableinTime(By elementLocator, int time) {
		wait = new WebDriverWait(driver, time);
		logger.debug("Looking for element locator to clickable " + elementLocator + " for time..." + time);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	//An expectation for checking that an element is either invisible or not present on the DOM.
	protected boolean waitForInvisibility(By elementLocator) {
		logger.debug("Looking for element locator to clickable " + elementLocator);
		wait = new WebDriverWait(driver, FrameworkConstant.DEFAULTTIMEOUT);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element is not clickable");
			return false;
		}

	}
}
