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

public class EnterpriseMonitorTest extends Base {

	LoginPageMethods lpM = new LoginPageMethods();
	Utilities utls = new Utilities();

	@BeforeClass
	public void setup() throws IOException, InterruptedException {

		launchWinAppDriver();
		launchApplication();
	}

	@Test (priority = 0, enabled = true)
	public void loginTest() {
		Assert.assertTrue(lpM.login(), "Login into applicaiton failed");
	}

	@Test (priority = 1, enabled=true)
	public void firstTest() {

		driver.findElementByClassName("Button").click();
		driver.findElementByName("Dashboard").click();
		driver.findElement(By.xpath("//*[@AutomationId='MaximizeButton']")).click();

		WebElement TwoxSix = driver.findElementByName("2x6");
		Assert.assertEquals(TwoxSix.isDisplayed(), true);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		closeWinAppDriver();

	}

}
