package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import regras.RegrasAgenda;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.print(
				"\n--------------------------------\nMENU\n--------------------------------\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" + 
						"(MT)Mudar telefone\n" + 
						"(F)avoritos\n" + 
						"(A)dicionar Favorito\n" + 
						"(RF)Remover Favorito\n" + 
						"(T)ags\n" + 
						"(RT)remover Tags\n" + 
						"(R)emover Contato\n" + 
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.nextLine().toUpperCase();
	}
	
	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			String comando = filtro(scanner);
			comandoFiltro(comando,agenda, scanner);
			break;
		case "MT":
			alterarTelefone(agenda, scanner);
			break;
		case "F":
			listarFavoritos(agenda);
			break;
		case "A":
			cadastrarFavorito(agenda, scanner);
			break;
		case "RF":
			removerFavorito(agenda, scanner);
			break;
		case "T":
			cadastrarTags(agenda, scanner);
			break;
		case "RT":
			removerTags(agenda, scanner);
			break;
		case "R":
			removerContato(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}
	
	
	/**
	 * Exibe o menu de filtragem e captura a escolha do/a usuário/a 
	 * @param scanner
	 * @return
	 */
	private static String filtro(Scanner scanner) {
		System.out.println("\n--------------------------------\nFiltro\n--------------------------------\n");
		System.out.println("Como deseja buscar o contato?");
		System.out.println("(P)osicao");
		System.out.println("(N)ome");
		System.out.println("(S)obrenome");
		System.out.println("(T)elefone");
		System.out.println("(Ta)gs");
		System.out.println("Opção>");
		return scanner.nextLine().toUpperCase();
	}
	
	/**
	 * Interpreta a opção escolhida para a busca do contato, informada por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comandoFiltro(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "P":
			exibeContatoPosicao(agenda, scanner);
			break;
			
		case "N":
			exibeContatoNome(agenda, scanner);
			break;
		
		case "S":
			exibeContatoSobrenome(agenda, scanner);
			break;
		
		case "T":
			exibeContatoTelefone(agenda, scanner);
			break;
		
		case "TA":
			exibeContatoTag(agenda, scanner);
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	
	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("Posição na agenda> ");
		int posicao = Integer.parseInt(scanner.nextLine())-1;
		
		if(RegrasAgenda.verificaIndex(posicao)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		System.out.print("Nome> ");
		String nome = scanner.nextLine();
		if(RegrasAgenda.verificaString(nome)) {
			System.out.println("\nERRO -- CONTATO INVALIDO");
			return;
		}
		
		System.out.print("Sobrenome> ");
		String sobrenome = scanner.nextLine();
		
		if(agenda.temContato(nome, sobrenome) != null) {
			System.out.println("\nERRO -- CONTATO JA CADASTRADO");
			return;
		}
		
		System.out.print("Telefone> ");
		String telefone = scanner.nextLine();
		if(RegrasAgenda.verificaString(telefone)) {
			System.out.println("\nERRO -- CONTATO INVALIDO");
			return;
		}
		agenda.adicionarContato(posicao, nome, sobrenome, telefone);
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda, que foi filtrado pela posicao. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContatoPosicao(Agenda agenda, Scanner scanner) {
		
		System.out.print("\nQual contato> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		posicao--;
		if(RegrasAgenda.verificaIndex(posicao)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		String contato = agenda.exibirContato(posicao);
		
		if(contato == null) {
			System.out.println("\nERRO -- O contato "+ (posicao+1) + " não existe");
			return;
		}
		
		System.out.println("Dados do contato:\n" + contato);
	}
	
	/**
	 * Imprime os detalhes  dos contatos da agenda, que foram filtrados pelo nome. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContatoNome(Agenda agenda, Scanner scanner) {
		
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		
		if(RegrasAgenda.verificaString(nome)) {
			System.out.println("\nERRO -- NOME INVALIDO");
			return;
		}
		
		String contatos[] = agenda.getContatosNome(nome);
		
		if(contatos.length == 0) {
			System.out.println("\nERRO -- Nenhum contato cadastrado com o nome: "+nome);
		}else {
			System.out.println("Dados do(s) contato(s):\n");
			for(String contato : contatos) {
				System.out.println(contato);
			}
		}
	}
	
	
	/**
	 * Imprime os detalhes  dos contatos da agenda, que foram filtrados pelo sobrenome. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContatoSobrenome(Agenda agenda, Scanner scanner) {
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		
		String contatos[] = agenda.getContatosSobrenome(sobrenome);
		
		if(contatos.length == 0) {
			System.out.println("\nERRO -- Nenhum contato cadastrado com o sobrenome: "+sobrenome);
		}else {
			System.out.println("Dados do(s) contato(s):\n");
			for(String contato : contatos) {
				System.out.println(contato);
			}
		}
		
	}
	
	/**
	 * Imprime os detalhes  dos contatos da agenda, que foram filtrados pelo numero do telefone. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContatoTelefone(Agenda agenda, Scanner scanner) {
		
		System.out.print("\nTelefone> ");
		String telefone = scanner.nextLine();
		
		if(RegrasAgenda.verificaString(telefone)) {
			System.out.println("\nERRO -- TELEFONE INVALIDO");
			return;
		}
		
		String contatos[] = agenda.getContatosTelefone(telefone);
		
		if(contatos.length == 0) {
			System.out.println("\nERRO -- Nenhum contato cadastrado com o telefone: "+telefone);
		}else {
			System.out.println("Dados do(s) contato(s):\n");
			for(String contato : contatos) {
				System.out.println(contato);
			}
		}
	}
	
	
	/**
	 * Imprime os detalhes  dos contatos da agenda, que foram filtrados pelo numero do telefone. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContatoTag(Agenda agenda, Scanner scanner) {
		
		System.out.print("\nTag> ");
		String tag = scanner.nextLine();
		
		if(RegrasAgenda.verificaString(tag)) {
			System.out.println("\nERRO -- TAG INVALIDA");
			return;
		}
		
		String contatos[] = agenda.getContatosTag(tag);
		
		if(contatos.length == 0) {
			System.out.println("\nERRO -- Nenhum contato cadastrado com A tag: "+tag);
		}else {
			System.out.println("Dados do(s) contato(s):\n");
			for(String contato : contatos) {
				System.out.println(contato+"\n");
			}
		}
	}
	
	
	
	
	/**
	 * Alterar o telefone de um contato já existente. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void alterarTelefone(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = Integer.parseInt(scanner.nextLine())-1;
		
		if(RegrasAgenda.verificaIndex(posicao)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		String contato = agenda.exibirContato(posicao);
		
		if(contato == null) {
			System.out.println("\nERRO -- O contato "+(posicao+1) + " não existe");
			return;
		}
		
		System.out.println("Dados do contato:\n" + contato);
		
		System.out.print("Novo Telefone> ");
		String novoTelefone = scanner.nextLine();
		if(RegrasAgenda.verificaString(novoTelefone)) {
			System.out.println("\nERRO -- CONTATO INVALIDO");
			return;
		}
		
		agenda.editarTelefone(posicao,novoTelefone);
		
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		String[] contatos = agenda.listarContatos();
		boolean naoPossuiContato = true;
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println(contatos[i]);
				naoPossuiContato = false;
			}
		}
		if(naoPossuiContato) {
			System.out.println("\nNenhum contato cadastrado");
		}
	}

	/**
	 * Imprime lista de contatos favoritos
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listarFavoritos(Agenda agenda) {
		System.out.println("\nLista de contatos favoritos: ");
		String[] contatosFavoritos = agenda.listarFavoritos();
		boolean naoPossuiFavorito = true;
		for (int i = 0; i < contatosFavoritos.length; i++) {
			if (contatosFavoritos[i] != null) {
				System.out.println(contatosFavoritos[i]);
				naoPossuiFavorito = false;
			}
		}
		if(naoPossuiFavorito) {
			System.out.println("\nNenhum contato cadastrado como favorito");
		}
	}
	
	/**
	 * Cadastra um contato na lista de favoritos. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastrarFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("Contato> ");
		int contatoIndex = Integer.parseInt(scanner.nextLine())-1;
		
		if(RegrasAgenda.verificaIndex(contatoIndex)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		if(RegrasAgenda.verificaSeExisteContato(agenda, contatoIndex)) {
			System.out.println("\nERRO -- O contato "+ (contatoIndex+1) + " não existe");
			return;
		}
		
		if(RegrasAgenda.verificaFavorito(agenda, contatoIndex)) {
			System.out.println("\nERRO -- O contato "+ (contatoIndex+1) + " já esta cadastrado como favorito");
			return;
		}
		
		System.out.print("Posicao> ");
		int favoritoIndex = Integer.parseInt(scanner.nextLine())-1;
		
		
		if(RegrasAgenda.verificaIndexFavorito(favoritoIndex)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		
		agenda.adicionarFavorito(contatoIndex, favoritoIndex);
		}
	
	
	/**
	 * Remove um contato da lista de favoritos. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("Contato> ");
		int contatoIndex = Integer.parseInt(scanner.nextLine())-1;
		
		if(RegrasAgenda.verificaIndex(contatoIndex)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA");
			return;
		}
		
		if(RegrasAgenda.verificaSeExisteContato(agenda, contatoIndex)) {
			System.out.println("\nERRO -- O contato "+ (contatoIndex+1) + " não existe");
			return;
		}
		
		if(!RegrasAgenda.verificaFavorito(agenda, contatoIndex)) {
			System.out.println("\nERRO -- O contato "+ (contatoIndex+1) + " não esta cadastrado como favorito");
			return;
		}
		
		agenda.removerFavorito(contatoIndex);
		}
	
	/**
	 * Cadastra uma tag de identificação em um ou vários contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastrarTags(Agenda agenda, Scanner scanner) {
		
		System.out.print("Contato(s)> ");
		String listaContatos = scanner.nextLine();
		
		
		System.out.print("Tag> ");
		String tag = scanner.nextLine();
		if(RegrasAgenda.verificaString(tag)) {
			System.out.println("\nERRO -- TAG INVÁLIDA!");
			return;
		}
		
		
		System.out.print("Posicao tag> ");
		int indexTag  = Integer.parseInt(scanner.nextLine())-1;
		
		if(RegrasAgenda.verificaIndexTags(indexTag)) {
			System.out.println("\nERRO -- POSIÇÃO INVÁLIDA!");
			return;
		}
		
		for(String contato : listaContatos.split(" ")) {
			int contatoIndex = Integer.parseInt(contato)-1;
			
			if(!RegrasAgenda.verificaSeExisteContato(agenda,contatoIndex)) {
				agenda.adicionarTag(contatoIndex,tag,indexTag);
			}else {
				System.out.println("\nERRO -- O contato "+ (contato+1) + " não existe");
			}
		}
		
	}
	
	
	/**
	 * Remove uma tag de identificação em um ou vários contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerTags(Agenda agenda, Scanner scanner) {
		
		System.out.print("Contato(s)> ");
		String listaContatos = scanner.nextLine();
		
		
		System.out.print("Tag> ");
		String tag = scanner.nextLine();
		if(RegrasAgenda.verificaString(tag)) {
			System.out.println("\nERRO -- TAG INVÁLIDA!");
			return;
		}
		
		for(String contato : listaContatos.split(" ")) {
			int contatoIndex = Integer.parseInt(contato)-1;
			
			if(!RegrasAgenda.verificaSeExisteContato(agenda,contatoIndex)) {
				agenda.removerTag(contatoIndex,tag);
			}else {
				System.out.println("\nERRO -- O contato "+ (contato+1) + " não existe");
			}
		}
		
	}
	
	/**
	 * Apagar um ou varios contato da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerContato(Agenda agenda, Scanner scanner) {
		System.out.print("Contato(s)> ");
		String listaContatos = scanner.nextLine();
		for(String contato : listaContatos.split(" ")) {
			if(!RegrasAgenda.verificaSeExisteContato(agenda, Integer.parseInt(contato)-1)) {
				agenda.removerContato(Integer.parseInt(contato)-1);
			}else {
				System.out.println("\nERRO -- O contato "+ (contato+1) + " não existe");
			}
			
		}
		
	}
	
	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
