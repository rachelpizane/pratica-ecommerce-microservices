package edu.rachelpizane.icompras.pedidos.service;

import edu.rachelpizane.icompras.pedidos.dto.AtualizacaoStatusPedidoDTO;
import edu.rachelpizane.icompras.pedidos.enums.PedidoStatus;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {
    private final PedidoRepository repository;

    @Transactional
    public void atualizarStatus(AtualizacaoStatusPedidoDTO atualizacao) {
        repository.findById(atualizacao.id())
                .ifPresent(pedido -> atualizarStatus(pedido, atualizacao));
    }

    private void atualizarStatus(Pedido pedido, AtualizacaoStatusPedidoDTO atualizacao) {
        PedidoStatus novoStatus = atualizacao.status();
        pedido.setStatus(novoStatus);

        switch (novoStatus) {
            case FATURADO -> pedido.setUrlNf(atualizacao.urlNotaFiscal());
            case ENVIADO -> pedido.setCodigoRastreio(atualizacao.codigoRastreio());
        }
    }
}
