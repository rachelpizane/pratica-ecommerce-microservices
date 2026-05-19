package edu.rachelpizane.icompras.faturamento.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.rachelpizane.icompras.faturamento.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.faturamento.service.NotaFiscalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoPagoSubscriber {

    private final ObjectMapper mapper;
    private final NotaFiscalService service;

    @KafkaListener(
            topics = "${icompras.config.kafka.topics.pedidos-pagos}",
            groupId = "${icompras.config.kafka.group-id}")
    public void listen(String json) {
        try {
            log.info("Recebendo pedido para faturamento");
            DetalhePedidoDTO detalhes = mapper.readValue(json, DetalhePedidoDTO.class);
            service.gerar(detalhes);
        } catch (Exception e) {
            log.error("Erro na consumação do topico de pedidos pagos");
        }
    }

}
