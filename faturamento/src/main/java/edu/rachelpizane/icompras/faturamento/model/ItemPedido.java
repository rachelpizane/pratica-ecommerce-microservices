package edu.rachelpizane.icompras.faturamento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    private Long id;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(this.quantidade).multiply(valorUnitario);
    }
}
