package agenda;

import uteis.ArrayUtil;

/**
 * Representação de uma agenda de contatos
 * @author João Victor dos Santos Oliveira
 *
 */
public class Agenda {
	/**
	 * Lista de contatos salvos na agenda
	 */
	private Contato contatos[];
	
	/**
	 * Lista de contatos favoritados
	 */
	private Contato favoritos[];
	
	/**
	 * Contador da quantidade de contatos salvos na agenda
	 */
	private int quantContatosSalvos;
	
	/**
	 * Contador da quantidade de contatos favoritos salvos na agenda
	 */
	private int quantContatosFavoritos;
	
	/**
	 * Quantidade maxima de contatos comportada pela agenda
	 */
	private static final int TAMANHO_AGENDA = 100;
	
	/**
	 * Quantidade maxima de contatos favoritos comportada pela agenda
	 */
	private static final int TAMANHO_AGENDA_FAVORITOS = 10;

	/**
	 * Constroi a agenda, inicializando todas os contadores com 0
	 */
	Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_AGENDA_FAVORITOS];
		this.quantContatosFavoritos = 0;
		this.quantContatosSalvos = 0;
	}

	/**
	 * Adiciona um contato na agenda
	 * @param index posição do contato na agenda
	 * @param nome nome do contato
	 * @param sobrenome sobrenome do contato
	 * @param telefone número do telefone do contato
	 */
	public void adicionarContato(int contatoIndex, String nome, String sobrenome, String telefone) {
		if (this.verificaIndex(contatoIndex)) {
			if (this.temContato(nome, sobrenome) == null) {
				if (this.contatos[contatoIndex] == null) {
					this.quantContatosSalvos++;
				}else {
					this.removerFavorito(contatoIndex);
				}
				this.contatos[contatoIndex] = new Contato(nome.trim(), sobrenome.trim(), telefone.trim());
				
			}
		}
	}

	/**
	 * Verifica se o contato informado já existe na agenda de contatos
	 * @param nome nome do contato
	 * @param sobrenome sobrenome do contato
	 * @return um objeto do tipo Contato contendo as informações do contato, ou null caso o contato não exista
	 */
	public Contato temContato(String nome, String sobrenome) {

		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				if (contatos[i].equals(nome, sobrenome)) {
					return contatos[i];
				}
			}
		}

		return null;
	}

	/**
	 * Exibe as informações do contato selecionado
	 * @param index posição do contato na agenda
	 * @return Representação textual do contato, ou null caso o contato não exista
	 */
	public String exibirContato(int contatoIndex) {
		
		if(this.verificaIndex(contatoIndex)) {
			if (this.contatos[contatoIndex] != null) {
				return contatos[contatoIndex].toString();
			}
		}
		return null;
	}

	
	/**
	 * Altera o numero de um contato já existente
	 * @param index posição do contato na agenda
	 * @param novoTelefone novo número de telefone
	 * 
	 */
	public void editarTelefone(int contatoIndex, String novoTelefone) {
		
		if(this.verificaIndex(contatoIndex)) {
			if (this.contatos[contatoIndex] != null) {
				this.getContato(contatoIndex).editarTelefone(novoTelefone);
			}
		}
	}

	
	/**
	 * Lista todos os contatos salvos na agenda
	 * @return um array de String contendo a representação textual de cada contato
	 */
	public String[] listarContatos() {
		String listaContatos[] = new String[this.quantContatosSalvos];
		int index = 0;
		for (int i = 0; i < this.contatos.length; i++) {
			if (contatos[i] != null) {
				listaContatos[index] = i+1 + " - " + this.contatos[i].getNomeCompleto();
				index++;
			}
		}

		return listaContatos;
	}

	/**
	 * Adiciona um contato na lista de favoritos
	 * @param contatoIndex posição do contato na agenda
	 * @param favoritoIndex posição da lista dos favoritos que deseja salvar o contato
	 */
	public void adicionarFavorito(int contatoIndex, int favoritoIndex) {
		
		if (this.verificaIndex(contatoIndex) && this.verificaIndexFavorito(favoritoIndex)) {
			Contato contato = this.getContato(contatoIndex);
			if(contato != null) {
				if (!contato.isFavorito()) {
					contato.setFavorito(true);

					if (this.favoritos[favoritoIndex] == null) {
						this.quantContatosFavoritos++;
					}
					this.favoritos[favoritoIndex] = contato;
				}
			}
		}
	}
	
	/**
	 * Remove um contato na lista de favoritos
	 * @param contatoIndex posição do contato na agenda
	 * @param favoritoIndex posição da lista dos favoritos que deseja salvar o contato
	 */
	public void removerFavorito(int contatoIndex) {
		
		if (this.verificaIndex(contatoIndex)) {
			Contato contato = this.getContato(contatoIndex);
			if (contato.isFavorito()) {
				contato.setFavorito(false);

				for (int i = 0; i < this.favoritos.length; i++) {
					if (this.favoritos[i] != null) {
						if (contato.equals(this.favoritos[i].getNome(), this.favoritos[i].getSobrenome())) {
							this.favoritos[i] = null;
							this.quantContatosFavoritos--;
						}
					}
				}
			}
		}
		
	}

	/**
	 * Retorna uma lista com todos os contatos favoritos
	 * @return array de String contendo a representação textual de cada contato
	 */
	public String[] listarFavoritos() {
		String listaContatosFavoritos[] = new String[this.quantContatosFavoritos];
		int index = 0;

		for (int i = 0; i < this.favoritos.length; i++) {
			if (favoritos[i] != null) {
				listaContatosFavoritos[index] = i + 1 + " - " + this.favoritos[i].getNomeCompleto();
				index++;
			}
		}

		return listaContatosFavoritos;
	}

	/**
	 * Adiciona uma tag a um contato
	 * @param contatoIndex posição do contato na agenda
	 * @param tag identificação extra para o contato
	 * @param indexTag posição da lista de tags onde deseja salvar a tag
	 */
	public void adicionarTag(int contatoIndex, String tag, int indexTag) {
		
		
		System.out.println(contatoIndex);
		if(this.verificaIndex(contatoIndex) && this.verificaIndexTags(indexTag)) {
			Contato contato = this.getContato(contatoIndex);
			System.out.println(contato);
			if(contato != null) {
				contato.adicionarTag(indexTag, tag);
			}
		}
	}

	/**
	 * Adiciona uma tag a um contato
	 * @param contatoIndex posição do contato na agenda
	 * @param tag identificação extra para o contato
	 * @param indexTag posição da lista de tags onde deseja salvar a tag
	 */
	public void removerTag(int contatoIndex, String tag) {
		
		if(this.verificaIndex(contatoIndex)) {
			Contato contato = this.getContato(contatoIndex);
			if(contato != null) {
				contato.removerTag(tag);
			}
		}
	}

	
	/**
	 * Exclui o contato da lista de contatos
	 * @param contatoIndex posição do contato na agenda
	 */
	public void removerContato(int contatoIndex) {
		
		if(this.verificaIndex(contatoIndex)) {
			Contato contato = this.contatos[contatoIndex];
			if(contato != null) {
				if (contato.isFavorito()) {
					for (int i = 0; i < favoritos.length; i++) {
						if (this.favoritos[i] != null && contato.equals(this.favoritos[i])) {
							this.favoritos[i] = null;
							this.quantContatosFavoritos--;
							break;
						}
					}
				}
				this.contatos[contatoIndex] = null;

				this.quantContatosSalvos--;
			}
		}
	}
	
	/**
	 * Retorna a instancia de um contato favorito selecionado
	 * @param favoritoIndex posição do contato na lista de favoritos da agenda
	 * @return objeto do tipo contato ou null caso o contato informado não exista
	 */
	public Contato getContatoFavorito(int favoritoIndex) {
		if(this.verificaIndexFavorito(favoritoIndex)) {
			return this.favoritos[favoritoIndex];
		}
		return null;
	}

	/**
	 * Retorna a instancia de um contato selecionado
	 * @param indexContato posição do contato na agenda
	 * @return objeto do tipo contato ou null caso o contato informado não exista
	 */
	public Contato getContato(int contatoIndex) {
		if(this.verificaIndex(contatoIndex)) {
			return this.contatos[contatoIndex];
		}
		return null;
	}
	
	/**
	 * Retorna todos os contatos em forma de objeto
	 * @return um array de objetos do tipo Contato, com todos os contatos cadastrados
	 */
	public Contato[] getContatos() {
		return this.contatos;
	}

	
	/**
	 * Retorna todos os contatos em forma textual que possuirem o nome igual ao informado
	 * @param nome nome passado como filtragem
	 * @return  um array de string contendo a representação textual dos contatos filtrados ou um array vazio caso nenhum contato seja compativel
	 */
	public String[] getContatosNome(String nome) {
		String[] contatos = new String[0];
		int index = 0;
		for (int i = 0; i < this.contatos.length; i++) {
			if(this.contatos[i] != null) {
				if(this.contatos[i].verificaNome(nome)) {
					contatos = ArrayUtil.aumentarArrayString(contatos);
					contatos[index] = this.contatos[i].toString();
					index++;
				}
			}
		}
		return contatos;
	
	}
	
	/**
	 * Retorna todos os contatos em forma textual que possuirem o sobrenome igual ao informado
	 * @param sobrenome sobrenome passado como filtragem
	 * @return  um array de string contendo a representação textual dos contatos filtrados ou um array vazio caso nenhum contato seja compativel
	 */
	public String[] getContatosSobrenome(String sobrenome) {
		String[] contatos = new String[0];
		int index = 0;
		for (int i = 0; i < this.contatos.length; i++) {
			if(this.contatos[i] != null) {
				if(this.contatos[i].verificaSobrenome(sobrenome)) {
					contatos = ArrayUtil.aumentarArrayString(contatos);
					contatos[index] = this.contatos[i].toString();
					index++;
				}
			}
		}
		return contatos;
	
	}
	
	
	/**
	 * Retorna todos os contatos em forma textual que possuirem o numero de telefone igual ao informado
	 * @param telefone numero do telefone passado como filtragem
	 * @return um array de string contendo a representação textual dos contatos filtrados ou um array vazio caso nenhum contato seja compativel
	 */
	public String[] getContatosTelefone(String telefone) {
		String[] contatos = new String[0];
		int index = 0;
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].verificaTelefone(telefone)) {
					contatos = ArrayUtil.aumentarArrayString(contatos);
					contatos[index] = this.contatos[i].toString();
					index++;
				}
			}
		}
		return contatos;
	}
	
	/**
	 * Retorna todos os contatos em forma textual que possuirem a tag informada em sua lista de tags
	 * @param tag tag de identificação
	 * @return um array de string contendo a representação textual dos contatos filtrados ou um array vazio caso nenhum contato seja compativel
	 */
	public String[] getContatosTag(String tag) {
		String[] contatos = new String[0];
		int index = 0;
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].verificaTag(tag)) {
					contatos = ArrayUtil.aumentarArrayString(contatos);
					contatos[index] = this.contatos[i].toString();
					index++;
				}
			}
		}
		return contatos;
	}
	
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para um contato
	 * @param index posicao do contato na agenda
	 * @return true caso o index seja valido e true caso o index seja invalido
	 */
	public boolean verificaIndex(int index) {
		
		if(index < 0 || index > 99) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para um contato favorito
	 * @param index posicao do contato na lista dos contatos favoritos
	 * @return true caso o index seja valido e true caso o index seja invalido
	 */
	public boolean verificaIndexFavorito(int index) {
			
			if(index < 0 || index > 9) {
				return false;
			}
			
			return true;
	}
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para uma tag
	 * @param index posicao da tag na lista de tags de um contato
	 * @return true caso o index seja valido e true caso o index seja invalido
	 */
	public boolean verificaIndexTags(int index) {
		
		if(index < 0 || index > 4) {
			return false;
		}
		
		return true;
	}
}
