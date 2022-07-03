package locadora;

import java.io.Serializable;

public class Cartao implements Serializable{
	private static final long serialVersionUID = 1667921221556233086L;
	private String nomeTitular;
	private long numero;
	private int cvc;
	public Cartao(String titular, long numero, int cvc) {
		this.nomeTitular = titular;
		this.numero = numero;
		this.cvc = cvc;
	}
}
