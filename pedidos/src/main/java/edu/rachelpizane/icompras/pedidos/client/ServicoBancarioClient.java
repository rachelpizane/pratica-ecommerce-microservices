package edu.rachelpizane.icompras.pedidos.client;

import edu.rachelpizane.icompras.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ServicoBancarioClient {

    public String solicitarPagamento(Pedido pedido) {
        log.info("Solicitando pagamento para o pedido: {}", pedido.getId());
        return UUID.randomUUID().toString();
    }
}
