package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.cviana.core.BaseTest;
import br.ce.cviana.core.DSL;
import br.ce.cviana.page.CampoTreinamentoPage;

public class DesafioCadastro extends BaseTest {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void start() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		page = new CampoTreinamentoPage();
		dsl = new DSL();
	}
	
	//=================================================================
	
	@Test
	public void realizarCadastro1() {
		
		page.setNome("Cabresto"); //dsl.escreve("elementosForm:nome","Cabresto");
		page.setSobrenome("Macarroni"); //dsl.escreve("elementosForm:sobrenome","Macarroni");
		
		page.setSexoMasculino(); //dsl.clicar("elementosForm:sexo:0");
		
		page.setComidaFrango(); //dsl.clicar("elementosForm:comidaFavorita:1");
		page.setComidaPizza(); //dsl.clicar("elementosForm:comidaFavorita:2");
		
		page.setEscolaridade("2o grau completo"); //dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		
		//Fazendo o select em uma única linha
//		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
//		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Karate");
		page.setEsporte("Corrida"); //dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		page.setEsporte("Karate"); //dsl.selecionarCombo("elementosForm:esportes", "Karate");
		
		page.cadastrar(); //dsl.clicar("elementosForm:cadastrar");
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastrado() ); //Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).findElement(By.tagName("span")).getText() );
		
		Assert.assertEquals("Cabresto", page.obterNomeCadastrado() ); //Assert.assertEquals("Cabresto", driver.findElement(By.id("descNome")).findElement(By.tagName("span")).getText());
		
		Assert.assertEquals("Macarroni", page.obterSobrenomeCadastrado() ); //driver.findElement(By.id("descSobrenome")).findElement(By.tagName("span")).getText()
		Assert.assertEquals("Masculino", page.obterSexoCadastrado() ); //driver.findElement(By.id("descSexo")).findElement(By.tagName("span")).getText()
		Assert.assertEquals("Frango Pizza", page.obterComidaCadastrado() ); //driver.findElement(By.id("descComida")).findElement(By.tagName("span")).getText()
		Assert.assertEquals("2graucomp", page.obterEscolaridadeCadastrado() ); //driver.findElement(By.id("descEscolaridade")).findElement(By.tagName("span")).getText()
		Assert.assertEquals("Corrida Karate", page.obterEsporteCadastrado() ); //driver.findElement(By.id("descEsportes")).findElement(By.tagName("span")).getText()
		Assert.assertEquals("", page.obterSugestoesCadastrado() ); //driver.findElement(By.id("descSugestoes")).findElement(By.tagName("span")).getText()
		
	}
	
	@Test
	public void validarNomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObeterTextoEAceita() );
	}
	
	@Test
	public void validarSobrenomeObrigatorio() {
		page.setNome("Fulano");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObeterTextoEAceita() );
	}
	
	@Test
	public void validarSexoObrigatorio() {
		page.setNome("Fulano");
		page.setSobrenome("Pereira");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObeterTextoEAceita() );
	}
	
	@Test
	public void validarComidaVegetariana() {
		page.setNome("Fulano");
		page.setSobrenome("Pereira");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariana();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObeterTextoEAceita() );
	}
	
	@Test
	public void validarPraticaEsporte() {
		page.setNome("Fulano");
		page.setSobrenome("Pereira");
		page.setSexoFeminino();
		page.setComidaCarne();
		
		page.setEsporte("Corrida","O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObeterTextoEAceita() );
	}
	
	
}
