package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;
import static br.ce.cviana.core.DriverFactory.killDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.cviana.core.DSL;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before
	public void start() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		dsl = new DSL();
	}
	
	//=================================================================
	
	@Test
	public void TesteTextField() {
		dsl.escreve("elementosForm:nome", "Joaquino");
		Assert.assertEquals("Joaquino", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void TesteTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Testando automação em TextArea");
		Assert.assertEquals("Testando automação em TextArea", dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	
	@Test
	public void TesteRadioSelection() {
		dsl.clicar("elementosForm:sexo:0");
		Assert.assertTrue( dsl.isRadioMarcado("elementosForm:sexo:0") );
	}
	
	
	@Test
	public void TesteCheckBox() {
		dsl.clicar("elementosForm:comidaFavorita:2");
		Assert.assertTrue( getDriver().findElement(By.id("elementosForm:comidaFavorita:2") ).isSelected() );
	}
	
	
	@Test
	public void TesteComboBox() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade") );
		
	}
	
	
	@Test
	public void TesteVarreduraComboBox() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	
	@Test
	public void TesteSelectMultiploComboBox() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getAllSelectedOptions();
		Assert.assertEquals(3, options.size());
	}
	
	
	@Test
	public void TesteDeselectMultiploComboBox() {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		combo.deselectByVisibleText("Corrida");
		
		List<WebElement> options = combo.getAllSelectedOptions();
		Assert.assertEquals(2, options.size());
	}
	
	
	@Test
	public void TesteSelecionarBotao() {
		
		dsl.clicarBotao("buttonSimple");
		WebElement element = getDriver().findElement(By.id("buttonSimple") );
		Assert.assertEquals("Obrigado!", element.getAttribute("Value") );
	}
	
	
	@Test
	public void TesteInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals( "Voltou!", dsl.obterTexto("resultado") );
	}
	
	
	@Test
	public void TesteBuscaTexto() {
		Assert.assertEquals( "Campo de Treinamento", dsl.obterTexto(By.tagName("h3")) );
		Assert.assertEquals( "Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")) );
	}
	
	@Test
	public void testJavascript() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("alert('Testando JS via Selenium')");
//		js.executeScript("document.getElementById('elementosForm:nome').value = 'Jacinto'");
//		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
//		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
//		js.executeScript("arguments[0].style.border = arguments[1]", element,"solid 4px red");
		
		dsl.executarJS("arguments[0].style.border = arguments[1]", element,"solid 4px red");
		
	}
	
	
	@Test
	public void deveClicarBotaoTabela() throws InterruptedException {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios"); Thread.sleep(3000);
	}
	
	
	//=================================================================
	
	@After
	public void finish() {
		killDriver();
	}
	
	
}
