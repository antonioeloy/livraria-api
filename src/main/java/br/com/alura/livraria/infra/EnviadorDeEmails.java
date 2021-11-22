package br.com.alura.livraria.infra;

public interface EnviadorDeEmails {

	void enviarEmail(String destinatario, String assunto, String mensagem);

}