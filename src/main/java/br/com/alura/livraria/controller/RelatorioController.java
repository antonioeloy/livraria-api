package br.com.alura.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.QuantidadeLivrosPorAutorDto;
import br.com.alura.livraria.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/quantidade-livros-autor")
	public List<QuantidadeLivrosPorAutorDto> relatorioQuantidadeLivrosPorAutor() {
		return relatorioService.relatorioQuantidadeLivrosPorAutor();
	}

}
