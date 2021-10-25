package br.com.alura.livraria.repository;

import java.util.List;
import java.util.Optional;

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
			+ "(select count(l2.id) from Livro l2)"
			+ ") "
			+ "from Autor a")
	public List<QuantidadeLivrosPorAutorDto> relatorioQuantidadeLivrosPorAutor();
	
	public Optional<Autor> findByEmail(String email);

}
