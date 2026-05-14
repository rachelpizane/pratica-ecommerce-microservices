package edu.rachelpizane.icompras.pedidos.model;

import edu.rachelpizane.icompras.pedidos.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPagamento {
    private String dados;
    private TipoPagamento tipoPagamento;
}
