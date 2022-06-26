package agenda;

/**
 * Representa um contato telefonico
 * @author João Victor dos Santos Oliveira
 *
 */
public class Contato {
	
	/**
	 * Nome do contato
	 */
	private String nome;
	
	/**
	 * Sobrenome do contato
	 */
	private String sobrenome;
	
	/**
	 * Telefone do contato
	 */
	private String telefone;
	
	/**
	 * Lista de Tags de identificação do contato
	 */
	private String tags[];
	
	/**
	 * Identificação de se o contato esta na lista de favoritos
	 */
	private boolean favorito;
	
	
	/**
	 * Constroi um contato, instanciando nome,sobrenome, telefone e inicializando favorito com false
	 * @param nome nome do contato
	 * @param sobrenome sobrenome do contato
	 * @param telefone telefone do contato
	 */
	Contato(String nome, String sobrenome, String telefone){
		
		if(nome == null || telefone == null) {
			throw new NullPointerException("Nome ou telefone nulo");
		}
		nome.trim();
		sobrenome.trim();
		telefone.trim();
		
		if(nome == "" || telefone == "") {
			throw new IllegalArgumentException("Nome ou telefone vazio");
		}
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.tags = new String[5];
		this.favorito = false;
	}
	
	/**
	 * Retorna a representação textual do contato. Se o contato for um dos favorítos, aparecerá um coração antes do seu nome
	 */
	public String toString() {
		String emoji = "";
		if(favorito){
			emoji = "❤️ ";
		}
		
		String tags = "";
		
		for (int i = 0; i < this.tags.length; i++) {
			if(this.tags[i] != null) {
				tags += this.tags[i] + " ";
			}
		}
		
		return  (emoji +this.nome + " " + this.sobrenome).trim() + "\n" + this.telefone + "\n" + tags;
	}
	
	
	/**
	 * Retorna a concatenação do nome com o sobrenome do contato
	 * @return String contendo o nome completo do contato
	 */
	public String getNomeCompleto() {
		
		return  (this.nome + " " + this.sobrenome).trim();
		
	}

	
	/**
	 * Alterar o numero do telefone do contato
	 * @param novoTelefone uma String contendo o novo número do telefone do contato
	 */
	public void editarTelefone(String novoTelefone) {
		if(novoTelefone != null && novoTelefone != "") {
			this.telefone = novoTelefone;
		}
	}
 
	/**
	 * Retorna se o contato é favorito ou não
	 * @return true caso ele seja favorito e false caso ele não seja
	 */
	public boolean isFavorito() {
		return this.favorito;
	}
	
	/**
	 * Define valor para o atributo favorito
	 * @param favorito true, caso ele seja favoritado ou false caso ele seja removido da lista.
	 */
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	/**
	 * Adiciona uma tag de identificação ao contato
	 * @param index posição da tag na lista de tags
	 * @param tag representação textual da tag de identificação
	 */
	public void adicionarTag(int index, String tag) {
		if(this.verificaIndexTags(index) && this.validaTag(tag) ) {
			this.tags[index] = tag;
		}	
	}
	
	/**
	 * Adiciona uma tag de identificação ao contato
	 * @param index posição da tag na lista de tags
	 * @param tag representação textual da tag de identificação
	 */
	public void removerTag(String tag) {
		if (this.validaTag(tag)) {
			for (int i = 0; i < tags.length; i++) {
				if (this.tags[i] != null) {
					if (this.tags[i].equals(tag)) {
						this.tags[i] = null;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Verifica se o contato é igual a outro contato, analisando o nome e o sobrenome
	 * @param nome nome do contato que deseja verifica se é igual a esse
	 * @param sobrenome sobrenome do contato que deseja verifica se é igual a esse
	 * @return false caso sejam diferentes e true caso sejam o mesmo contato
	 */
	public boolean equals(String nome, String sobrenome) {
		
		if(!this.nome.toLowerCase().equals(nome.toLowerCase())) {
			return false;
		}
		if(!this.sobrenome.toLowerCase().equals(sobrenome.toLowerCase())) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Retorna o nome do contato
	 * @return uma String contendo o nome do contato
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Retorna o sobrenome do contato
	 * @return uma String contendo o sobrenome do contato
	 */
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	/**
	 * Verifica se o nome passado é igual ao cadastrado no contato
	 * @return true caso os dois nomes sejam iguais ou false caso não sejam
	 */
	public boolean verificaNome(String nome) {
		if(this.nome.equals(nome)) {
			return  true;
		}
		return  false;
	}
	
	/**
	 * Verifica se o sobrenome passado é igual ao cadastrado no contato
	 * @return true caso os dois sobrenomes sejam iguais ou false caso não sejam
	 */
	public boolean verificaSobrenome(String sobrenome) {
		if(this.sobrenome.equals(sobrenome)) {
			return  true;
		}
		return  false;
	}
	
	/**
	 * Verifica se o telefone passado é igual ao cadastrado no contato
	 * @return true caso os dois telefones sejam iguais ou false caso não sejam
	 */
	public boolean verificaTelefone(String telefone) {
		if(this.telefone.equals(telefone)) {
			return  true;
		}
		return  false;
	}
	
	
	/**
	 * Verifica se o contato possui a tag informada na sua lista de tags
	 * @return true o contato possua a tag e false caso não possua
	 */
	public boolean verificaTag(String tag) {
		if (this.validaTag(tag)) {
			for (int i = 0; i < tags.length; i++) {
				if (tag.equals(this.tags[i])) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para uma tag
	 * @param index posicao da tag na lista de tags de um contato
	 * @return true caso o index seja valido e false caso o index seja invalido
	 */
	public boolean verificaIndexTags(int index) {
		
		if(index < 0 || index > 4) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se o valor passado para a tag é diferente de null ou vazio
	 * @param tag tag de identificação
	 * @return true caso a tag seja válida e false caso a tag seja invalida
	 */
	public boolean validaTag(String tag) {
		
		if(tag == null || tag.equals("")) {
			return false;
		}
		
		return true;
	}
		
}