package edu.rachelpizane.icompras.pedidos.model;

import edu.rachelpizane.icompras.pedidos.client.representation.ProdutoRepresentation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

    @Transient
    ProdutoRepresentation produto;
}