package edu.rachelpizane.icompras.pedidos.dto;

import edu.rachelpizane.icompras.pedidos.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento tipoPagamento) {
}
