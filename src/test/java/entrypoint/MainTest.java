package entrypoint;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pageMethods.CommonMethods;
import pageObjectsRepo.CommonObjects;
import utilities.Utilities;

public class MainTest extends Base {
	
	/*
	 * Reference:
	 * https://www.automatetheplanet.com/automate-windows-desktop-apps-winappdriver/
	 */
	
	CommonObjects cObj= new CommonObjects();
	CommonMethods cM = new CommonMethods();	
	Utilities utls = new Utilities();

	@BeforeClass
	public void setup() throws IOException, InterruptedException {

		launchWinAppDriver();
		launchApplication();
	}

	@Test (priority = 0)
	public void additionTest() {		
		String expectedResult = "11";
		String actualResult = cM.add(5,6);
		
		Assert.assertEquals(actualResult, expectedResult);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		closeWinAppDriver();

	}

}
