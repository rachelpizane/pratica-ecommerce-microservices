package edu.rachelpizane.icompras.logistica.dto;

import edu.rachelpizane.icompras.logistica.enums.PedidoStatus;

public record AtualizacaoPedidoFaturadoDTO(
        Long id,
        PedidoStatus status,
        String urlNotaFiscal) {
}
