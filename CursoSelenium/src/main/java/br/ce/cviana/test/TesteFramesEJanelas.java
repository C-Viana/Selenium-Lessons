package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TesteFramesEJanelas {
	

	@Before
	public void start() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html");
	}
	
	//=================================================================
	
	@After
	public void finish() {
		killDriver();
	}
	
	//=================================================================
	
	
	@Test
	public void interagirComFrameEscondido() {
		
		WebElement element = getDriver().findElement(By.id("frame2"));
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollBy(0,arguments[0])", element.getLocation().y );
		
		getDriver().switchTo().frame("frame2");
		
		
		getDriver().findElement(By.id("frameButton")).click();
		
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg );
		alert.accept();
	}
	
	
	@Test
	public void TestFrame() {
		
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg );
		alert.accept();
		
		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	
	@Test
	public void TestJanelaComTitulo() {
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("There you go");
		getDriver().close();
		
		getDriver().switchTo().window( (String)getDriver().getWindowHandles().toArray()[0] ); 
		getDriver().findElement(By.tagName("textarea")).sendKeys("There you go again"); 
	}
	
	
	@Test
	public void TestJanelaSemTitulo() {
		getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window( (String)getDriver().getWindowHandles().toArray()[1] );
		
		getDriver().findElement(By.tagName("textarea")).sendKeys("Testing once");
		
		getDriver().switchTo().window( (String)getDriver().getWindowHandles().toArray()[0] );
		getDriver().findElement(By.tagName("textarea")).sendKeys("Testing once again");
	}
	
	
}
