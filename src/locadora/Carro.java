package locadora;

import java.io.Serializable;

public class Carro extends Veiculo implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private double custo;
	//Construtor da superclasse
	public Carro(String placa, int ano, String marca, String modelo, String cor) {
		super(placa, ano, marca, modelo, cor);
		this.setCategoria("B-C-D-E");
		this.setCusto(300);
	}
	//Get|Set
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public String toString() {
		return "Carro - Diária R$" + getCusto() + ", Placa: " + getPlaca() + ", Categoria: " + getCategoria()
				+ ", Ano: " + getAno() + ", Marca: " + getMarca() + ", Modelo: " + getModelo();
	}
}
