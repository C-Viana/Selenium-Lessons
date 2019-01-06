package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.cviana.core.DSL;

public class TesteSincronismo {
	
	private DSL dsl;
	
	@Before
	public void start() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		dsl = new DSL();
	}
	

	//=================================================================
	
	@After
	public void finish() {
		killDriver();
	}
	
	//=================================================================

	@Test
	public void interagirComEsperaImplicita() {
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		dsl.clicar("buttonDelay");
		dsl.escreve("novoCampo", "Texto escrito pelo Selenium");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void interagirComEsperaExplicita() {
		
		dsl.clicar("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")) );
		dsl.escreve("novoCampo", "Texto escrito pelo Selenium");
		
	}
	
	
}
