package edu.rachelpizane.icompras.pedidos.service;

import edu.rachelpizane.icompras.pedidos.dto.AtualizacaoStatusPedidoDTO;
import edu.rachelpizane.icompras.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {
    private final PedidoRepository repository;

    public void atualizarStatus(AtualizacaoStatusPedidoDTO atualizacao) {
        return;
    }
}
