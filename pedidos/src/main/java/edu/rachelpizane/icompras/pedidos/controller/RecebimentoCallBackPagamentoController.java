package edu.rachelpizane.icompras.pedidos.controller;

import edu.rachelpizane.icompras.pedidos.dto.ErrorRespostaDTO;
import edu.rachelpizane.icompras.pedidos.dto.RecebimentoCallBackPagamentoDTO;
import edu.rachelpizane.icompras.pedidos.exception.ValidationException;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
public class RecebimentoCallBackPagamentoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> atualizarStatusPagamento(
            @RequestBody RecebimentoCallBackPagamentoDTO recebimento,
            @RequestHeader(required = true, name = "apiKey") String apiKey) {

        try {
            pedidoService.atualizarStatusPagamento(recebimento);

            return ResponseEntity.ok().build();

        } catch (ValidationException ex) {
            ErrorRespostaDTO errorResposta = new ErrorRespostaDTO(
                    "NAO_ENCONTRADO",
                    ex.getField(),
                    ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResposta);
        }
    }
}
