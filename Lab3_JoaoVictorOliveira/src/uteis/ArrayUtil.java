package uteis;

public class ArrayUtil {
	
	public static String[] aumentarArrayString(String[] contatos) {
		String contatosNovo[] = new String[contatos.length+1];
		
		for (int i = 0; i < contatosNovo.length-1; i++) {
			contatosNovo[i] = contatos[i];
		}
		
		return contatosNovo;
	}

}
