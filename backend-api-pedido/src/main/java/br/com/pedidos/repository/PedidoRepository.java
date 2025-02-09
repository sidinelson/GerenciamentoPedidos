package br.com.pedidos.repository;


import br.com.pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    @Query("FROM PedidoModel p WHERE p.numeroPedido =:numeroPedido")//JPQL
    PedidoModel findByPedido(@Param("numeroPedido") Long numeroPedido);
    @Query("SELECT ID FROM PedidoModel p WHERE p.numeroPedido =:numeroPedido")//JPQL
    Long findBynumeroIDPedido(@Param("numeroPedido") Long numeroPedido);
    @Query("SELECT p FROM PedidoModel p LEFT JOIN FETCH p.itens WHERE p.numeroPedido = :numeroPedido")
    PedidoModel buscarPedidoComItens(@Param("numeroPedido") Long numeroPedido);

    boolean existsByNumeroPedido(Long numeroPedido);


}
