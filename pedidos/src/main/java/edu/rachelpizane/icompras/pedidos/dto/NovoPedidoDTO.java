package edu.rachelpizane.icompras.pedidos.dto;

import java.math.BigDecimal;
import java.util.List;

public record NovoPedidoDTO(
        Long idCliente,
        DadosPagamentoDTO dadosPagamento,
        List<ItemPedidoDTO> itens
) { }
