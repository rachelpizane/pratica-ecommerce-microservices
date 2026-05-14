package edu.rachelpizane.icompras.pedidos.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        Long idProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) { }
