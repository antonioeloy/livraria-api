package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	private String criarJsonAutor(String nome, String email, String dataNascimento, String minicurriculo) {
		
		String json = "{" +
				"\"nome\": \"" + nome + "\", " +
				"\"email\": \"" + email + "\", " +
				"\"dataNascimento\": \"" + dataNascimento + "\", " +
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
	
	private String criarJsonLivro(String titulo, String dataLancamento, String numeroPaginas, String autorId) {
		
		String json = "{" +
				"\"titulo\": \"" + titulo + "\", " +
				"\"dataLancamento\": \"" + dataLancamento + "\", " +
				"\"numeroPaginas\": " + numeroPaginas + ", " +
				"\"autorId\": " + autorId +
				"}";
				
		return json;
				
	}
	
	private String criarJsonLivroIncompleto(String titulo, String dataLancamento, String numeroPaginas) {
		
		String json = "{" +
				"\"titulo\": \"" + titulo + "\", " +
				"\"dataLancamento\": \"" + dataLancamento + "\", " +
				"\"numeroPaginas\": " + numeroPaginas +
				"}";
				
		return json;
				
	}
	
	private String criarJsonLivroRetorno(String titulo, String dataLancamento, String numeroPaginas) {
		
		String json = "{" +
				"\"titulo\": \"" + titulo + "\", " +
				"\"dataLancamento\": \"" + dataLancamento + "\", " +
				"\"numeroPaginas\": " + numeroPaginas +
				"}";
				
		return json;
				
	}

	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		
		String jsonAutor = criarJsonAutor(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				"1989-06-13", 
				"Obras do autor"
				);
		
		String jsonAutorRetorno = criarJsonAutorRetorno(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				"13/06/1989"
				);
		
		MvcResult resultadoPostAutor = mvc
			.perform(
					post("/autores")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonAutor)
					)
			.andExpect(
					status().isCreated()
					)
			.andExpect(
					header().exists("Location")
					)
			.andExpect(
					content().json(jsonAutorRetorno)
					)
			.andReturn();
		
		String location = resultadoPostAutor.getResponse().getHeader("Location");
		
		String idAutor = location.substring(location.lastIndexOf("/") + 1);
		
		String jsonLivro = criarJsonLivro(
				"Aprenda java em 21 dias", 
				"2021-01-01", 
				String.valueOf(100), 
				idAutor
				);
		
		String jsonLivroRetorno = criarJsonLivroRetorno(
				"Aprenda java em 21 dias", 
				"01/01/2021", 
				String.valueOf(100)
				);
		
		mvc
		.perform(
				post("/livros")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonLivro)
				)
		.andExpect(
				status().isCreated()
				)
		.andExpect(
				header().exists("Location")
				)
		.andExpect(
				content().json(jsonLivroRetorno)
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		
		String jsonLivro = criarJsonLivroIncompleto(
				"Aprenda java em 21 dias", 
				"2021-01-01", 
				String.valueOf(100)
				);
		
		mvc
		.perform(
				post("/livros")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonLivro)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}

}
