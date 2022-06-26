package agenda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContatoTest {
	
	private Contato contatoCompleto;
	private Contato contatoSemSobrenome;
	
	@BeforeEach
	void preparaContato() {
		this.contatoCompleto = new Contato("Joao","Victor","1234-5678");
		this.contatoSemSobrenome = new Contato("Joao","","1234-5678");
	}
	
	/**
	 * Cadastra o contato caso todas as informações necessárias sejam passadas
	 */
	@Test
	public void DeveCadastraContato() {
		 Assertions.assertNotNull(contatoCompleto);
	}
	
	/**
	 * O sistema não deve permitir salvar um contato se o nome for null
	 */
	@Test
	public void NaoDeveCadastrarContatoComNomeNull() {
		NullPointerException exception =  Assertions.assertThrows(NullPointerException.class, () -> new Contato(null,"Victor","1234-5678"));
		
		Assertions.assertEquals(exception.getMessage(),"Nome ou telefone nulo");
		
	}
	
	/**
	 * O sistema não deve permitir salvar um contato se o telefone for null
	 */
	@Test
	public void NaoDeveCadastrarContatoComTelefoneNull() {
		NullPointerException exception =  Assertions.assertThrows(NullPointerException.class, () -> new Contato("João","Victor",null));
		
		Assertions.assertEquals(exception.getMessage(),"Nome ou telefone nulo");
		
	}
	
	/**
	 * O sistema não deve permitir salvar um contato se o nome for vazio
	 */
	
	@Test
	public void NaoDeveCadastrarContatoComNomeVazio() {
		IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class, () -> new Contato("","Victor","1234-5678"));
		
		Assertions.assertEquals(exception.getMessage(),"Nome ou telefone vazio");
		
	}
	
	/**
	 * O sistema não deve permitir salvar um contato se o telefone for vazio
	 */
	@Test
	public void NaoDeveCadastrarContatoComTelefoneVazio() {
		IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class, () -> new Contato("João","Victor",""));
		
		Assertions.assertEquals(exception.getMessage(),"Nome ou telefone vazio");
		
	}
	
	/**
	 * O sistema deve alterar o número do telefone do contato
	 */
	@Test
	public void DeveEditarTelefone() {
		this.contatoCompleto.editarTelefone("8765-4321");
		Assertions.assertTrue(this.contatoCompleto.verificaTelefone("8765-4321"));
	}
	
	/**
	 * O sistema não deve alterar o número do telefone do contato caso o parâmetro informado seja vazio
	 */
	@Test
	public void NaoDeveEditarTelefoneVazio() {
		this.contatoCompleto.editarTelefone("");
		Assertions.assertFalse(this.contatoCompleto.verificaTelefone(""));
	}
	
	/**
	 * O sistema não deve alterar o número do telefone do contato caso o parâmetro informado seja null
	 */
	@Test
	public void NaoDeveEditarTelefoneNull() {
		this.contatoCompleto.editarTelefone(null);
		Assertions.assertFalse(this.contatoCompleto.verificaTelefone(null));
	}
	
	/**
	 * O sistema deve exibir o nome completo do contato
	 */
	@Test
	public void ExibirNomeCompleto() {
		Assertions.assertEquals(this.contatoCompleto.getNomeCompleto(), "Joao Victor");
	}
	
	/**
	 * O sistema deve exibir a representação textual do contato completo
	 */
	@Test
	public void exibirContatoCompleto() {
		Assertions.assertEquals(this.contatoCompleto.toString(), "Joao Victor\n1234-5678\n");
	}
	
	/**
	 * O sistema deve exibir a representação textual do contato completo
	 */
	@Test
	public void exibirContatoSemSobrenome() {
		Assertions.assertEquals(this.contatoSemSobrenome.toString(), "Joao\n1234-5678\n");
	}
	
	/**
	 * O sistema deve exibir o nome completo do contato sem sobrenome
	 */
	@Test
	public void ExibirNomeCompletoSemSobrenome() {
		
		Assertions.assertEquals(this.contatoSemSobrenome.getNomeCompleto(), "Joao");
	}
	
	/**
	 * O sistema deve verificar se os contatos são iguais e retornar verdadeiro
	 */
	@Test
	public void verificaSeOsContatosSaoIguais() {
		
		Assertions.assertTrue(this.contatoCompleto.equals("Joao", "Victor"));
	}
	
	/**
	 * O sistema deve verificar se os contatos são iguais e retornar falso
	 */
	@Test
	public void verificaSeOsContatosSaoDiferentes1() {
		
		Assertions.assertFalse(this.contatoCompleto.equals("Joao", "Pedro"));
	}
	
	/**
	 * O sistema deve verificar se os contatos são iguais e retornar falso
	 */
	@Test
	public void verificaSeOsContatosSaoDiferentes2() {
		
		Assertions.assertFalse(this.contatoCompleto.equals("Jose", "Victor"));
	}
	
	
	/**
	 * O sistema deve cadastrar uma tag no contato
	 */
	@Test
	public void DeveCadastrarTag() {
		this.contatoCompleto.adicionarTag(1, "UFCG");
		Assertions.assertTrue(this.contatoCompleto.verificaTag("UFCG"));
	}
	
	/**
	 * O sistema não deve cadastrar uma tag vazia na lista de tag do contato
	 */
	@Test
	public void NaoDeveCadastrarTagVazia() {
		this.contatoCompleto.adicionarTag(1, "");
		Assertions.assertFalse(this.contatoCompleto.verificaTag(""));
	}
	
	/**
	 * O sistema não deve cadastrar uma tag null na lista de tag do contato
	 */
	@Test
	public void NaoDeveCadastrarTagNull() {
		this.contatoCompleto.adicionarTag(1, null);
		Assertions.assertFalse(this.contatoCompleto.verificaTag(null));
	}
	
	/**
	 * O sistema deve remover uma tag no contato
	 */
	@Test
	public void DeveRemoverTag() {
		this.contatoCompleto.adicionarTag(1, "UFCG");
		this.contatoCompleto.removerTag("UFCG");
		Assertions.assertFalse(this.contatoCompleto.verificaTag("UFCG"));
	}
	
	/**
	 * O sistema deve exibir um coração antes do contato
	 */
	@Test
	public void exibirFavorito() {
		this.contatoCompleto.setFavorito(true);
		Assertions.assertEquals(this.contatoCompleto.toString(), "❤️ Joao Victor\n1234-5678\n");
	}
}
