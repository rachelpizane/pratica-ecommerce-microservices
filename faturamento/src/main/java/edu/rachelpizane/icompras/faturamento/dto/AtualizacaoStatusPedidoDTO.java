package edu.rachelpizane.icompras.faturamento.dto;

import edu.rachelpizane.icompras.faturamento.enums.PedidoStatus;

public record AtualizacaoStatusPedidoDTO(Long id, PedidoStatus status, String urlNotaFiscal) {
}
