package br.com.alura.livraria.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class EnviadorDeEmailReal implements EnviadorDeEmails {
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(destinatario);
		email.setSubject(assunto);
		email.setText(mensagem);
		
		mailSender.send(email);
		
	}
	
}
