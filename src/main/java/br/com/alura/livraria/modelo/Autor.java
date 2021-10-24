package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private LocalDate dataNascimento;
	
	private String minicurriculo;
	
	public void atualiza(String nome, String email, LocalDate dataNascimento, String minicurriculo) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.minicurriculo = minicurriculo;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Autor autor = (Autor) obj;
		
		if (this.id.equals(autor.id)) {
			return true;
		}
		
		return false;
		
	}

}
