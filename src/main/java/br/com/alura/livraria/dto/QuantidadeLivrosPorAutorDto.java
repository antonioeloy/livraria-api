package br.com.alura.livraria.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;

@Getter
public class QuantidadeLivrosPorAutorDto {
	
	private String autor;
	
	private Long quantidadeLivros;
	
	private BigDecimal percentual;
	
	public QuantidadeLivrosPorAutorDto(String autor, Long quantidadeLivros, Long quantidadeTotal) {
		this.autor = autor;
		this.quantidadeLivros = quantidadeLivros;
		this.percentual = new BigDecimal(quantidadeLivros)
				.divide(new BigDecimal(quantidadeTotal), 4, RoundingMode.HALF_UP)
				.multiply(new BigDecimal("100"))
				.setScale(2);
	}

}
