package locadora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

 public static void main(String[] args) {
		Locadora agencia = new Locadora();
		String nome;
		long cpf;
		long telefone;
		String categoria;
		String titular;
		long numCartao;
		int cvc;
		Cliente cliente = null;
		int control1 = 0;
		int control2 = 0;
		int validaEsc = 0;
		int esc = 0;
		
		
		while (control1 == 0) {
			while (validaEsc == 0) {
				esc = lerInt("1: Informar dados do cliente\n2: Usar dados pré definidos");
				if(esc > 0 && esc <=2) {
					validaEsc = 1;
					switch (esc) {
						case 1: {
							nome = lerString("Informe o nome do cliente.");
							cpf = lerLong("Informe o CPF do cliente.");
							telefone = lerLong("Informe o número de telefone do cliente.");
							categoria = lerString("Informe a categoria da CNH do cliente");
							System.out.println("Agora informe um Cartão de crédito para concluir o cadastro.");
							titular = lerString("Nome do titular: ");
							numCartao = lerLong("Número do cartão:");
							cvc = lerInt("Código de segurança: ");
							cliente = new Cliente(nome, cpf, telefone, categoria, new Cartao(titular, numCartao, cvc));
							break;
						}
						case 2: {
							cliente = new Cliente("Giovanni Lorenzo Gonçalves", 50752210092L, 84994413618L, "E", new Cartao("Giovanni L Gonçalves", 5178209867422687L, 893));
							break;
						}
					}
				}else System.out.println("Opção indisponível.");
			}
			esc = 0;
			validaEsc = 0;
			control2 = 0;
			while (control2 == 0) {
				while (validaEsc == 0) {
					esc = lerInt("\n1: Cadastrar Cliente\n2: Alugar veículo\n3: Cancelar contrato\n4: Consultar contratos\n5: Extender contrato\n6: Informar outro cliente\n7: Sair");
					if (esc >0 && esc <= 7) {
						validaEsc = 1;
						switch (esc) {
						case 1:{
							agencia.cadastrarCliente(cliente);
							break;
						}case 2:{
							//Funcionário da locadora, que está realizando o atendimento
							agencia.alugarAutomovel(cliente,  new Funcionario("Ângela Maria Medeiros", 15491588059L, 624687654L, 3643743));
							break;
						}case 3 :{
							agencia.cancelarContrato(cliente);
							break;
						}case 4:{
							agencia.consultarContratos(cliente);
							break;
						}case 5:{
							agencia.extenderContrato(cliente);
							break;
						}case 6:{
							control2 = 1;
							break;
						}case 7:{
							control2 = 1;
							control1 = 1;
							break;
						}
						
						}
					}else System.out.println("Opção indisponível.");
				}
				esc = 0;
				validaEsc = 0;
			}
		}
		
		
		
	}
	public static String lerString(String txt) {
		Scanner sc = new Scanner(System.in);
		System.out.println(txt);
		String texto = sc.nextLine();
		return texto;
	}
	public static int lerInt(String txt) {
		Scanner sc = new Scanner(System.in);
		int valor;
		while(true) {
			System.out.println(txt);
			try {
				valor = sc.nextInt();
				break;
			}catch (InputMismatchException e) {
				System.out.println("Informar apenas números inteiros, sem caracteres especiais.");
				sc.nextLine();
			}
		}
		return valor;
	}
	public static long lerLong(String txt) {
		Scanner sc = new Scanner(System.in);
		long valor;
		while(true) {
			System.out.println(txt);
			try {
				valor = sc.nextLong();
				break;
			}catch (InputMismatchException e) {
				System.out.println("Informar apenas números, sem caracteres especiais.");
				sc.nextLine();
			}
		}
		return valor;
	}	
}
