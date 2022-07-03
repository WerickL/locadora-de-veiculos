package locadora;

import java.io.Serializable;

public class Van extends Veiculo implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private static double custo;
	public Van(String placa, int ano, String marca, String modelo, String cor) {
		super(placa, ano, marca, modelo, cor);
		this.setCategoria("D-E"); 
		this.setCusto(500);
		
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	@Override
	public String toString() {
		return "Van - Diária R$" + getCusto() + ", Placa: " + getPlaca() + ", Categoria: " + getCategoria()
				+ ", Ano: " + getAno() + ", Marca: " + getMarca() + ", Modelo: " + getModelo();
	}
}
