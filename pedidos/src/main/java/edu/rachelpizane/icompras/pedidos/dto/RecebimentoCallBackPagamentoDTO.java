package edu.rachelpizane.icompras.pedidos.dto;

public record RecebimentoCallBackPagamentoDTO(
        Long codigo,
        String chavePagamento,
        Boolean status,
        String observacoes
) {
}
