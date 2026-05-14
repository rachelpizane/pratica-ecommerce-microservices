package edu.rachelpizane.icompras.pedidos.mapper;

import edu.rachelpizane.icompras.pedidos.dto.ItemPedidoDTO;
import edu.rachelpizane.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    @Mapping(target = "id", ignore = true)
    ItemPedido toEntity(ItemPedidoDTO dto);
}
