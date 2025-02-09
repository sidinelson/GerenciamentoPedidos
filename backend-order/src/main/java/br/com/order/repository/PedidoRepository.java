package br.com.order.repository;


import br.com.order.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    @Query("SELECT p FROM PedidoModel p LEFT JOIN FETCH p.itens WHERE p.numeroPedido = :numeroPedido")//JPQL
    Optional<PedidoModel> findBynumeroOrder(@Param("numeroPedido") Long numeroPedido);

    @Query("FROM PedidoModel p WHERE p.numeroPedido =:numeroPedido")//JPQL
    PedidoModel findByPedido(@Param("numeroPedido") Long numeroPedido);

    @Query("SELECT ID FROM PedidoModel p WHERE p.numeroPedido =:numeroPedido")//JPQL
    Long findBynumeroPedido(@Param("numeroPedido") Long numeroPedido);
    @Query(" SELECT i, p.dataCadastro, p.descontoTotal, p.valorTotal FROM ItensPedidoModel i "
            + " JOIN i.pedido p"
            + " WHERE p.numeroPedido = :numeroPedido  "
            + " ORDER BY i.dataCadastro ASC")//JPQL
    List<PedidoModel> findByPedidosItens(@Param("numeroPedido") Long numeroPedido);

    @Query("SELECT p FROM PedidoModel p LEFT JOIN FETCH p.itens WHERE p.numeroPedido = :numeroPedido")
    Optional<PedidoModel> buscarPedidoComItens(@Param("numeroPedido") Long numeroPedido);
    boolean existsByNumeroPedido(Long numeroPedido);

}
