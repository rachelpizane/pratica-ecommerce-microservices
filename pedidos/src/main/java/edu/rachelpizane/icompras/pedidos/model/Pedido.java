package edu.rachelpizane.icompras.pedidos.model;

import edu.rachelpizane.icompras.pedidos.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idCliente;

    @Column(nullable = false)
    private LocalDateTime dataPedido;

    private String chavePagamento;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PedidoStatus status;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal total;

    private String codigoRastreio;

    private String urlNf;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        if (dataPedido == null) {
            dataPedido = LocalDateTime.now();
        }
        if (status == null) {
            status = PedidoStatus.REALIZADO;
        }
    }
}
