package br.com.alura.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.livraria.dto.QuantidadeLivrosPorAutorDto;
import br.com.alura.livraria.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	@Query("select "
			+ "new br.com.alura.livraria.dto.QuantidadeLivrosPorAutorDto"
			+ "("
			+ "a.nome, "
			+ "(select count(l1.id) from Livro l1 where l1.autor.id = a.id), "
			+ "cast ( ((select count(l2.id) from Livro l2 where l2.autor.id = a.id)/(select count(l3.id) from Livro l3)) as double )"
			+ ") "
			+ "from Autor a")
	public List<QuantidadeLivrosPorAutorDto> relatorioQuantidadeLivrosPorAutor();

}
