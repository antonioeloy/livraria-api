package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autores")
@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@ApiOperation("Lista todos os autores")
	@GetMapping
	public Page<AutorDto> listar(Pageable paginacao) {
		return autorService.listar(paginacao);
	}
	
	@ApiOperation("Cadastra um autor")
	@PostMapping
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto autorFormDto, 
			UriComponentsBuilder uriBuilder) {
		AutorDto autorDto = autorService.cadastrar(autorFormDto);
		URI uri = uriBuilder
				.path("/autores/{id}")
				.buildAndExpand(autorDto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(autorDto);
	}
	
	@ApiOperation("Retorna os dados de um autor")
	@GetMapping("/{id}")
	public ResponseEntity<AutorDto> buscar(@PathVariable @NotNull Long id) {
		AutorDto autorDto = autorService.buscar(id);
		return ResponseEntity.ok(autorDto);
	}
	
	@ApiOperation("Atualiza os dados de um autor")
	@PutMapping("/{id}")
	public ResponseEntity<AutorDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid AutorFormDto autorFormDto) {
		AutorDto autorDto = autorService.atualizar(id, autorFormDto);
		return ResponseEntity.ok(autorDto);
	}
	
	@ApiOperation("Exclui um autor")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remover(@PathVariable @NotNull Long id) {
		autorService.remover(id);
		return ResponseEntity.noContent().build();
	}

}
