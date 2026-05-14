package edu.rachelpizane.icompras.pedidos.service;

import edu.rachelpizane.icompras.pedidos.client.ServicoBancarioClient;
import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.mapper.PedidoMapper;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.repository.ItemPedidoRepository;
import edu.rachelpizane.icompras.pedidos.repository.PedidoRepository;
import edu.rachelpizane.icompras.pedidos.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final PedidoValidator validator;
    private final PedidoMapper mapper;
    private final ServicoBancarioClient servicoBancarioClient;

    @Transactional
    public Pedido criar(NovoPedidoDTO request) {
        validator.validar(request);

        Pedido pedidoSalvo = realizarPersistenciaInicial(request);
        processarPagamento(pedidoSalvo);

        return pedidoSalvo;
    }
    private Pedido realizarPersistenciaInicial(NovoPedidoDTO request) {
        Pedido pedido = mapper.toEntity(request);
        return repository.save(pedido);
    }

    private void processarPagamento(Pedido pedido) {
        String chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }
}
