package edu.rachelpizane.icompras.faturamento.dto;

import java.math.BigDecimal;
import java.util.List;

public record DetalhePedidoDTO(
        Long id,
        Long idCliente,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone,
        String dataPedido,
        BigDecimal total,
        List<DetalheItemPedidoDTO> itens
) {
}
