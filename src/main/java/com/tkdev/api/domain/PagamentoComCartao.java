package com.tkdev.api.domain;

import javax.persistence.Entity;

import com.tkdev.api.domain.enums.EstadoPagamento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

}
