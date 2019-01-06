package br.ce.cviana.test;

import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.cviana.core.DSL;

public class TesteAjax {

	private DSL dsl;

	@Before
	public void start() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}

	// =================================================================

	@After
	public void finish() {
		killDriver();
	}

	// =================================================================

	@Test
	public void interagirComEsperaImplicita() {

		dsl.escreve("j_idt635:name", "ESCRITO!");
		dsl.clicar("j_idt635:j_idt638");
		WebDriverWait wait = new WebDriverWait(getDriver(), 25);
		// wait.until( ExpectedConditions.textToBe(By.id("j_idt635:display"),
		// "ESCRITO!") );
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt649_start")));
		Assert.assertEquals("ESCRITO!", dsl.obterTexto("j_idt635:display"));

	}
}
