package edu.rachelpizane.icompras.pedidos.subscriber;

import edu.rachelpizane.icompras.pedidos.dto.AtualizacaoStatusPedidoDTO;
import edu.rachelpizane.icompras.pedidos.service.AtualizacaoStatusPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoSubscriber {

    private final AtualizacaoStatusPedidoService service;

    @KafkaListener(
            topics = {
                    "${icompras.config.kafka.topics.pedidos-faturados}",
                    "${icompras.config.kafka.topics.pedidos-enviados}"
            },
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumir(AtualizacaoStatusPedidoDTO atualizacao) {
        log.info("Recebendo atualizacao: {}", atualizacao.id());

        try {
            service.atualizarStatus(atualizacao);
            log.info("Pedido ID: {} atualizado para {} com sucesso!", atualizacao.id(), atualizacao.status());

        } catch (Exception e) {
            log.error("Erro ao atualizar status pedido: {}", atualizacao.id());
        }

    }

}
