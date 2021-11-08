package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.infra.security.TokenService;
import br.com.alura.livraria.modelo.Perfil;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.PerfilRepository;
import br.com.alura.livraria.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private TokenService tokenService;
	
	private String token;
	
	private String criarJsonAutor(String nome, String email, String dataNascimento, String minicurriculo) {
		
		String json = "{" +
				"\"nome\": \"" + nome + "\", " +
				"\"email\": \"" + email + "\", " +
				"\"dataNascimento\": \"" + dataNascimento + "\", " +
				"\"minicurriculo\": \"" + minicurriculo + "\"" +
				"}";
				
		return json;
				
	}
	
	private String criarJsonAutorIncompleto(String nome, String email, String minicurriculo) {
		
		String json = "{" +
				"\"nome\": \"" + nome + "\", " +
				"\"email\": \"" + email + "\", " +
				"\"minicurriculo\": \"" + minicurriculo + "\"" +
				"}";
				
		return json;
				
	}
	
	private String criarJsonAutorRetorno(String nome, String email, String dataNascimento) {
		
		String json = "{" +
				"\"nome\": \"" + nome + "\", " +
				"\"email\": \"" + email + "\", " +
				"\"dataNascimento\": \"" + dataNascimento + "\"" +
				"}";
				
		return json;
				
	}
	
	@BeforeEach
	private void gerarToken() {
		Usuario logado = new Usuario("Antonio", "antonio", "123456");
		Perfil admin = perfilRepository.findById(1L).get();
		logado.adicionarPerfil(admin);
		usuarioRepository.save(logado);
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado, logado.getLogin());
		this.token = tokenService.gerarToken(authentication);
		
	}

	@Test
	void deveriaCadastrarAutorComDadosCompletos() throws Exception {
		
		String json = criarJsonAutor(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				"1989-06-13", 
				"Obras do autor"
				);
		
		String jsonRetorno = criarJsonAutorRetorno(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				"13/06/1989"
				);
		
		mvc
		.perform(
				post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.header("Authorization", token)
				)
		.andExpect(
				status().isCreated()
				)
		.andExpect(
				header().exists("Location")
				)
		.andExpect(
				content().json(jsonRetorno)
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
		
		String json = criarJsonAutorIncompleto(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				"Obras do autor"
				);
		
		mvc
		.perform(
				post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.header("Authorization", token)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}

}
