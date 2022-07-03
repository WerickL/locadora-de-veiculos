package locadora;

import java.io.Serializable;

public class Moto extends Veiculo implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private double custo;
	//Construtor da superclasse
	public Moto(String placa, int ano, String marca, String modelo, String cor) {
		super(placa, ano, marca, modelo, cor);
		super.setCategoria("A");
		this.setCusto(200);
	}
	
	//Gets|Sets
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public String toString() {
		return "Moto - Diária R$" + getCusto() + ", Placa: " + getPlaca() + ", Categoria: " + getCategoria()
				+ ", Ano: " + getAno() + ", Marca: " + getMarca() + ", Modelo: " + getModelo();
	}
}
