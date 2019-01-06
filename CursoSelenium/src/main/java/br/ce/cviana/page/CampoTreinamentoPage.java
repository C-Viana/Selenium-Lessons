package br.ce.cviana.page;
import org.openqa.selenium.By;

import br.ce.cviana.core.BasePage;

public class CampoTreinamentoPage extends BasePage {
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicar("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicar("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicar("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicar("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicar("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariana() {
		dsl.clicar("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String escolaridade) {
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
	}
	
	public void setEsporte(String... esportes) {
		for(String valor: esportes) {
			dsl.selecionarCombo("elementosForm:esportes", valor);
		}
		
	}
	
	public void cadastrar() {
		dsl.clicar("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='resultado']/span") );
	}
	
	public String obterNomeCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descNome']/span") );
	}
	
	public String obterSobrenomeCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descSobrenome']/span") );
	}
	
	public String obterSexoCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descSexo']/span") );
	}
	
	public String obterComidaCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descComida']/span") );
	}
	
	public String obterEscolaridadeCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descEscolaridade']/span") );
	}
	
	public String obterEsporteCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descEsportes']/span") );
	}
	
	public String obterSugestoesCadastrado() {
		return dsl.obterTexto( By.xpath(".//*[@id='descSugestoes']/span") );
	}		
	
}
