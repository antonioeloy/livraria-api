package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {
	
	@NotBlank(message = "O nome do autor não pode ser nulo nem conter apenas caracteres em branco")
	private String nome;
	
	@NotBlank(message = "O email do autor não pode ser nulo nem conter apenas caracteres em branco")
	@Email(message = "O email do autor deve ser válido")
	private String email;
	
	@NotNull(message = "A data de nascimento do autor não pode ser nula")
	@PastOrPresent(message = "A data de nascimento do autor deve ser válida")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O minicurrículo do autor não pode ser nulo nem conter apenas caracteres em branco")
	private String minicurriculo;

}
