package edu.rachelpizane.icompras.pedidos.validator;

import edu.rachelpizane.icompras.pedidos.client.ClienteClient;
import edu.rachelpizane.icompras.pedidos.client.ProdutoClient;
import edu.rachelpizane.icompras.pedidos.client.representation.ClienteRepresentation;
import edu.rachelpizane.icompras.pedidos.client.representation.ProdutoRepresentation;
import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {
    private final ProdutoClient produtoClient;
    private final ClienteClient clienteClient;

    public void validar(NovoPedidoDTO pedido) {
        validarCliente(pedido.idCliente());
        pedido.itens().forEach(item -> validarItem(item.idProduto()));
    }

    private void validarCliente(Long idCliente) {
        try {
            ResponseEntity<ClienteRepresentation> response = clienteClient.obterDados(idCliente);
            ClienteRepresentation cliente = response.getBody();

            log.info("Cliente de id {} encontrado: {}", cliente.id(), cliente.nome());
        } catch (FeignException.NotFound ex) {
            String mensagem = String.format("Cliente de id %d não encontrado", idCliente);
            log.error(mensagem);

            throw new ValidationException("idCliente", mensagem);
        }
    }

    private void validarItem(Long idProduto) {
        try {
            ResponseEntity<ProdutoRepresentation> response = produtoClient.obterDados(idProduto);
            ProdutoRepresentation produto = response.getBody();

            log.info("Produto de id {} encontrado: {}", produto.id(), produto.nome());
        } catch (FeignException.NotFound ex) {
            String mensagem = String.format("Produto de id %d não encontrado", idProduto);
            log.error(mensagem);

            throw new ValidationException("idProduto", mensagem);
        }
    }
}
