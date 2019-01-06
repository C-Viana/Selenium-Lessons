package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class ChallengeSearchByXPath {
	
	
	@Before
	public void start() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
	}
	
	//=================================================================
	
	@After
	public void finish() {
		killDriver();
	}
	
	//=================================================================
	
	@Test
	public void typeNameInput() {
		
		getDriver().findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/input[@id='elementosForm:nome']")).sendKeys("NAMELESS");
		Assert.assertEquals("NAMELESS", getDriver().findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).getAttribute("value") );
		
	}
	
	@Test
	public void selectFemaleGenderRadio() {
		
		getDriver().findElement( By.xpath("//input[@type='radio' and @value='F']")).click();
		Assert.assertTrue( getDriver().findElement(By.xpath("//*[@id=\"elementosForm:sexo:1\"]")).isSelected() );
		
	}
	
	@Test
	public void selectPizzaCheckbox() {
		
		getDriver().findElement( By.xpath("//*[@id='elementosForm:comidaFavorita:2']")).click();
		Assert.assertTrue( getDriver().findElement(By.xpath("//*[@id='elementosForm:comidaFavorita:2']")).isSelected() );
		
	}
	
	@Test
	public void searchUserB() {
		
		Assert.assertEquals("Usuario B", getDriver().findElement(By.xpath("//table[@id='tabelaSemJSF']/tbody/tr/td[contains(text(),'Usuario B')]")).getText() );
		
	}
	
	@Test
	public void clickButtonMaria() {
		
		getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']/tbody//td[.='Maria']/..//input[@type='button']")).click() ;
		Alert alert = getDriver().switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		
		Assert.assertEquals("Maria", alertMessage );
		
	}
	
	@Test
	public void clickRadioPhdPhd() {
		
		getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']//td[1][.='Doutorado']/..//td[2][.='Doutorado']/..//input[@type='radio']")).click() ;
		
		Assert.assertTrue( getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']//td[1][.='Doutorado']/..//td[2][.='Doutorado']/..//input[@type='radio']")).isSelected() );
		
	}
	
	@Test
	public void inputSecondUserGraduation() {
		
		getDriver().findElement(By.xpath("(//*[@id='elementosForm:tableUsuarios']//td[2][.='Superior'])[2]/..//input[@type='text']")).sendKeys("SOMETHING") ;
		
		Assert.assertEquals("SOMETHING", getDriver().findElement(By.xpath("(//*[@id='elementosForm:tableUsuarios']//td[2][.='Superior'])[2]/..//input[@type='text']")).getAttribute("value") );
		
	}
	
	
	
}
