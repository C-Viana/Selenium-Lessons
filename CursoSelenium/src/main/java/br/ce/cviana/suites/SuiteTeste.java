package br.ce.cviana.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.cviana.core.DriverFactory;
import br.ce.cviana.test.DesafioCadastro;
import br.ce.cviana.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ DesafioCadastro.class, TesteRegrasCadastro.class })
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	
}
