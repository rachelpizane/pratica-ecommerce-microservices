package edu.rachelpizane.icompras.pedidos.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.rachelpizane.icompras.pedidos.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.pedidos.mapper.DetalhePedidoMapper;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DetalhePedidoMapper mapper;
    private final ObjectMapper objectMapper;

    @Value("${icompras.config.kafka.topics.pedidos-pagos}")
    private String topico;

    public void publicar(DetalhePedidoDTO detalhes) {
        log.info("Publicando pedido pago {}", detalhes.id());

        try {
            String json = objectMapper.writeValueAsString(detalhes);
            kafkaTemplate.send(topico, "dados", json);
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar o json", e);
        } catch (RuntimeException e) {
            log.error("Erro técnico ao publicar no tópico de pedidos", e);
        }
    }
}
