package edu.rachelpizane.icompras.pedidos.dto;

import edu.rachelpizane.icompras.pedidos.enums.PedidoStatus;

public record AtualizacaoStatusPedidoDTO(
        Long id,
        PedidoStatus status,
        String urlNotaFiscal,
        String codigoRastreio
) {
}
