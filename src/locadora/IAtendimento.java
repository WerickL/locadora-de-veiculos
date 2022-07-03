package locadora;

import java.io.FileNotFoundException;

public interface IAtendimento {
	public void cadastrarCliente(Cliente cliente);
	public void alugarAutomovel(Cliente cliente, Funcionario atendente)throws FileNotFoundException;
	public void cancelarContrato(Cliente cliente)throws FileNotFoundException;
	public void consultarContratos(Cliente cliente);
	public void extenderContrato(Cliente cliente);
	
	
}
