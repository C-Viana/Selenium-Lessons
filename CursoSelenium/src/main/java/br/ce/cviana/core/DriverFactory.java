package br.ce.cviana.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverFactory {
	
	private static WebDriver driver;
	
	
	private DriverFactory() {
		
	}
	
	
	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Propriedades.browser) {
			case CHROME:
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case EXPLORER:
				driver = new InternetExplorerDriver();
				break;
			}
			driver.manage().window().setSize(new Dimension(1200, 800));
			driver.manage().window().setPosition(new Point(0,0));
		}
		
		return driver;
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	
	
}
