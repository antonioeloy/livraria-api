package br.com.alura.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.QuantidadeLivrosPorAutorDto;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class RelatorioService {
	
	@Autowired
	private AutorRepository autorRepository;

	public List<QuantidadeLivrosPorAutorDto> relatorioQuantidadeLivrosPorAutor() {
		return autorRepository.relatorioQuantidadeLivrosPorAutor();
	}

}
