package br.com.alura.livraria.infra;

public class AutorExcluidoPossuiLivrosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AutorExcluidoPossuiLivrosException(String mensagem) {
		super(mensagem);
	}

}
