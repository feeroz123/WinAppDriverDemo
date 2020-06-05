package entrypoint;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pageMethods.LoginPageMethods;
import utilities.Utilities;

public class MainTest extends Base {
	
	/*
	 * Reference:
	 * https://www.automatetheplanet.com/automate-windows-desktop-apps-winappdriver/
	 */

	LoginPageMethods lpM = new LoginPageMethods();
	Utilities utls = new Utilities();

	@BeforeClass
	public void setup() throws IOException, InterruptedException {

		launchWinAppDriver();
		launchApplication();
	}

	@Test (priority = 0)
	public void loginTest() {
		Assert.assertTrue(lpM.login(), "Login into application failed");
	}

	@Test (priority = 1)
	public void firstTest() {
		driver.findElementByName("Clear").click();
		driver.findElementByName("Five").click();
		driver.findElementByName("Plus").click();
		driver.findElementByName("Six").click();
		driver.findElementByName("Equals").click();
		
		String result = driver.findElement(By.xpath("//*[@AutomationId='CalculatorResults']")).getText();
		Assert.assertEquals(result, "Display is 11");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		closeWinAppDriver();

	}

}
