package testes;
import static org.junit.Assert.*;
import org.junit.*;
import locadora.*;
import java.io.*;

public class TesteLocadora {
	//Meus métodos são voids, então testei os estado das variáveis antes e depois da execução de alguns métodos
	@Test
	public void test1() {
		//Tentei usar um after pra apagar o arquivo antes de cada teste mas não funcionou
		Locadora t = new Locadora();
		File f = new File(t.pathClientes);
		f.delete();
		Cliente c1 = new Cliente("Luna Valentina Assunção", 93810352209L, 82992287925L, "AD", new Cartao("Luna Valentina", 5552433279379618L , 449));
		assertEquals(true, t.clientes.isEmpty());
		t.cadastrarCliente(c1);
		assertEquals(false, t.clientes.isEmpty());
	}
	@Test
	public void test2() {
		Locadora t = new Locadora();
		File f = new File(t.pathClientes);
		f.delete();
		assertEquals(0L,  t.arquivoClientes.length());
		Cliente c1 = new Cliente("Luna Valentina Assunção", 93810352209L, 82992287925L, "AD", new Cartao("Luna Valentina", 5552433279379618L , 449));
		t.cadastrarCliente(c1);
		assert(t.arquivoClientes.length() > 0L);
	}
}