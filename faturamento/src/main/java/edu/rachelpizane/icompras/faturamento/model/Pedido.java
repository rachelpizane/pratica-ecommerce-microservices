package edu.rachelpizane.icompras.faturamento.model;

import java.math.BigDecimal;
import java.util.List;

public record Pedido(
        Long id,
        Cliente cliente,
        String dataPedido,
        BigDecimal total,
        List<ItemPedido> itens) {
}
