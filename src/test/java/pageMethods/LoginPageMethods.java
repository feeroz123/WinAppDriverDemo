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
			
			// STEPS TO LOGIN IN TO APPLICATION
			System.out.println("Logged into application");
			
			return true;
		} catch (Exception e) {
			log.error("Login failed");
			e.printStackTrace();
			return false;
		}

	}

}
