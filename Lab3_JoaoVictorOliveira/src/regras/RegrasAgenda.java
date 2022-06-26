package regras;

import agenda.Agenda;

/**
 * Representa as regras de validações necessárias para manipular um contato da agenda de contatos.
 * @author João Victor dos Santos Oliveira
 *
 */
public class RegrasAgenda {
	
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para um contato
	 * @param index posicao do contato na agenda
	 * @return true caso o index seja inválido e true caso o index esteja ok
	 */
	public static boolean verificaIndex(int index) {
		
		if(index < 0 || index > 99) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para um contato favorito
	 * @param index posicao do contato na lista dos contatos favoritos
	 * @return true caso o index seja inválido e true caso o index esteja ok
	 */
	public static boolean verificaIndexFavorito(int index) {
			
			if(index < 0 || index > 9) {
				return true;
			}
			
			return false;
	}
	
	/**
	 * Verifica se o valor do index esta entre os valores possiveis para uma tag
	 * @param index posicao da tag na lista de tags de um contato
	 * @return true caso o index seja inválido e true caso o index esteja ok
	 */
	public static boolean verificaIndexTags(int index) {
		
		if(index < 0 || index > 4) {
			return true;
		}
		
		return false;
}
	
	
	/**
	 * Verifica se uma string não é vazia
	 * @param string uma Strings 
	 * @return true caso a String seja vazia e true caso a String esteja preenchida
	 */
	public static boolean verificaString(String string) {
		string = string.replace(" ", "");
		if(string.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se existe um contato registrado na posição informada
	 * @param agenda A agenda de contatos.
	 * @param posicao posicao do contato na agenda.
	 * @return true caso o não exista nenhum contato no index passado e false caso exista
	 */
	public static boolean verificaSeExisteContato(Agenda agenda, int posicao) {
		
		if(agenda.getContato(posicao) == null) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Verifica se o contato informado é um contato favorito
	 * @param agenda A agenda de contatos.
	 * @param posicao posicao do contato na agenda.
	 * @return true caso o contato esteja na lista de contatos favoritos e false caso não esteja
	 */
	public static boolean verificaFavorito(Agenda agenda, int posicao) {
		
		if(agenda.getContato(posicao).isFavorito()) {
			return true;
		}
		
		return false;
		
	}
	
}
