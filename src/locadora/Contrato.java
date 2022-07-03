package locadora;
import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private LocalDate inicio;
	Cliente cliente;
	Funcionario atendente;
	Veiculo veiculo;
	private int tempoAluguel;
	LocalDate entrega;
	
	public Contrato(Cliente cliente, Funcionario atendente, Veiculo veiculo, int tempoAluguel) {
		this.cliente = cliente;
		this.atendente = atendente;
		this.veiculo = veiculo;
		this.tempoAluguel = tempoAluguel;
		this.inicio = LocalDate.now();
		dataEntrega();
	}
	private void dataEntrega() {
		this.entrega = inicio.plusDays(tempoAluguel);
	}
	public LocalDate getDataEntrega() {
		return this.entrega;
	}
	public void setDate(int dias) {
		entrega = entrega.plusDays(dias);
	}
	public String exibirContrato() {
		return ("Contrato de aluguel\n"+"O cliente: "+cliente.toString())+ "\nEfetuou o aluguel do ve�culo: "
	+veiculo.toString()+"\nno dia: "+inicio+", com data de devolu��o em: "+getDataEntrega()+".\nEfetivado pelo Funcion�rio: "+atendente.toString();
	}
}
