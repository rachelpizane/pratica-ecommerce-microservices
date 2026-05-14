package edu.rachelpizane.icompras.pedidos.controller;

import edu.rachelpizane.icompras.pedidos.dto.DadosPagamentoDTO;
import edu.rachelpizane.icompras.pedidos.dto.ErrorRespostaDTO;
import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO request) {
        try {
            Pedido pedido = service.criar(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(pedido.getId());

        } catch (ValidationException ex) {
            ErrorRespostaDTO errorResposta = new ErrorRespostaDTO(
                    "DADOS_INVALIDOS",
                    ex.getField(),
                    ex.getMessage());

            return ResponseEntity.badRequest().body(errorResposta);
        }
    }

    @PostMapping("/{id}/pagamentos")
    public ResponseEntity<Object> atualizarPagamento(
            @PathVariable Long id,
            @RequestBody DadosPagamentoDTO request) {

        try {
            service.adicionarNovoPagamento(id, request);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (ValidationException ex) {
            ErrorRespostaDTO errorResposta = new ErrorRespostaDTO(
                    "NAO_ENCONTRADO",
                    ex.getField(),
                    ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResposta);
        }
    }
}
