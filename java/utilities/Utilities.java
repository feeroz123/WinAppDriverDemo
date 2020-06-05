package utilities;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.windows.WindowsDriver;

public class Utilities {
	
	public Desktop desktop;
	private final static Logger log = LogManager.getLogger(Utilities.class.getName());
	
	/***
	 * Method to start WinAppDriver server
	 */
	public void startWinAppDriverServer() {
		if (Desktop.isDesktopSupported()) {
			String driverPath = getAppProperty("DRIVER_PATH");
			
			try {
				desktop = Desktop.getDesktop();		
				desktop.open(new File(driverPath));
				log.debug("WinAppDriver.exe was started successfully");
			} catch (IOException e) {
				log.error("WinAppDriver.exe could not be started");
				e.printStackTrace();
			}
		}
		else {
			log.error("Desktop class not supported on this platform");
		}
	}
	
	public void stopWinAppDriverServer() {
		try {
			Runtime.getRuntime().exec("TASKKILL /F /IM WinAppDriver.exe");
			System.exit(0);
			log.debug("WinAppDriver.exe was killed");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("WinAppDriver.exe could not be killed");
		}
		
	}
	
	public void downArrow(WindowsDriver<?> driver) {
		try {
			Thread.sleep(500);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			log.debug("Pressed Down arrow key");
		} catch (Exception e) {
			log.error("Down arrow key could not be pressed");
			e.printStackTrace();
		}
	}

	public void upArrow(WindowsDriver<?> driver) {
		try {
			Thread.sleep(500);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_UP);
			r.keyRelease(KeyEvent.VK_UP);
			log.debug("Pressed Down arrow key");
		} catch (Exception e) {
			log.error("Up arrow key could not be pressed");
			e.printStackTrace();
		}
	}
	
	public void enterKey(WindowsDriver<?> driver) {
		try {
			Thread.sleep(500);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			log.debug("Pressed Enter key");
		} catch (Exception e) {
			log.error("Enter key could not be pressed");
			e.printStackTrace();
		}
	}
	
	public void tabKey(WindowsDriver<?> driver) {
		try {
			Thread.sleep(500);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			log.debug("Pressed Tab key");
		} catch (Exception e) {
			log.error("Tab key could not be pressed");
			e.printStackTrace();
		}
	}
	
	/***
	 * Method to fetch the value of passed key from application.properties file
	 * @param propertyKey
	 * @return
	 */
	public String getAppProperty(String propertyKey) {
		Properties props = new Properties();

		try {
			InputStream appPropFile = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/configs/application.properties");
			props.load(appPropFile);
			log.debug("The 'application.properties' file was loaded");
		} catch (IOException e) {
			log.error("*** The 'application.properties' file was not found");
			e.printStackTrace();
		}
		String result = props.getProperty(propertyKey);
		log.debug("Retrieved the value of "+ propertyKey +" from 'application.properties' file");
		return String.valueOf(result);
	}

}
