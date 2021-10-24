package br.com.alura.livraria.infra;

public class LivroMesmoTituloException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LivroMesmoTituloException(String mensagem) {
		super(mensagem);
	}

}
