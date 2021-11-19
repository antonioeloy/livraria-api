package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroDetalhadoDto;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.infra.LivroMesmoTituloException;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LivroRepository livroRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable paginacao) {
		
		Page<Livro> livros = livroRepository.findAll(paginacao);
		
		return livros.map(l -> modelMapper.map(l, LivroDto.class));
		
	}
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto livroFormDto) {
		
		Boolean existeLivroMesmoTitulo = livroRepository.findByTitulo(livroFormDto.getTitulo())
				.stream()
				.anyMatch(livro -> livro.getTitulo().equals(livroFormDto.getTitulo()));
		
		if (existeLivroMesmoTitulo) {
			throw new LivroMesmoTituloException("Um livro com o mesmo título já foi cadastrado");
		}
		
		modelMapper.typeMap(LivroFormDto.class, Livro.class).addMappings(mapper -> mapper.skip(Livro::setId));
		Livro livro = modelMapper.map(livroFormDto, Livro.class);
		
		livroRepository.save(livro);
		
		return modelMapper.map(livro, LivroDto.class);
		
	}

	public LivroDetalhadoDto buscar(Long id) {
		
		Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		return modelMapper.map(livro, LivroDetalhadoDto.class);
		
	}

	@Transactional
	public LivroDto atualizar(Long id, @Valid LivroFormDto livroFormDto) {
		
		Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		livro.atualizar(
				livroFormDto.getTitulo(), 
				livroFormDto.getDataLancamento(), 
				livroFormDto.getNumeroPaginas(), 
				autorRepository.getById(livroFormDto.getAutorId()));
		
		livroRepository.save(livro);
		
		return modelMapper.map(livro, LivroDto.class);
		
	}

	@Transactional
	public void remover(Long id) {
		
		Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		livroRepository.delete(livro);
		
	}
	
	
}
