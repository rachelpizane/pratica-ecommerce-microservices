package edu.rachelpizane.icompras.pedidos.provider;

import edu.rachelpizane.icompras.pedidos.client.ClienteClient;
import edu.rachelpizane.icompras.pedidos.client.representation.ClienteRepresentation;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteProvider {
    private final ClienteClient client;

    public ClienteRepresentation buscarCliente(Long idCliente) {
        try {
            ResponseEntity<ClienteRepresentation> response = client.obterDados(idCliente);
            ClienteRepresentation cliente = response.getBody();

            log.info("Cliente de id {} encontrado: {}", cliente.id(), cliente.nome());

            return cliente;

        } catch (FeignException.NotFound ex) {
            String mensagem = String.format("Cliente de id %d não encontrado", idCliente);
            log.error(mensagem);

            throw new ValidationException("idCliente", mensagem);
        }
    }
}
