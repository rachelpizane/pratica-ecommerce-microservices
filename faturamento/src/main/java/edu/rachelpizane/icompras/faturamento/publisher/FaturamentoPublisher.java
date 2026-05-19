package edu.rachelpizane.icompras.faturamento.publisher;

import edu.rachelpizane.icompras.faturamento.dto.AtualizacaoStatusPedidoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FaturamentoPublisher {
    private final KafkaTemplate<String, AtualizacaoStatusPedidoDTO> kafkaTemplate;

    @Value("${icompras.config.kafka.topics.pedidos-faturados}")
    private String topico;

    public void publicar(AtualizacaoStatusPedidoDTO atualizacao) {
        log.info("Publicando pedido faturado {}", atualizacao.id());

        try {
            kafkaTemplate.send(topico, "dados", atualizacao);
        } catch (RuntimeException e) {
            log.error("Erro técnico ao publicar no tópico de pedidos", e);
        }
    }
}
