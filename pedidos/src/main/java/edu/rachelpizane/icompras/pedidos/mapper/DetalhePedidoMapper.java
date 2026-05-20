package edu.rachelpizane.icompras.pedidos.mapper;

import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.dto.DetalhePedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetalheItemPedidoMapper.class})
public interface DetalhePedidoMapper {
    @Mapping(source = "cliente.nome", target = "nome")
    @Mapping(source = "cliente.cpf", target = "cpf")
    @Mapping(source = "cliente.logradouro", target = "logradouro")
    @Mapping(source = "cliente.numero", target = "numero")
    @Mapping(source = "cliente.bairro", target = "bairro")
    @Mapping(source = "cliente.email", target = "email")
    @Mapping(source = "cliente.telefone", target = "telefone")
    @Mapping(source = "dataPedido", target = "dataPedido", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "urlNf", target = "urlNotaFiscal")
    DetalhePedidoDTO toDto(Pedido pedido);
}
