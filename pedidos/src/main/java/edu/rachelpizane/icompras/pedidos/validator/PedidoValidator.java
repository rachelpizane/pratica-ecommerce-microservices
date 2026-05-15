package edu.rachelpizane.icompras.pedidos.validator;

import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.provider.ClienteProvider;
import edu.rachelpizane.icompras.pedidos.provider.ProdutoProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {
    private final ProdutoProvider produtoProvider;
    private final ClienteProvider clienteProvider;

    public void validar(NovoPedidoDTO pedido) {
        validarCliente(pedido.idCliente());
        pedido.itens().forEach(item -> validarItem(item.idProduto()));
    }

    private void validarCliente(Long idCliente) {
        clienteProvider.buscarCliente(idCliente);
    }

    private void validarItem(Long idProduto) {
        produtoProvider.buscarProduto(idProduto);
    }
}
