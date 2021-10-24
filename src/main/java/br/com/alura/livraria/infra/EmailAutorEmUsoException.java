package br.com.alura.livraria.infra;

public class EmailAutorEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailAutorEmUsoException(String mensagem) {
		super(mensagem);
	}

}
