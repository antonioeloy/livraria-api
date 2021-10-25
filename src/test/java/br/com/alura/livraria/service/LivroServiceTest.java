package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.infra.LivroMesmoTituloException;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private AutorRepository autorRepository;

	@Mock
	private LivroRepository livroRepository;
	
	@InjectMocks
	private LivroService livroService;
	
	private Autor criarAutor() {
		
		Autor autor = new Autor(
				1L,
				"Antonio Eloy", 
				"antonio.eloy@email.com.br", 
				LocalDate.parse("1989-06-13"), 
				"Obras do autor");
		
		return autor;
		
	}
	
	private Livro criarLivro() {
		
		Livro livro = new Livro(
				1L,
				"Aprenda java em 21 dias", 
				LocalDate.parse("2021-01-01"), 
				100, 
				criarAutor());
		
		return livro;
		
	}
	
	private LivroFormDto criarLivroFormDto() {
		
		LivroFormDto livroFormDto = new LivroFormDto(
				"Aprenda java em 21 dias", 
				LocalDate.parse("2021-01-01"), 
				100, 
				1L);
		
		return livroFormDto;
		
	}

	@Test
	void deveCadastrarLivro() {
		
		LivroFormDto livroFormDto = criarLivroFormDto();
		
		List<Livro> listaVaziaDeLivrosComMesmoTitulo = List.of();
		
		Mockito
		.when(livroRepository.findByTitulo(livroFormDto.getTitulo()))
		.thenReturn(listaVaziaDeLivrosComMesmoTitulo);
		
		LivroDto livroDto = livroService.cadastrar(livroFormDto);
		
		Mockito
		.verify(livroRepository).save(Mockito.any());
		
		assertEquals(livroDto.getTitulo(), livroFormDto.getTitulo());
		assertEquals(livroDto.getDataLancamento(), livroFormDto.getDataLancamento());
		assertEquals(livroDto.getNumeroPaginas(), livroFormDto.getNumeroPaginas());
		
	}
	
	@Test
	void naoDeveCadastrarLivroPoisExisteOutroLivroCadastradoComMesmoTitulo() {
		
		LivroFormDto livroFormDto = criarLivroFormDto();
		
		Livro livroComMesmoTitulo = criarLivro();
		
		List<Livro> listaComLivroComMesmoTitulo = List.of(livroComMesmoTitulo);
		
		Mockito
		.when(livroRepository.findByTitulo(livroFormDto.getTitulo()))
		.thenReturn(listaComLivroComMesmoTitulo);
		
		assertThrows(LivroMesmoTituloException.class, () -> livroService.cadastrar(livroFormDto));
		
	}

}
