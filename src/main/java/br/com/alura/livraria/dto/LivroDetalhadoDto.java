package br.com.alura.livraria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDetalhadoDto extends LivroDto {
	
	private AutorDto autor;

}
