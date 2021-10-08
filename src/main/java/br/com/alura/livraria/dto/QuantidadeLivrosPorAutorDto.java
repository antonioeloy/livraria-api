package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuantidadeLivrosPorAutorDto {
	
	private String autor;
	
	private Long quantidadeLivros;
	
	private Double percentual;

}
