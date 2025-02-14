package br.com.pedidos.repository;


import br.com.pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    @Query("SELECT p FROM PedidoModel p LEFT JOIN FETCH p.itens WHERE p.numeroPedido = :numeroPedido")
    PedidoModel buscarPedidoComItens(@Param("numeroPedido") Long numeroPedido);
    boolean existsByNumeroPedido(Long numeroPedido);

}
