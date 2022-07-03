package locadora;

import java.io.Serializable;

public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private String placa;
	private String categoria;
	private int ano;
	private String marca;
	private String modelo;
	private String cor;
	private boolean disponivel;
	
	//Construtor
	public Veiculo(String placa, int ano, String marca, String modelo, String cor) {
		this.placa = placa;
		this.ano = ano;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.disponivel = true;
	}
	
	//Gets|Sets
	public boolean isDisponivel() {
		return this.disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Veiculo placa: " + placa + ", categoria: " + categoria + ", ano: " + ano + ", marca: " + marca
				+ ", modelo: " + modelo + ", cor: " + cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}
