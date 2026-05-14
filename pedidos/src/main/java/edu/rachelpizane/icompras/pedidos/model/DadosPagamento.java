package edu.rachelpizane.icompras.pedidos.model;

import edu.rachelpizane.icompras.pedidos.enums.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPagamento {
    private String dados;
    private TipoPagamento tipoPagamento;
}
