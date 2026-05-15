package edu.rachelpizane.icompras.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApplication {

//    @Bean
//    public CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template) {
//        return args -> template.send("icompras.pedidos-pagos", "dados", "teste");
//    }

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

}
