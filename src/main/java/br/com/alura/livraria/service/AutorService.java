package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AutorDetalhadoDto;
import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.infra.AutorExcluidoPossuiLivrosException;
import br.com.alura.livraria.infra.EmailAutorEmUsoException;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class AutorService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<AutorDto> listar(Pageable paginacao) {
		
		Page<Autor> autores = autorRepository.findAll(paginacao);
		
		return autores.map(a -> modelMapper.map(a, AutorDto.class));
		
	}
	
	@Transactional
	public AutorDto cadastrar(AutorFormDto autorFormDto) {
		
		Boolean emailEmUso = autorRepository.findByEmail(autorFormDto.getEmail())
				.stream()
				.anyMatch(autor -> autor.getEmail().equals(autorFormDto.getEmail()));
		
		if (emailEmUso) {
			throw new EmailAutorEmUsoException("O email informado já está em uso");
		}
		
		modelMapper.typeMap(AutorFormDto.class, Autor.class).addMappings(mapper -> mapper.skip(Autor::setId));
		Autor autor = modelMapper.map(autorFormDto, Autor.class);
		
		autorRepository.save(autor);
		
		return modelMapper.map(autor, AutorDto.class);
		
	}

	public AutorDetalhadoDto buscar(Long id) {
		
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(autor, AutorDetalhadoDto.class);
	
	}

	@Transactional
	public AutorDto atualizar(Long id, AutorFormDto autorFormDto) {
		
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		autor.atualiza(
				autorFormDto.getNome(), 
				autorFormDto.getEmail(), 
				autorFormDto.getDataNascimento(), 
				autorFormDto.getMinicurriculo());
		
		autorRepository.save(autor);
		
		return modelMapper.map(autor, AutorDto.class);
		
	}

	@Transactional
	public void remover(Long id) {
		
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Boolean autorPossuiLivros = livroRepository.findAll()
				.stream()
				.anyMatch(livro -> livro.getAutor().equals(autor));
		
		if (autorPossuiLivros) {
			throw new AutorExcluidoPossuiLivrosException("O autor possui livros cadastrados");
		}
		
		autorRepository.deleteById(id);
		
	}

}
