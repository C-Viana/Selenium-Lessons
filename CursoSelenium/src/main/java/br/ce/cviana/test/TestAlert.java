package br.ce.cviana.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;


public class TestAlert {
	
	@Test
	public void TesteInteragirAlert() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		
		
		getDriver().findElement(By.id("alert")).click();
		Alert alert = getDriver().switchTo().alert();
		String alertMessage = alert.getText();
		Assert.assertEquals("Alert Simples", alertMessage);
		alert.accept();
		
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(alertMessage);
		
		killDriver();
		
	}
	
	
	@Test
	public void TesteInteragirAlertConfirm() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(800, 800));
		driver.manage().window().setPosition(new Point(0,0) );
		driver.get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		
		
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String alertMessage = alert.getText();
		Assert.assertEquals("Confirmado", alertMessage);
		alert.accept();
		
		driver.quit();
		
	}
	
	@Test
	public void TesteInteragirAlertCancel() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(800, 800));
		driver.manage().window().setPosition(new Point(0,0) );
		driver.get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		
		
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		String alertMessage = alert.getText();
		Assert.assertEquals("Negado", alertMessage);
		alert.accept();
		
		driver.quit();
		
	}
	
	@Test
	public void TesteInteragirPrompt() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(800, 800));
		driver.manage().window().setPosition(new Point(0,0) );
		driver.get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		
		alert.sendKeys("09");
		alert.accept();
		
		Assert.assertEquals("Era 09?", alert.getText());
		alert.accept();
		
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
		driver.quit();
		
	}
	
	
}
