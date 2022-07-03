package locadora;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1667921221556233086L;
	private int matricula;
	//Construtor da superclasse
	public Funcionario(String nome, long cpf, long telefone, int matricula) {
		super(nome, cpf, telefone);
		this.matricula = matricula;
	}
	//Get|Set
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	@Override
	public String toString() {
		return super.toString() +", matricula: " + matricula;
	}

}
