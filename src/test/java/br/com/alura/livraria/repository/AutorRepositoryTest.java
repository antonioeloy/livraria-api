package br.com.alura.livraria.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.QuantidadeLivrosPorAutorDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioQuantidadeDeLivrosPorAutor() {
		
		Autor andreDaSilva = new Autor(
				"André da Silva",
				"email@autor.com.br",
				LocalDate.parse("1989-01-01"),
				"Obras do autor"
				);
		em.persist(andreDaSilva);
		
		Autor fernandaNogueira = new Autor(
				"Fernanda Nogueira",
				"email@autor.com.br",
				LocalDate.parse("1989-01-01"),
				"Obras do autor"
				);
		em.persist(fernandaNogueira);
		
		Autor julianaCarvalho = new Autor(
				"Juliana Carvalho",
				"email@autor.com.br",
				LocalDate.parse("1989-01-01"),
				"Obras do autor"
				);
		em.persist(julianaCarvalho);
		
		Autor ritaDeAssis = new Autor(
				"Rita de Assis",
				"email@autor.com.br",
				LocalDate.parse("1989-01-01"),
				"Obras do autor"
				);
		em.persist(ritaDeAssis);
		
		Autor rodrigoDeSouza = new Autor(
				"Rodrigo de Souza",
				"email@autor.com.br",
				LocalDate.parse("1989-01-01"),
				"Obras do autor"
				);
		em.persist(rodrigoDeSouza);
		
		Livro aprendaJava = new Livro(
				"Aprenda Java em 21 dias",
				LocalDate.parse("2004-03-12"),
				100,
				andreDaSilva);
		em.persist(aprendaJava);
		
		Livro comoSerMaisProdutivo = new Livro(
				"Como ser mais produtivo",
				LocalDate.parse("2004-04-21"),
				100,
				fernandaNogueira);
		em.persist(comoSerMaisProdutivo);
		
		Livro aprendaFalarEmPublico = new Livro(
				"Aprenda a falar em público",
				LocalDate.parse("2004-07-01"),
				100,
				julianaCarvalho);
		em.persist(aprendaFalarEmPublico);
		
		Livro otimizandoSeuTempo = new Livro(
				"Otimizando seu tempo",
				LocalDate.parse("2004-12-10"),
				100,
				fernandaNogueira);
		em.persist(otimizandoSeuTempo);
		
		Livro comoFazerBolosIncriveis = new Livro(
				"Como fazer bolos incríveis",
				LocalDate.parse("2009-09-12"),
				100,
				ritaDeAssis);
		em.persist(comoFazerBolosIncriveis);
		
		Livro investindoEmAcoesNaBolsaDeValores = new Livro(
				"Investindo em ações na bolsa de valores",
				LocalDate.parse("2010-12-21"),
				100,
				rodrigoDeSouza);
		em.persist(investindoEmAcoesNaBolsaDeValores);
		
		Livro aprendaPython = new Livro(
				"Aprenda python em 12 dias",
				LocalDate.parse("2012-01-01"),
				100,
				andreDaSilva);
		em.persist(aprendaPython);
		
		List<QuantidadeLivrosPorAutorDto> relatorio = autorRepository.relatorioQuantidadeLivrosPorAutor();
		
		Assertions.assertThat(relatorio)
		.hasSize(5)
		.extracting(QuantidadeLivrosPorAutorDto::getAutor, QuantidadeLivrosPorAutorDto::getQuantidadeLivros, QuantidadeLivrosPorAutorDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("André da Silva", 2L, new BigDecimal("28.57")),
				Assertions.tuple("Fernanda Nogueira", 2L, new BigDecimal("28.57")),
				Assertions.tuple("Juliana Carvalho", 1L, new BigDecimal("14.29")),
				Assertions.tuple("Rita de Assis", 1L, new BigDecimal("14.29")),
				Assertions.tuple("Rodrigo de Souza", 1L, new BigDecimal("14.29"))
				);
		
	}

}
