package locadora;
import java.io.Serializable;
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1667921221556233086L;
	private String categoriaCNH;
	private boolean cadastrado = false;
	
	public Cliente(String nome, long cpf, long telefone, String catCNH, Cartao cartaoCredito) {
		super(nome, cpf, telefone);
		this.setCategoriaCNH(catCNH);
	}
	public Cliente() {
	}
	public String getCategoriaCNH() {
		return categoriaCNH;
	}
	public void setCategoriaCNH(String categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}
	public boolean isCadastrado() {
		return this.cadastrado;
	}
	public void setCadastrado(boolean is) {
		this.cadastrado= is;
	}
	@Override
	public String toString() {
		return super.toString()+", categoriaCNH: " + categoriaCNH ;
	}
}
