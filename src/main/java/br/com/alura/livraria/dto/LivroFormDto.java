package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {
	
	@NotBlank(message = "O título do livro não pode ser nulo nem conter apenas caracteres em branco")
	@Size(min = 10, message = "O título do livro deve conter no mínimo 10 caracteres")
	private String titulo;
	
	@NotNull(message = "A data de lançamento do livro não pode ser nula")
	@PastOrPresent(message = "A data de lançamento do livro deve ser válida")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	
	@NotNull(message = "O número de páginas do livro não pode ser nulo")
	@Min(value = 100, message = "O número de páginas do livro deve ser maior ou igual a 100")
	private Integer numeroPaginas;
	
	@NotNull(message = "O autor do livro não pode ser nulo")
	private Long autorId;

}
