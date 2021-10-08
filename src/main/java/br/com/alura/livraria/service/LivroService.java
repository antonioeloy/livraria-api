package br.com.alura.livraria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(l -> modelMapper.map(l, LivroDto.class));
	}
	
	@Transactional
	public void cadastrar(LivroFormDto livroFormDto) {
		modelMapper.typeMap(LivroFormDto.class, Livro.class).addMappings(mapper -> mapper.skip(Livro::setId));
		Livro livro = modelMapper.map(livroFormDto, Livro.class);
		livroRepository.save(livro);
	}
	
}
