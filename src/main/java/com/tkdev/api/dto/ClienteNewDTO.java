package com.tkdev.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.tkdev.api.services.validation.ClienteInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ClienteInsert
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Cliente
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCNPJ;

	private Integer tipo;

	// Endereço
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;

	private String complemento;

	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	// Telefone
	private String telefone1;

	private String telefone2;

	private String telefone3;

	private Integer cidadeId;
}
