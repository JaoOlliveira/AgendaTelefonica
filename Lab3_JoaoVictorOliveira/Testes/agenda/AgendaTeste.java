package agenda;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgendaTeste {
	
	private Agenda agenda;
	
	@BeforeEach
	void preparaAgenda() {
		this.agenda = new Agenda();
		this.agenda.adicionarContato(0, "Joao",  "Victor",  "1234-5678");
		this.agenda.adicionarContato(1, "Pedro", "Augusto", "9999-9999");
		this.agenda.adicionarContato(3, "Ana",   "Carla",   "2222-2222");
	}
	
	
	// Primeiro bloco de códigos de teste (CADASTRO DE CONTRATO)
	
	
	/**
	 * Cadastra um contato caso todos os dados sejam informados
	 */
	@Test
	void cadastrarContato() {
		this.agenda.adicionarContato(2, "Maria", "Clara", "1111-1111");
		 Assertions.assertNotNull(this.agenda.temContato("Maria", "Clara"));
	}
	
	/**
	 * Cadastra um contato caso todos os dados sejam informadosna posição limite inferior
	 */
	@Test
	void cadastrarContatoLimiteInferior() {
		this.agenda.adicionarContato(0, "Maria", "Clara", "1111-1111");
		 Assertions.assertNotNull(this.agenda.temContato("Maria", "Clara"));
	}
	
	/**
	 * Cadastra um contato caso todos os dados sejam informadosna posição limite superior
	 */
	@Test
	void cadastrarContatoLimiteSuperior() {
		this.agenda.adicionarContato(99, "Maria", "Clara", "1111-1111");
		 Assertions.assertNotNull(this.agenda.temContato("Maria", "Clara"));
	}
	
	/**
	 * Não cadastrar um contato caso o index passado para o contato seja  menor que zero
	 */
	@Test
	void naoCadastrarContatoIndexMenorQueZero() {
		this.agenda.adicionarContato(-1, "Maria", "Clara", "1111-1111");
		 Assertions.assertNull(this.agenda.temContato("Maria", "Clara"));
	}
	
	/**
	 * Não cadastrar um contato caso o index passado para o contato seja igual ou maior que cem
	 */
	@Test
	void naoCadastrarContatoIndexMaiorOuIgualCem() {
		this.agenda.adicionarContato(100, "Maria", "Clara", "1111-1111");
		 Assertions.assertNull(this.agenda.temContato("Maria", "Clara"));
	}
	
	
	/**
	 * Não cadastrar um contato caso o index passado para o contato seja  menor que zero
	 */
	@Test
	void naoCadastrarContatoJaCadastrado() {
		this.agenda.adicionarContato(30, "Joao", "Victor", "1111-1111");
		 Assertions.assertNull(this.agenda.getContato(30));
	}
	
	/**
	 * Não cadastrar um contato caso o index passado para o contato seja  menor que zero
	 */
	@Test
	void sobreporContato() {
		this.agenda.adicionarContato(4, "Jose",   "Luciano",   "7777-7777");
		this.agenda.adicionarContato(4, "Maria", "Fernanda", "8888-8888");
		 Assertions.assertFalse(this.agenda.getContato(4).equals("Jose", "Luciano"));
	}
	

	
	
	// Segundo bloco de códigos de teste (EXIBIÇÃO DE CONTRATO)
	
	void listarVazio() {
		Agenda agendaVazia = new Agenda();
		Assert.assertNull(agendaVazia.listarContatos());
	}
	
	/**
	 * Exibe o contato, caso a posição seja valida.
	 * Para a posição ser considerada válida ela precisa ser >=0 e <=99 e possuir um contato associado a ela
	 */
	@Test
	void exibirContato() {
		
		 Assertions.assertNotNull(this.agenda.exibirContato(0));
	}
	
	/**
	 * Não exibe contatos com index menor que 0
	 */
	@Test
	void naoExibirContatoIndexMenorQue0() {
		 Assertions.assertNull(this.agenda.exibirContato(-1));
	}
	
	/**
	 * Não exibe contatos com index maior que 99
	 */
	@Test
	void naoExibirContatoIndexMaiorOuIgualCem() {
		 Assertions.assertNull(this.agenda.exibirContato(100));
	}
	
	/**
	 * Não exibe contatos com index válido mas sem nenhum contato associado a ele
	 */
	@Test
	void naoExibirContatoIndexValidoSemContato() {
		 Assertions.assertNull(this.agenda.exibirContato(50));
	}
	
	/**
	 * Remove o contato da lista de contatos
	 */
	@Test
	void removerContato() {
		this.agenda.adicionarContato(6, "Aurora", "Boreal", "4545-4545");
		this.agenda.removerContato(6);
		Assert.assertNull(this.agenda.getContato(6));
	}
	
	@Test
	void removerContatoFavoritado() {
		this.agenda.adicionarContato(6, "Aurora", "Boreal", "4545-4545");
		this.agenda.adicionarFavorito(6, 6);
		this.agenda.removerContato(6);
		Assert.assertNull(this.agenda.getContatoFavorito(6));
	}
	
	

	
	// Terceiro bloco de códigos de teste (CADASTRO E EXIBIÇÃO DE FAVORITOS)
	
	
	/**
	 * Adiciona o contato a lista de favoritos, caso a posição seja valida.
	 * Para a posição ser considerada válida ela precisa ser >=0 e <=99 e possuir um contato associado a ela
	 */
	@Test
	void adicionarFavorito() {
		this.agenda.adicionarFavorito(1, 0);
		Assertions.assertNotNull(this.agenda.getContatoFavorito(0));
	}
	
	/**
	 * O sistema deve exibir um coração antes do contato
	 */
	@Test
	public void exibirFavorito() {
		this.agenda.adicionarFavorito(1, 0);
		Assertions.assertEquals(this.agenda.getContato(1).toString(), "❤️ Pedro Augusto\n9999-9999\n");
	}
	
	/**
	 * O sistema deve parar de exibir o coração caso o contato deixe de ser um contato favorito
	 */
	@Test
	public void exibirExFavorito() {
		this.agenda.adicionarFavorito(1, 0);
		this.agenda.removerFavorito(1);
		Assertions.assertEquals(this.agenda.getContato(1).toString(), "Pedro Augusto\n9999-9999\n");
	}
	
	/**
	 * Não adiciona o contato na lista de favoritos com index favorito menor que 0
	 */
	@Test
	void naoAdicionarFavoritoIndexMenorQueZero() {
		this.agenda.adicionarFavorito(1, -1);
		Assertions.assertNull(this.agenda.getContatoFavorito(-1));
	}
	
	/**
	 * Não adiciona o contato na lista de favoritos com index favorito maior que 9
	 */
	@Test
	void naoAdicionarFavoritoIndexMaiorQueNove() {
		this.agenda.adicionarFavorito(1, 10);
		Assertions.assertNull(this.agenda.getContatoFavorito(10));
	}
	
	/**
	 * Não adiciona o contato na lista de favoritos se o index do contato for válido mas sem nenhum contato associado a ele
	 */
	@Test
	void naoAdicionarFavoritoIndexSemContato() {
		this.agenda.adicionarFavorito(74, 1);
		Assertions.assertNull(this.agenda.getContatoFavorito(1));
	}
	
	/**
	 * Remover o contato da lista de favoritos, caso a posição seja valida.
	 * Para a posição ser considerada válida ela precisa ser >=0 e <=99 e possuir um contato associado a ela
	 */
	@Test
	void removerFavorito() {
		this.agenda.adicionarFavorito(1, 0);
		this.agenda.removerContato(1);
		Assertions.assertNull(this.agenda.getContatoFavorito(0));
	}
	
	
	
	
	
	
	
	
	// Quarto bloco de códigos de teste (EDITAR CONTATO)
	
	

	/**
	 * Editar o numero do telefone do contato, caso a posição seja valida.
	 * Para a posição ser considerada válida ela precisa ser >=0 e <=99 e possuir um contato associado a ela
	 */
	@Test
	void editarTelefone() {
		this.agenda.editarTelefone(0, "2222-2222");
		 Assertions.assertTrue(this.agenda.getContato(0).verificaTelefone("2222-2222"));
	}
	
	/**
	 * Não edita o numero do telefone do contato com index menor que 0
	 */
	@Test
	void naoEditarTelefoneIndexMenorQueZero() {
		this.agenda.editarTelefone(-1, "2222-2222");
		 Assertions.assertNull(this.agenda.getContato(-1));
	}
	
	/**
	 * Não edita o numero do telefone do contato com index maior que 99
	 */
	@Test
	void naoEditarTelefoneIndexMaiorOuIgualCem() {
		this.agenda.editarTelefone(0, "2222-2222");
		 Assertions.assertNull(this.agenda.getContato(100));
	}
	
	/**
	 * Não edita o numero do telefone do contato com index válido mas sem nenhum contato associado a ele
	 */
	@Test
	void naoEditarTelefoneIndexValidoSemContato() {
		this.agenda.editarTelefone(50, "2222-2222");
		 Assertions.assertNull(this.agenda.getContato(50));
	}
	
	


}
