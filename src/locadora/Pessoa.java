package locadora;

import java.io.Serializable;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1667921221556233086L;
	private String nome;
	private long cpf;
	private long telefone;
	
	//Construtor
	public Pessoa(String nome, long cpf, long telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	public Pessoa() {
		
	}
	
	
	//Gets|Sets
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}


	@Override
	public String toString() {
		return nome + ", cpf: " + cpf + ", telefone: " + telefone;
	}
}
