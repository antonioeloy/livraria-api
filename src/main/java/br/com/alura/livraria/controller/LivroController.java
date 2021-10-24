package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public Page<LivroDto> listar(Pageable paginacao) {
		return livroService.listar(paginacao);
	}
	
	@PostMapping
	public ResponseEntity<LivroDto> cadastrar(@RequestBody @Valid LivroFormDto livroFormDto, 
			UriComponentsBuilder uriBuilder) {
		LivroDto livroDto = livroService.cadastrar(livroFormDto);
		URI uri = uriBuilder
				.path("/livros/{id}")
				.buildAndExpand(livroDto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(livroDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> buscar(@PathVariable Long id) {
		LivroDto livroDto = livroService.buscar(id);
		return ResponseEntity.ok(livroDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDto> atualizar(@PathVariable Long id, @RequestBody @Valid LivroFormDto livroFormDto) {
		LivroDto livroDto = livroService.atualizar(id, livroFormDto);
		return ResponseEntity.ok(livroDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remover(@PathVariable Long id) {
		livroService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
}
