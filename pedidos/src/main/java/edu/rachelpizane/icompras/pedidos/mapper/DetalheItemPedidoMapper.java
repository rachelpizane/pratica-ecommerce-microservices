package edu.rachelpizane.icompras.pedidos.mapper;

import edu.rachelpizane.icompras.pedidos.model.ItemPedido;
import edu.rachelpizane.icompras.pedidos.dto.DetalheItemPedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalheItemPedidoMapper {

    @Mapping(source = "item.produto.nome", target = "nome")
    @Mapping(source = "valorUnitario", target = "valorUnitario")
    DetalheItemPedidoDTO toDto(ItemPedido item);
}
