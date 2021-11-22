package br.com.alura.livraria.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "test"})
public class EnviadorDeEmailDevTest implements EnviadorDeEmails {
	
	@Override
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		
		System.out.println("ENVIADO E-MAIL:");
		System.out.println("Destinatário: " + destinatario);
		System.out.println("Assunto: " + assunto);
		System.out.println("Mensagem: " + mensagem);
		
	}
	
}
