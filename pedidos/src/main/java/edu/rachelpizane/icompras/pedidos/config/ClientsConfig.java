package edu.rachelpizane.icompras.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "edu.rachelpizane.icompras.pedidos.client")
public class ClientsConfig {
}
