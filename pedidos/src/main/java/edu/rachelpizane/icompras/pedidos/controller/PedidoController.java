package edu.rachelpizane.icompras.pedidos.controller;

import edu.rachelpizane.icompras.pedidos.dto.NovoPedidoDTO;
import edu.rachelpizane.icompras.pedidos.model.Pedido;
import edu.rachelpizane.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody NovoPedidoDTO request) {
        Pedido pedido = service.criar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.getId());
    }
}
