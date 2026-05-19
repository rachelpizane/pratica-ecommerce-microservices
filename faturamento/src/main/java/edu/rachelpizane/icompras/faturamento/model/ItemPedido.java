package edu.rachelpizane.icompras.faturamento.model;

import java.math.BigDecimal;

public record ItemPedido(
        Long id,
        String descricao,
        BigDecimal valorUnitario,
        Integer quantidade) {

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(this.quantidade).multiply(valorUnitario);
    }
}
