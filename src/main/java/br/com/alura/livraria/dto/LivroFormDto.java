package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LivroFormDto {
	
	@NotBlank
	@Size(min = 10, max = 200)
	private String titulo;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataLancamento;
	
	@NotNull
	@Min(value = 100)
	private Integer numeroPaginas;
	
	@NotNull
	private Long autorId;

}
