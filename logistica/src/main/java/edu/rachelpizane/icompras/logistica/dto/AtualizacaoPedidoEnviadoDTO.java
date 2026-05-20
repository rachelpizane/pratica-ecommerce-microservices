package edu.rachelpizane.icompras.logistica.dto;

import edu.rachelpizane.icompras.logistica.enums.PedidoStatus;

public record AtualizacaoPedidoEnviadoDTO(
        Long id,
        PedidoStatus status,
        String codigoRastreio) {
}
