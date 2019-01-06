package br.ce.cviana.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.cviana.core.DSL;

import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

public class TestPrime {
	
	
	private DSL dsl;
	
	@Before
	public void start() {
		dsl = new DSL();
	}
	
	//=================================================================
	
	@After
	public void finish() {
		killDriver();
	}
	
	//=================================================================
	
	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get( "https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml" );
		//Por tag
		dsl.clicar(By.xpath("//input[@id='j_idt636:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt636:console:0"));
		//Por valor
		dsl.clicar(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt636:console:1"));
		
	}
	
	
	@Test
	public void desafioCombo() {
		getDriver().get( "https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml" );
		dsl.selecionarCombo(By.xpath("//div[@id='j_idt636:console']//select[@id='j_idt636:console_input']"), "PS4"); 
		dsl.obterValorCampo("j_idt636:console_input");
	}
	
	
	//=================================================================
	
	
	
	
	
}
