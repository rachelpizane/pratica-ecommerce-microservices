package edu.rachelpizane.icompras.pedidos.client;


import edu.rachelpizane.icompras.pedidos.client.representation.ProdutoRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtos", url = "${icompras.pedidos.clients.produtos.url}")
public interface ProdutoClient {

    @GetMapping("/{id}")
    ResponseEntity<ProdutoRepresentation> obterDados(@PathVariable Long id);
}
