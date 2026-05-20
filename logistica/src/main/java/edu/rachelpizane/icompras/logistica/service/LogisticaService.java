package edu.rachelpizane.icompras.logistica.service;

import edu.rachelpizane.icompras.logistica.dto.AtualizacaoPedidoEnviadoDTO;
import edu.rachelpizane.icompras.logistica.dto.AtualizacaoPedidoFaturadoDTO;
import edu.rachelpizane.icompras.logistica.enums.PedidoStatus;
import edu.rachelpizane.icompras.logistica.publisher.EnvioPedidoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class LogisticaService {

    private final EnvioPedidoPublisher publisher;

    public void enviar(AtualizacaoPedidoFaturadoDTO atualizacao) {
        AtualizacaoPedidoEnviadoDTO pedidoEnviado = new AtualizacaoPedidoEnviadoDTO(
                atualizacao.id(),
                PedidoStatus.ENVIADO,
                gerarCodigoRastreio());

        publisher.publicar(pedidoEnviado);
    }

    private String gerarCodigoRastreio() {
        Random random = new Random();

        char letra1 = (char) ('A' + random.nextInt(26));
        char letra2 = (char) ('A' + random.nextInt(26));

        int numeros = 100000000 + random.nextInt(900000000);

        return "" + letra1 + letra2 + numeros + "BR";
    }
}
