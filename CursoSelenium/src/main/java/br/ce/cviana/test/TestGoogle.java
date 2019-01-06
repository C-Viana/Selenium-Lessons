package br.ce.cviana.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestGoogle {
		
	@Test
	public void test() {
		
		//System.setProperty("webdriver.gecko.driver","C:/WebDrivers/geckodriver.exe"); //Se o driver não estivesse no PATH do Windows, seria necessário utilizar este comando
		//System.setProperty("webdriver.chrome.driver","C:/WebDrivers/chromedriver.exe");
		//WebDriver driver = new InternetExplorerDriver(); //Desabilitar Modo Protegido no navegador
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(800, 1200));
		driver.manage().window().setPosition(new Point(0,0) );
		driver.get("http://www.google.com" );
		//System.out.println("Página aberta: " + driver.getTitle() );
		Assert.assertEquals("Google", driver.getTitle() );
		driver.quit();
	}
	
	
	
}
