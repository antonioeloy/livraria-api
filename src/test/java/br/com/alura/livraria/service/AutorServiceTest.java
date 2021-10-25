package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.infra.EmailAutorEmUsoException;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private LivroRepository livroRepository;
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private AutorService autorService;
	
	private Autor criarAutor() {
		
		Autor autor = new Autor(
				1L,
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				LocalDate.parse("1989-06-13"), 
				"Obras do autor");
		
		return autor;
		
	}
	
	private Optional<Autor> criarOptionalAutor() {
		return Optional.of(criarAutor());
	}
	
	private AutorFormDto criarAutorFormDto() {
		
		AutorFormDto autorFormDto = new AutorFormDto(
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				LocalDate.parse("1989-06-13"), 
				"Obras do autor");
		
		return autorFormDto;
	}

	@Test
	void deveriaCadastrarAutor() {
		
		AutorFormDto autorFormDto = criarAutorFormDto();
		
		Optional<Autor> vazio = Optional.empty();
		
		Mockito
		.when(autorRepository.findByEmail(autorFormDto.getEmail()))
		.thenReturn(vazio);
		
		AutorDto autorDto = autorService.cadastrar(autorFormDto);
		
		Mockito
		.verify(autorRepository).save(Mockito.any());
		
		assertEquals(autorDto.getNome(), autorFormDto.getNome());
		assertEquals(autorDto.getEmail(), autorFormDto.getEmail());
		assertEquals(autorDto.getDataNascimento(), autorFormDto.getDataNascimento());
		
	}
	
	@Test
	void naoDeveriaCadastrarAutorCujoEmailJaEstaEmUso() {
		
		AutorFormDto autorFormDto = criarAutorFormDto();
		
		Optional<Autor> autor = criarOptionalAutor();	
		
		Mockito
		.when(autorRepository.findByEmail(autorFormDto.getEmail()))
		.thenReturn(autor);
		
		assertThrows(EmailAutorEmUsoException.class, () -> autorService.cadastrar(autorFormDto));
	
	}

}
