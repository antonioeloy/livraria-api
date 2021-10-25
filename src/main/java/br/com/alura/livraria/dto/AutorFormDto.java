package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
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
public class AutorFormDto {
	
	@NotBlank
	@Size(min = 2, max = 100)
	private String nome;
	
	@NotBlank
	@Email
	@Size(min = 2, max = 50)
	private String email;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;
	
	@NotBlank
	@Size(min = 2, max = 200)
	private String minicurriculo;

}
