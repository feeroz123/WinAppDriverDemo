package pageMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.Base;
import pageObjectsRepo.LoginPageObj;

public class LoginPageMethods extends Base {

	LoginPageObj lpObj = new LoginPageObj();

	private static final Logger log = LogManager.getLogger(LoginPageMethods.class.getName());

	public boolean login() {
		log.info("Performing login into the application");

		try {
			clickByClassName(lpObj.siteDropdown_className, "Site selection dropdown button");
			pressDownArrow();
			pressEnterKey();
			clickByName(lpObj.siteLoginButton_Name, "Site Login button");
			
			pressTabKey();
			enterTextByClassName(lpObj.username_classname, getProperty("USERNAME"), "Entered user name");
			
			pressTabKey();
			enterTextByClassName(lpObj.password_classname, getProperty("PASSWORD"), "Entered password");
			clickByName(lpObj.credLoginButton_Name, "Login button");
			return true;
		} catch (Exception e) {
			log.error("Login failed");
			e.printStackTrace();
			return false;
		}

	}

}
