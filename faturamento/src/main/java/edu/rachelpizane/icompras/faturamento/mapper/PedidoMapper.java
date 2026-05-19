package edu.rachelpizane.icompras.faturamento.mapper;

import edu.rachelpizane.icompras.faturamento.dto.DetalheItemPedidoDTO;
import edu.rachelpizane.icompras.faturamento.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.faturamento.model.Cliente;
import edu.rachelpizane.icompras.faturamento.model.ItemPedido;
import edu.rachelpizane.icompras.faturamento.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {
    public Pedido map(DetalhePedidoDTO detalhes) {
        Cliente cliente = new Cliente(
                detalhes.idCliente(),
                detalhes.nome(),
                detalhes.cpf(),
                detalhes.logradouro(),
                detalhes.numero(),
                detalhes.bairro(),
                detalhes.email(),
                detalhes.telefone()
        );

        List<ItemPedido> itens =
                detalhes.itens().stream()
                        .map(this::mapItem)
                        .toList();

        return new Pedido(
                detalhes.id(),
                cliente,
                detalhes.dataPedido(),
                detalhes.total(),
                itens
        );
    }

    ItemPedido mapItem(DetalheItemPedidoDTO item) {
        return new ItemPedido(
                item.idProduto(),
                item.nome(),
                item.valorUnitario(),
                item.quantidade());
    }
}
