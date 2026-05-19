package edu.rachelpizane.icompras.faturamento.dto;

import java.math.BigDecimal;

public record DetalheItemPedidoDTO(
        Long idProduto,
        String nome,
        Integer quantidade,
        BigDecimal valorUnitario
) {
    public BigDecimal getTotal(){
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
