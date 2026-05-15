package edu.rachelpizane.icompras.pedidos.dto;

import edu.rachelpizane.icompras.pedidos.enums.PedidoStatus;

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
        PedidoStatus status,
        List<DetalheItemPedidoDTO> itens
) {
}
