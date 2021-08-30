package com.tkdev.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Cliente
	private String nome;
	private String email;
	private String cpfOuCNPJ;
	private Integer tipo;

	// Endereço
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	// Telefone
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;
}
