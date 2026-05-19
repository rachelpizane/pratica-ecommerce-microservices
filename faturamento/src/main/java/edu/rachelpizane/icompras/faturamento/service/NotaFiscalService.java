package edu.rachelpizane.icompras.faturamento.service;

import edu.rachelpizane.icompras.faturamento.dto.DetalheItemPedidoDTO;
import edu.rachelpizane.icompras.faturamento.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.faturamento.mapper.PedidoMapper;
import edu.rachelpizane.icompras.faturamento.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaFiscalService {
    private final PedidoMapper mapper;

    public void gerar(DetalhePedidoDTO detalhes) {
        Pedido pedido = mapper.map(detalhes);

        log.info("Gerada nota fiscal para o pedido {} ", detalhes.id());
    }
}
