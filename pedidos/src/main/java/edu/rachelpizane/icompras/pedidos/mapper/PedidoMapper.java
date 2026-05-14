package edu.rachelpizane.icompras.pedidos.mapper;

import edu.rachelpizane.icompras.pedidos.dto.DadosPagamentoDTO;
import edu.rachelpizane.icompras.pedidos.dto.ItemPedidoDTO;
import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.model.ItemPedido;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ItemPedidoMapper.class}
)
public interface PedidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "dataPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", constant = "REALIZADO")
    Pedido toEntity(NovoPedidoDTO dto);

    List<ItemPedido> toItemPedidoEntities(List<ItemPedidoDTO> itens);

    @AfterMapping
    default void afterMappingEntity(NovoPedidoDTO dto, @MappingTarget Pedido pedido) {
        if (dto == null) return;

        adicionarItens(dto.itens(), pedido);
        calcularTotal(pedido);
    }

    private void adicionarItens(List<ItemPedidoDTO> itensDTO, Pedido pedido) {
        if (itensDTO == null) return;

        List<ItemPedido> itens = toItemPedidoEntities(itensDTO);
        pedido.addTodosItemPedido(itens);
    }

    private void calcularTotal(Pedido pedido) {
        BigDecimal total = pedido.getItens().stream()
                .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setTotal(total);
    }
}
