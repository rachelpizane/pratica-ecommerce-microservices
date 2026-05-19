package edu.rachelpizane.icompras.faturamento.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("${icompras.config.kafka.topics.pedidos-faturados}")
    private String topicoFaturado;

    @Bean
    public NewTopic criarTopicoPedidosFaturados() {
        return TopicBuilder.name(topicoFaturado)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
