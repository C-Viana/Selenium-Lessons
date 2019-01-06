package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.cviana.core.DSL;

public class challengeOne {
	
	private DSL dsl;
	
	@Before
	public void initTest() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		dsl = new DSL();
	}
	
	@Test
	public void rewriteName() {
		dsl.escreve("elementosForm:nome", "Nome 1" ); 
		Assert.assertEquals("Nome 1", dsl.obterValorCampo("elementosForm:nome") );
		dsl.escreve("elementosForm:nome", "Nome 2" ); 
		Assert.assertEquals("Nome 2", dsl.obterValorCampo("elementosForm:nome") );
	}
	
	
	@After
	public void finishTest() {
		killDriver();
	}
	
	
}
