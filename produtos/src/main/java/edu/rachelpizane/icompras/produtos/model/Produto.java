package edu.rachelpizane.icompras.produtos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_produtos")
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private Boolean ativo;

    @PrePersist
    private void prePersist() {
        if (ativo == null) {
            ativo = true;
        }
    }
}
