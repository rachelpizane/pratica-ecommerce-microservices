package edu.rachelpizane.icompras.logistica.subscriber;

import edu.rachelpizane.icompras.logistica.dto.AtualizacaoPedidoFaturadoDTO;
import edu.rachelpizane.icompras.logistica.service.LogisticaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizacaoPedidoFaturadoSubscriber {

    private final LogisticaService service;

    @KafkaListener(
            topics = "${icompras.config.kafka.topics.pedidos-faturados}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumir(AtualizacaoPedidoFaturadoDTO atualizacao) {
        log.info("Recebendo atualizacao: {}", atualizacao.id());
        service.enviar(atualizacao);
        log.info("Pedido processado com sucesso! Código: {}", atualizacao.id());
        return;
    }
}
