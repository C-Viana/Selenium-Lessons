package br.ce.cviana.core;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static br.ce.cviana.core.DriverFactory.getDriver;

public class DSL {
	
	
	public void escreve(String id, String texto) {
		getDriver().findElement(By.id(id)).clear();
		getDriver().findElement(By.id(id)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	public void clicar(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicar(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(By by, String valor) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void selecionarCombo(String id, String valor) {
		selecionarCombo(By.id(id), valor);
	}
	
	public String obterValorCombo(By by) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public String obterValorCombo(String id) {
		return obterValorCombo(By.id(id));
	}
	
	public void clicarBotao(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	
	public String alertaObeterTextoEAceita() {
		Alert alert = getDriver().switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		return alertMessage;
	}
	
	
	
	
	public int obterIndiceColuna(String coluna, WebElement tabela) {
		int idColuna = -1;
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		for(int i=0; i<colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}
	
	
	public int obterIndiceLinha(String linha, WebElement tabela, int idColuna) {
		int idLinha = -1;
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]")); // //*[@id="elementosForm:tableUsuarios"]/tbody/tr[2]
		for(int i=0; i<linhas.size(); i++) {
			if(linhas.get(i).getText().equals(linha)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}
	
	
	public void clicarBotaoTabela(String colunaBusca, String linhaBusca, String colunaBotao, String idTabela) {
		
		
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']")); // //*[@id='elementosForm:tableUsuarios']
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		int idLinha = obterIndiceLinha(linhaBusca, tabela, idColuna);
		
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]") );
		celula.findElement(By.xpath(".//input")).click();
		
	}
	
	
	
	//******************* JS *****************************
	public Object executarJS(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		return js.executeScript(cmd,params);
	}
	
	
	
}
