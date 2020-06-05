package pageMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import base.Base;
import pageObjectsRepo.CommonObjects;

public class CommonMethods extends Base {

	CommonObjects cObj = new CommonObjects();

	private final Logger log = LogManager.getLogger(CommonMethods.class.getName());
	
	public String add(int firstNum, int secondNum) {
		log.info("Performing addition");
		clickByName(cObj.clear, "Clear");
		clickByName(getNumber(firstNum), "firstNum");
		clickByName(cObj.plus, "Plus");
		clickByName(getNumber(secondNum), "secondNum");
		clickByName(cObj.equals, "Equals");
		
		return getResult();
	}

	/***
	 * Method to get the result text
	 * @param resultFieldName
	 * @return
	 */
	public String getResult() {
		log.info("Getting result");
		return driver.findElement(By.xpath(cObj.result)).getText().replace("Display is", "").trim();	

	}
	
	public String getNumber(int intNumber) {
		String num = null;
		
		switch(intNumber) {
		case 0:
			num = cObj.num_0;
			break;
		case 1:
			num = cObj.num_1;
			break;
		case 2:
			num = cObj.num_2;
			break;
		case 3:
			num = cObj.num_3;
			break;
		case 4:
			num = cObj.num_4;
			break;
		case 5:
			num = cObj.num_5;
			break;
		case 6:
			num = cObj.num_6;
			break;
		case 7:
			num = cObj.num_7;
			break;
		case 8:
			num = cObj.num_8;
			break;
		case 9:
			num = cObj.num_9;
			break;
		}
		
		return num;
	}

}
