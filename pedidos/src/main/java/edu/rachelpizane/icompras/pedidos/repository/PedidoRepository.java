package edu.rachelpizane.icompras.pedidos.repository;

import edu.rachelpizane.icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByIdAndChavePagamento(Long id, String chavePagamento);
}
