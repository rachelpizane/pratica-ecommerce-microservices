package edu.rachelpizane.icompras.pedidos.client.representation;

import java.math.BigDecimal;

public record ProdutoRepresentation(
        Long id,
        String nome,
        BigDecimal valorUnitario
) {
}
