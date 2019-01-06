package br.ce.cviana.test;
import static br.ce.cviana.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.cviana.core.BaseTest;
import br.ce.cviana.core.DSL;
import br.ce.cviana.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	//=================================================================
	
	@Parameter(value=0) public String nome;
	@Parameter(value=1) public String sobrenome;
	@Parameter(value=2) public String sexo;
	@Parameter(value=3) public List<String> comidas;
	@Parameter(value=4) public String[] esportes;
	@Parameter(value=5) public String msg;
	
	//=================================================================
	
	@Before
	public void start() {
		getDriver().get( "file:///" + System.getProperty("user.dir") + "/src/main/resources/Campo de treinamento/componentes.html" );
		page = new CampoTreinamentoPage();
		dsl = new DSL();
	}
	
	
	//=================================================================
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] { 
			{"", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio"},
			{"Fulano", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
			{"Fulano", "Silveiro", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
			{"Fulano", "Silveiro", "masculino", Arrays.asList("Carne","Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Fulano", "Silveiro", "masculino", Arrays.asList("Carne"), new String[] {"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?"}
		} );
	}
	
	
	@Test
	public void validarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino") || sexo.equals("masculino") || sexo.equals("Masc") || sexo.equals("m") || sexo.equals("M") )
			page.setSexoMasculino();
		else if (sexo.equals("Feminino") || sexo.equals("feminino") || sexo.equals("Femi") || sexo.equals("f") || sexo.equals("F") )
			page.setSexoFeminino();
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariana();
		
		page.setEsporte(esportes);
		page.cadastrar();
		Assert.assertEquals(msg, dsl.alertaObeterTextoEAceita() );
	}
	
	
	
	
	
	
	
}
