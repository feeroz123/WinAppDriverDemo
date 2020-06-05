package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.Utilities;

public class Base {
	
	@SuppressWarnings("rawtypes")
	public static WindowsDriver driver;
	public static DesiredCapabilities caps;
	Utilities utls = new Utilities();
	
	private final static Logger log = LogManager.getLogger(Base.class.getName());
	
	/***
	 * Method to launch the WinAppDriver.exe
	 * @param browser
	 */
	public void launchWinAppDriver() {
		log.debug("Starting WinAppDriver.exe server");
		utls.startWinAppDriverServer();
	}
	
	/***
	 * Method to launch the WinAppDriver.exe
	 * @param browser
	 * @throws InterruptedException 
	 */
	public void closeWinAppDriver() {
		log.debug("Starting WinAppDriver.exe server");
		utls.stopWinAppDriverServer();
	}
	
	/***
	 * Method to launch the application
	 * @throws MalformedURLException
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("rawtypes")
	public void launchApplication() throws MalformedURLException, InterruptedException {
		log.debug("Starting the Enterprise Monitor application");
		
		caps = new DesiredCapabilities();
		caps.setCapability("app", getProperty("APP_PATH"));  
		driver = new WindowsDriver(new URL(getProperty("DRIVER_URL")), caps);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Mandatory sleep to avoid failure after launch of application
		Thread.sleep(1000);
	}
	
	/***
	 * Enhanced method to perform Click action by Xpath
	 * 
	 * @param str_Xpath
	 * @param Str_description
	 */
	public  void clickByXPath(String str_Xpath, String Str_description) {
		try {
			waitTillElementIsClickable(str_Xpath);
			driver.findElement(By.xpath(str_Xpath)).click();
			log.debug("Clicked on: " + Str_description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - " + Str_description);
			Assert.fail();
		}
	}
	
	/***
	 * Enhanced method to perform Click action by Class Name
	 * 
	 * @param str_ClassName
	 * @param Str_description
	 */
	public  void clickByClassName(String str_ClassName, String Str_description) {
		try {
			//waitTillElementIsClickable(str_ClassName);

			driver.findElementByClassName(str_ClassName).click();
			log.debug("Clicked on: " + Str_description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - " + Str_description);
			Assert.fail();
		}
	}
	
	/***
	 * Enhanced method to perform Click action by Class Name
	 * 
	 * @param str_Name
	 * @param Str_description
	 */
	public  void clickByName(String str_Name, String Str_description) {
		try {
			//waitTillElementIsClickable(str_Name);
			driver.findElementByName(str_Name).click();
			log.debug("Clicked on: " + Str_description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - " + Str_description);
			Assert.fail();
		}
	}
	
	/***
	 * Method to verify if a Web element exists on the screen
	 * @param Str_XpathOfElement
	 * @param Str_description
	 */
	public boolean exists(String Str_XpathOfElement, String Str_description) {
		try {
			waitTillElementPresent(Str_XpathOfElement);
			Boolean status = driver.findElement(By.xpath(Str_XpathOfElement)).isDisplayed();
			if (status) {
				log.debug("Validated that " + Str_description + " is displayed");
				return true;
			} else {
				log.debug(Str_description + " is not Displayed");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - Error while checking existence of element" + Str_description);
			return false;
		}
	}
	
	public void pressDownArrow() {
		log.debug("Pressing Down arrow key");
		utls.downArrow(driver);
	}
	
	public void pressUpArrow() {
		log.debug("Pressing Up arrow key");
		utls.upArrow(driver);
	}
	
	public void pressTabKey() {
		log.debug("Pressing Tab key");
		utls.tabKey(driver);
	}
	
	public void pressEnterKey() {
		log.debug("Pressing Enter key");
		utls.enterKey(driver);
	}
	
	/***
	 * Enhanced method to enter text.
	 * 
	 * @param str_ClassName
	 * @param str_Data
	 * @param Str_description
	 */
	public  void enterTextByClassName(String str_ClassName, String str_Data, String Str_description) {
		try {
			driver.findElementByClassName(str_ClassName).sendKeys(str_Data);
			log.debug(Str_description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - Error while entering text");
			Assert.fail();
		}
	}
	
	/***
	 * Enhanced method to enter text.
	 * 
	 * @param str_Name
	 * @param str_Data
	 * @param Str_description
	 */
	public  void enterTextByName(String str_Name, String str_Data, String Str_description) {
		try {
			driver.findElement(By.xpath(str_Name)).clear();
			driver.findElement(By.xpath(str_Name)).sendKeys(str_Data);
			log.debug(Str_description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - Error while entering text");
			Assert.fail();
		}
	}
	
	
	/***
	 * Method to wait until Web element becomes clickable in screen
	 * 
	 * @param Str_ElementXpath
	 */
	public void waitTillElementIsClickable(String Str_ElementXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Str_ElementXpath)));
			log.debug("Waited for clickability of: " + Str_ElementXpath);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - Element was not found to be clickable");
		}

	}
	
	/***
	 * Method to wait until Web element is present in screen
	 * 
	 * @param Str_ElementXpath
	 */
	public void waitTillElementPresent(String Str_ElementXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Str_ElementXpath)));
			log.debug("Waited for visibility of: " + Str_ElementXpath);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("*** Fail - Element was not present");
		}
	}
	
	public String getProperty(String property_key) {
		log.debug("Getting application property value: " + property_key);
		return utls.getAppProperty(property_key); 
	}
}
