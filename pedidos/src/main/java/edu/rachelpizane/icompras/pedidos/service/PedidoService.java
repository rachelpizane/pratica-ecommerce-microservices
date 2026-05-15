package edu.rachelpizane.icompras.pedidos.service;

import edu.rachelpizane.icompras.pedidos.client.ServicoBancarioClient;
import edu.rachelpizane.icompras.pedidos.client.representation.ClienteRepresentation;
import edu.rachelpizane.icompras.pedidos.dto.DadosPagamentoDTO;
import edu.rachelpizane.icompras.pedidos.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.dto.RecebimentoCallBackPagamentoDTO;
import edu.rachelpizane.icompras.pedidos.enums.PedidoStatus;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import edu.rachelpizane.icompras.pedidos.mapper.DetalhePedidoMapper;
import edu.rachelpizane.icompras.pedidos.mapper.PedidoMapper;
import edu.rachelpizane.icompras.pedidos.model.DadosPagamento;
import edu.rachelpizane.icompras.pedidos.model.ItemPedido;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.provider.ClienteProvider;
import edu.rachelpizane.icompras.pedidos.provider.ProdutoProvider;
import edu.rachelpizane.icompras.pedidos.publisher.PagamentoPublisher;
import edu.rachelpizane.icompras.pedidos.repository.ItemPedidoRepository;
import edu.rachelpizane.icompras.pedidos.repository.PedidoRepository;
import edu.rachelpizane.icompras.pedidos.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final ItemPedidoRepository itemPedidoRepository;

    private final PedidoValidator validator;

    private final PedidoMapper mapper;
    private final DetalhePedidoMapper detalhePedidomapper;

    private final ServicoBancarioClient servicoBancarioClient;
    private final ClienteProvider clienteProvider;
    private final ProdutoProvider produtoProvider;

    private final PagamentoPublisher publisher;

    @Transactional
    public Pedido criar(NovoPedidoDTO request) {
        validator.validar(request);

        Pedido pedidoSalvo = realizarPersistenciaInicial(request);
        processarPagamento(pedidoSalvo);

        return pedidoSalvo;
    }

    public DetalhePedidoDTO buscarPedidoDetalhado(Long id) {
        Pedido pedido = buscarPedido(id);
        return buscarPedidoDetalhado(pedido);
    }

    public void atualizarStatusPagamento(RecebimentoCallBackPagamentoDTO recebimento) {
        Pedido pedido = buscarPedido(recebimento.codigo(), recebimento.chavePagamento());

        if(Boolean.TRUE.equals(recebimento.status())) {
            pedido.setStatus(PedidoStatus.PAGO);
            repository.save(pedido);
            publisher.publicar(buscarPedidoDetalhado(pedido));
        } else {
            pedido.setStatus(PedidoStatus.ERRO_PAGAMENTO);
            pedido.setObservacoes(recebimento.observacoes());
        }
    }

    @Transactional
    public void adicionarNovoPagamento(Long id, DadosPagamentoDTO dados) {
        Pedido pedido = buscarPedido(id);

        atualizarPagamento(pedido, dados);
        processarPagamento(pedido);

        repository.save(pedido);
    }

    private DetalhePedidoDTO buscarPedidoDetalhado(Pedido pedido) {
        carregarDadosCompletosPedido(pedido);
        return detalhePedidomapper.toDto(pedido);
    }

    private Pedido buscarPedido(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("id",
                        String.format("Pedido não encontrado para o id %d",
                                id)));
    }


    private Pedido buscarPedido(Long id, String chavePagamento) {
        return repository
                .findByIdAndChavePagamento(id, chavePagamento)
                .orElseThrow(() -> new ValidationException("codigo",
                        String.format("Pedido não encontrado para o código %d e chave de pagamento %s",
                                id, chavePagamento)));
    }

    private void atualizarPagamento(Pedido pedido, DadosPagamentoDTO dto) {
        DadosPagamento dados = new DadosPagamento(dto.dados(), dto.tipoPagamento());
        pedido.setDadosPagamento(dados);
        pedido.setStatus(PedidoStatus.REALIZADO);
        pedido.setObservacoes("Novo pagamento realizado, aguardando o novo processamento");
    }

    private Pedido realizarPersistenciaInicial(NovoPedidoDTO request) {
        Pedido pedido = mapper.toEntity(request);
        return repository.save(pedido);
    }

    private void processarPagamento(Pedido pedido) {
        String chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private Pedido carregarDadosCompletosPedido(Pedido pedido) {
        carregarDadosCliente(pedido);
        carregarDadosProduto(pedido);

        return pedido;
    }

    private void carregarDadosCliente(Pedido pedido) {
        ClienteRepresentation cliente = clienteProvider.buscarCliente(pedido.getIdCliente());
        pedido.setCliente(cliente);
    }

    private void carregarDadosProduto(Pedido pedido) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedido(pedido);
        itens.forEach(item -> item.setProduto(produtoProvider.buscarProduto(item.getIdProduto())));
        pedido.removeTodosItemPedido();
        pedido.addTodosItemPedido(itens);
    }
}
