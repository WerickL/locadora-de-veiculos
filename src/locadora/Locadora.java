package locadora;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Locadora implements IAtendimento {
	// todos os caminhos destinados a clientes têm como referência essa variável!
	public static String pathClientes = "C:\\Users\\NOTEBOOK\\Desktop\\clientes.dat";
	// todos os caminhos destinados a contratos têm como referência essa variável!
	public static String pathContratos = "C:\\Users\\NOTEBOOK\\Desktop\\contratos.dat";
	// todos os caminhos destinados a veículos têm como referência essa variável!
	public static String pathVeiculos = "C:\\Users\\NOTEBOOK\\Desktop\\veiculos.dat";
	public ArrayList<Object> veiculos = new ArrayList<Object>();
	public ArrayList<Object> clientes = new ArrayList<Object>();
	public ArrayList<Object> contratos = new ArrayList<Object>();
	public File arquivoClientes = new File(pathClientes);
	public File arquivoVeiculos = new File(pathVeiculos);
	public File arquivoContratos = new File(pathContratos);
	private Scanner sc = new Scanner(System.in);

	// Métodos principais
	@Override
	public void cadastrarCliente(Cliente cliente) {
		veiculos.clear();
		clientes.clear();
		contratos.clear();
		String esc;
		//Verifica o cadastro
		try {
			if (!arquivoClientes.isAbsolute() | !arquivoVeiculos.isAbsolute() | !arquivoContratos.isAbsolute()) {
				throw new PathIndisponivel("Atualize as variáveis de path na classe Locadora");
		}else {
			while (true) {
				try {
					clientes = abreArquivo(pathClientes);
					break;
				} catch (FileNotFoundException e) {
					criarArquivo(pathClientes);
					try {
						clientes = abreArquivo(pathClientes);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (Object cadastrado : clientes) {
				if (((Pessoa) cadastrado).getCpf() == cliente.getCpf()) {
					cliente.setCadastrado(true);
					System.out.println("\nO cliente já é cadastrado.");
					while (true) {
						System.out.println("Deseja atualizar o cadastro? S/N");
						try {
							esc = sc.nextLine();
							if (esc.toUpperCase().intern() == "S") {
								int indice = clientes.indexOf((Object)(cadastrado));
								clientes.set(indice, (Object)cliente);
								try {
									atualizarArquivo(clientes, pathClientes);
									System.out.println("Cadastro atualizado!");
									break;
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							} else if (esc.toUpperCase().intern() == "N"){
								break;
							}else {
								System.out.println("Certique-se de escolher uma opção válida.");
							}
						} catch (InputMismatchException tipo) {
							System.out.println("Certifique-se de informar um valor inteiro");
							sc.nextLine();
						}
					}	
					//break;
				}
			}
			if (cliente.isCadastrado()) {
			} else {
				cliente.setCadastrado(true);
				clientes.add(cliente);
				try {
					atualizarArquivo(clientes, pathClientes);
					System.out.println("Cadastro concluido!");
	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			}catch(PathIndisponivel e) {
			e.printStackTrace();
		}
	}

	// Método de aluguel
	@Override
	public void alugarAutomovel(Cliente cliente, Funcionario atendente) {
		veiculos.clear();
		clientes.clear();
		contratos.clear();
		int esc = -1;
		int days;
		Contrato contrato;
		//verifica se o caminho do arquivo existe
		try {
			if (!arquivoClientes.isAbsolute() | !arquivoVeiculos.isAbsolute() | !arquivoContratos.isAbsolute()) {
				throw new PathIndisponivel("Atualize as variáveis de path na classe Locadora");
		}else {
			//Verifica o cadastro
			while (true) {
				try {
					clientes = abreArquivo(pathClientes);
					break;
				} catch (FileNotFoundException e) {
					criarArquivo(pathClientes);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (Object cadastrado : clientes) {
				if (((Pessoa) cadastrado).getCpf() == ((Pessoa) cliente).getCpf()) {
					cliente.setCadastrado(true);
					break;
				}
			}
			//Só efetua ação se o cliente estiver cadastrdo
			if (cliente.isCadastrado()) {
				while (true) {
					try {
						veiculos = abreArquivo(pathVeiculos);
						break;
					} catch (FileNotFoundException e) {
						listaVeiculos();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				// Mostar uma lista de automóveis, verificando se tá disponível
				int i = 0;
				for (Object veiculo : veiculos) {
					if (((Veiculo) veiculo).isDisponivel()) {
						if (veiculo instanceof Carro)
							System.out.println(i + "-" + ((Carro) veiculo).toString());
						else if (veiculo instanceof Moto)
							System.out.println(i + "-" + ((Moto) veiculo).toString());
						else if (veiculo instanceof Van)
							System.out.println(i + "-" + ((Van) veiculo).toString());
					} else
						System.out.println(i + ": Veículo indisponível!");
					i++;
				}
				while (true) {
					// Perguntar a escolha do usuário
					while (true) {
						while (true) {
							System.out.println("\nEscolha o veículo desejado.");
							try {
								esc = sc.nextInt();
								if (esc > -1 & esc < i) {
									if (!((Veiculo) veiculos.get(esc)).isDisponivel()) {
										System.out.println("Veículo indisponível.");
									} else
										break;
								} else
									System.out.println("Certique-se de escolher uma opção válida.");
							} catch (InputMismatchException tipo) {
								System.out.println("Certifique-se de informar um valor inteiro");
								sc.nextLine();
							}
						}
						break;
					}
					// verificar se a certeira atende ao requisito
					int tipo2 =cliente.getCategoriaCNH().length();
					if (((Veiculo) veiculos.get(esc)).getCategoria().contains(String.valueOf(cliente.getCategoriaCNH().charAt(0))) || ((Veiculo)veiculos.get(esc)).getCategoria().contains(String.valueOf(tipo2-1))) {
						// Atualiza os status
						((Veiculo) veiculos.get(esc)).setDisponivel(false);
						// perguntar quantos dias vai ficar alugado
						while (true) {
							System.out.println("\nPor quantos dias deseja permanecer com o veículo?");
							try {
								days = sc.nextInt();
								if (days > 0) {
									break;
								}else System.out.println("Tempo mínimo: 1 dia.");
							} catch (InputMismatchException tipo) {
								System.out.println("Certifique-se de informar um valor inteiro, em dias.");
								sc.nextLine();
							}
						}
						try {
							atualizarArquivo(veiculos, pathVeiculos);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						contrato = new Contrato(cliente, atendente, ((Veiculo) veiculos.get(esc)), days);
		
						while (true) {
							try {
								contratos = abreArquivo(pathContratos);
								break;
							} catch (FileNotFoundException e) {
								criarArquivo(pathContratos);
							} catch (IOException e) {
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						contratos.add(contrato);
						try {
							atualizarArquivo(contratos, pathContratos);
							System.out.println("Contrato emitido!");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					} else
						System.out.println("A categoria de CNH informada não permite dirigir este veículo!");
				}
				System.out.println(contrato.exibirContrato());
			}else System.out.println("Cliente não cadastrado!");
		}
		}catch(PathIndisponivel e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cancelarContrato(Cliente cliente) {
		veiculos.clear();
		clientes.clear();
		contratos.clear();
		//Verifica o cadastro
		File arquivoClientes = new File(pathClientes);
		File arquivoVeiculos = new File(pathVeiculos);
		File arquivoContratos = new File(pathContratos);
		//verifica se o caminho do arquivo existe
		try {
			if (!arquivoClientes.isAbsolute() | !arquivoVeiculos.isAbsolute() | !arquivoContratos.isAbsolute()) {
				throw new PathIndisponivel("Atualize as variáveis de path na classe Locadora");
		}else {
			while (true) {
				try {
					clientes = abreArquivo(pathClientes);
					break;
				} catch (FileNotFoundException e) {
					criarArquivo(pathClientes);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (Object cadastrado : clientes) {
				if (((Pessoa) cadastrado).getCpf() == ((Pessoa) cliente).getCpf()) {
					cliente.setCadastrado(true);
					break;
				}
			}
			if (cliente.isCadastrado()) {
				// Recupera os contratos
				while (true) {
					try {
						contratos = abreArquivo(pathContratos);
						break;
					} catch (FileNotFoundException e) {
						criarArquivo(pathContratos);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				// Encontra os contratos vinculados ao cliente
				int valida = 0;
				ArrayList<Contrato> contratosCliente = new ArrayList<Contrato>();
				System.out.println("Contratos vinculados ao cliente: " + cliente.getNome());
				for (Object contrato : contratos) {
					if (((Contrato) contrato).cliente.getCpf() == cliente.getCpf()) {
						valida++;
						contratosCliente.add((Contrato) contrato);
		
					}
				}
				if (valida == 0) {
					System.out.println("Não há contratos ativos vinculados a este cliente.");
				} else {
					// Mostra os contratos vinculados ao cliente
					int i = 0;
					for (Contrato doCliente : contratosCliente) {
						i++;
						System.out.println("\n" + i + "-" + doCliente.exibirContrato());
					}
					// Recebe a escolha do usuário
					int esc;
					int indice;
					while (true) {
						while (true) {
							System.out.println("\nQual contrato deseja cancelar?");
							try {
								esc = sc.nextInt();
								if (esc > 0 && esc <= i) {
									
										indice = contratos.indexOf((Object)(contratosCliente.get(esc - 1)));
										contratos.remove(indice);							
										try {
											atualizarArquivo(contratos, pathContratos);
										} catch (IOException e) {
											e.printStackTrace();
										}
										// Recupera os veículos
										while (true) {
											try {
												veiculos = abreArquivo(pathVeiculos);
												break;
											} catch (FileNotFoundException e) {
												listaVeiculos();
											} catch (IOException e) {
												e.printStackTrace();
											} catch (ClassNotFoundException e) {
												e.printStackTrace();
											}
										}
										// Muda o status do veículo para disponível
										int ind = 0;
		
										while (true) {
											try {
												if (((Veiculo)veiculos.get(ind)).getPlaca().intern() == contratosCliente.get(esc - 1).veiculo.getPlaca().intern()) {
													((Veiculo)veiculos.get(ind)).setDisponivel(true);
													break;
												} else
													ind++;
		
											} catch (IndexOutOfBoundsException fora) {
												System.out.println("A placa do veículo não foi encontrada.");
											}
										}
										// Atualiza os arquivos
										try {
											atualizarArquivo(veiculos, pathVeiculos);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										break;
								} else
									System.out.println("Certique-se de escolher uma opção válida.");
							} catch (InputMismatchException tipo) {
								System.out.println("Certifique-se de informar um valor inteiro");
								sc.nextLine();
							}
						}
						break;
					}
					System.out.println("\nContrato cancelado com sucesso.");
				}
			}else System.out.println("\nCliente não cadastrado!");
		}
			}catch(PathIndisponivel e) {
			e.printStackTrace();
		}
	}

	@Override
	public void consultarContratos(Cliente cliente) {
		veiculos.clear();
		clientes.clear();
		contratos.clear();
		int valida = 0;
		File arquivoClientes = new File(pathClientes);
		File arquivoContratos = new File(pathContratos);
		//verifica se o caminho do arquivo existe
		try {
			if (!arquivoClientes.isAbsolute() | !arquivoVeiculos.isAbsolute() | !arquivoContratos.isAbsolute()) {
				throw new PathIndisponivel("Atualize as variáveis de path na classe Locadora");
		}else {
			//Verifica o cadastro
			while (true) {
				try {
					clientes = abreArquivo(pathClientes);
					break;
				} catch (FileNotFoundException e) {
					criarArquivo(pathClientes);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (Object cadastrado : clientes) {
				if (((Pessoa) cadastrado).getCpf() == ((Pessoa) cliente).getCpf()) {
					cliente.setCadastrado(true);
					break;
				}
			}
			if (cliente.isCadastrado()) {
				while (true) {
					try {
						contratos = abreArquivo(pathContratos);
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Não existem contratos disponíveis");
						criarArquivo(pathContratos);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				// Encontra os contratos vinculados ao cliente
				ArrayList<Contrato> contratosCliente = new ArrayList<Contrato>();
				System.out.println("\nContratos vinculados ao cliente: " + cliente.getNome());
				for (Object contrato : contratos) {
					if (((Contrato) contrato).cliente.getCpf() == cliente.getCpf()) {
						contratosCliente.add((Contrato) contrato);
						valida++;
					}
				}
				if (valida == 0) {
					System.out.println("Não há contratos ativos vinculados a este cliente.");
				} else {
					// Mostra os contratos vinculados ao cliente
					int i = 0;
					for (Contrato doCliente : contratosCliente) {
						i++;
						System.out.println("\n" + i + "-" + doCliente.exibirContrato());
					}
				}
			}else System.out.println("Cliente não cadastrado.");
		}
			}catch(PathIndisponivel e) {
			e.printStackTrace();
			}
	}
	@Override
	public void extenderContrato(Cliente cliente) {
		veiculos.clear();
		clientes.clear();
		contratos.clear();
		//Verifica o cadastro
		File arquivoClientes = new File(pathClientes);
		File arquivoContratos = new File(pathContratos);
		//verifica se o caminho do arquivo existe
		try {
			if (!arquivoClientes.isAbsolute() | !arquivoVeiculos.isAbsolute() | !arquivoContratos.isAbsolute()) {
				throw new PathIndisponivel("Atualize as variáveis de path na classe Locadora");
		}else {
			while (true) {
				try {
					clientes = abreArquivo(pathClientes);
					break;
				} catch (FileNotFoundException e) {
					criarArquivo(pathClientes);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (Object cadastrado : clientes) {
				if (((Pessoa) cadastrado).getCpf() == ((Pessoa) cliente).getCpf()) {
					cliente.setCadastrado(true);
					break;
				}
			}
			if (cliente.isCadastrado()) {
				while (true) {
					try {
						contratos = abreArquivo(pathContratos);
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Não esxitem contratos disponíveis");
						criarArquivo(pathContratos);
						break;
					} catch (IOException e) {
						e.printStackTrace();
						break;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						break;
					}
				}
				// Encontra os contratos vinculados ao cliente
				int valida = 0;
				ArrayList<Contrato> contratosCliente = new ArrayList<Contrato>();
				System.out.println("\nContratos vinculados ao cliente: " + cliente.getNome());
				for (Object contrato : contratos) {
					if ((((Contrato) contrato).cliente.getCpf() == cliente.getCpf())) {
						valida++;
						contratosCliente.add((Contrato) contrato);
					}
				}
				// Verifica se há contratos
				if (valida == 0) {
					System.out.println("Não há contratos ativos vinculados a este cliente.");
				} else {
					// Mostra os contratos vinculados ao cliente
					int i = 0;
					for (Contrato doCliente : contratosCliente) {
						i++;
						System.out.println("\n" + i + "-" + doCliente.exibirContrato());
					}
					int esc = 0;
					int indice;
					int days;
						while (true) {
							System.out.println("\nQual contrato deseja extender?");
							try {
								esc = sc.nextInt();
								if (esc > 0 && esc <= i) {
									indice = contratos.indexOf((Object) (contratosCliente.get(esc - 1)));
									while (true) {
										System.out.println("\nPor quantos dias deseja permanecer com o veículo?");
										try {
											days = sc.nextInt();
											if (days > 0) {
												break;
											}else System.out.println("Tempo mínimo: 1 Dia");
											
										} catch (InputMismatchException tipo) {
											System.out.println("Certifique-se de informar um valor inteiro, em dias.");
											sc.nextLine();
										}
									}
									((Contrato) contratos.get(indice)).setDate(days);
									try {
										atualizarArquivo(contratos, pathContratos);
										System.out.println("Contrato Atualizado!");
										System.out.println(((Contrato) contratos.get(indice)).exibirContrato());
										break;
									}catch (IOException e) {
										e.printStackTrace();
									}
								}else System.out.println("Informe uma opção válida.");
							}catch (InputMismatchException tipo) {
								System.out.println("Certifique-se de informar um valor inteiro");
								sc.nextLine();
							}
						}
				}
			}else System.out.println("\nCliente não cadastrado!");
		}
		}catch(PathIndisponivel e) {
			e.printStackTrace();
		}
	}

	// Métodos auxiliares
	private void criarArquivo(String path) {
		try {
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));
			obj.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void atualizarArquivo(ArrayList<Object> objetos, String path) throws IOException {
		ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));
		obj.writeObject(objetos);
		obj.close();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Object> abreArquivo(String path) throws IOException, ClassNotFoundException {
		
		ArrayList<Object> objetos = new ArrayList<Object>();
		ObjectInputStream lerObj = new ObjectInputStream(new FileInputStream(path));
		try {
			objetos = (ArrayList<Object>) lerObj.readObject();

		} catch (EOFException fim) {
			
		} finally {
			lerObj.close();
		}

		return objetos;
	}

	// Cadastrei os veículos pra facilitar a execução
	private void listaVeiculos() {
		veiculos.clear();
		veiculos.add(new Carro("MOD0833", 2015, "VW - VolksWagen", "Gol I Motion Trendline", "Vermelho"));
		veiculos.add(new Carro("MSW6848", 2014, "VW - VolksWagen", "up! take", "Amarelo"));
		veiculos.add(new Carro("MYW7828", 2017, "VW - VolksWagen", "up! Run I MOTION", ""));
		veiculos.add(new Carro("LVX3436", 2016, "Renault", "DUSTER OROCH Dyna", "Cinza"));
		veiculos.add(new Carro("KDU6978", 2016, "Fiat", "Strada Adv", "Prata"));
		veiculos.add(new Carro("KCH1912", 2015, "Fiat", "Grand Siena", "Branco"));
		veiculos.add(new Carro("KCU5191", 2016, "Fiat", "Toro Freedom", "Vermelho"));
		veiculos.add(new Moto("MYU7749", 2012, "BMW", "750 GS", "Branca"));
		veiculos.add(new Moto("HWL2823", 2015, "BMW", "310 GS", "Preta"));
		veiculos.add(new Moto("NAU7985", 2018, "Triumph", "Tiger 750", "Vermelha"));
		veiculos.add(new Van("HZP3116", 2012, "Mercedes-Benz", "Sprinter 515", "Prata"));
		veiculos.add(new Van("HZB1711", 2012, "Mercedes-Benz", "Sprinter 415 Furg", "Preta"));

		while (true) {
			try {
				abreArquivo(pathVeiculos);
				break;
			} catch (FileNotFoundException e) {
				criarArquivo(pathVeiculos);
				try {
					atualizarArquivo(veiculos , pathVeiculos);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
