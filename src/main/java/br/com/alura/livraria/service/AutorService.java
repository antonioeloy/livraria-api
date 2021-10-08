package br.com.alura.livraria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<AutorDto> listar() {
		List<Autor> autores = autorRepository.findAll();
		return autores
				.stream()
				.map(a -> modelMapper.map(a, AutorDto.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void cadastrar(AutorFormDto autorFormDto) {
		modelMapper.typeMap(AutorFormDto.class, Autor.class).addMappings(mapper -> mapper.skip(Autor::setId));
		Autor autor = modelMapper.map(autorFormDto, Autor.class);
		autorRepository.save(autor);
	}

}
