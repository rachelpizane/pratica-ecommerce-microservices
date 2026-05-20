package edu.rachelpizane.icompras.logistica.publisher;

import edu.rachelpizane.icompras.logistica.dto.AtualizacaoPedidoEnviadoDTO;
import edu.rachelpizane.icompras.logistica.dto.AtualizacaoPedidoFaturadoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvioPedidoPublisher {
    private final KafkaTemplate<String, AtualizacaoPedidoEnviadoDTO> kafkaTemplate;

    @Value("${icompras.config.kafka.topics.pedidos-enviados}")
    private String topico;

    public void publicar(AtualizacaoPedidoEnviadoDTO atualizacao) {
        log.info("Publicando pedido enviado {}", atualizacao.id());

        try {
            kafkaTemplate.send(topico, "dados", atualizacao);
        } catch (RuntimeException e) {
            log.error("Erro técnico ao publicar no tópico de pedidos enviados: ", e);
        }
    }
}