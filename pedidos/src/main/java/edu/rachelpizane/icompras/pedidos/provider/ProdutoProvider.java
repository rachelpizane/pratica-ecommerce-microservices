package edu.rachelpizane.icompras.pedidos.provider;

import edu.rachelpizane.icompras.pedidos.client.ClienteClient;
import edu.rachelpizane.icompras.pedidos.client.ProdutoClient;
import edu.rachelpizane.icompras.pedidos.client.representation.ProdutoRepresentation;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProdutoProvider {
    private final ProdutoClient client;

    public ProdutoRepresentation buscarProduto(Long idProduto) {
        try {
            ResponseEntity<ProdutoRepresentation> response = client.obterDados(idProduto);
            ProdutoRepresentation produto = response.getBody();

            log.info("Produto de id {} encontrado: {}", produto.id(), produto.nome());

            return produto;

        } catch (FeignException.NotFound ex) {
            String mensagem = String.format("Produto de id %d não encontrado", idProduto);
            log.error(mensagem);

            throw new ValidationException("idProduto", mensagem);
        }
    }
}
