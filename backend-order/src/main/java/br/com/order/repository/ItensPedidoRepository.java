package br.com.order.repository;

import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Long> {
    @Query("FROM ItensPedidoModel i WHERE i.numeroPedido =:numeroPedido  ")//JPQL
    Optional<ItensPedidoModel> existeNumeroControle(@Param("numeroPedido") Long  numeroPedido);
    @Query("FROM ItensPedidoModel i WHERE i.numeroPedido =:numeroPedido")//JPQL
    List<ItensPedidoModel> findByItensPedidos(@Param("numeroPedido") Long  numeroPedido);

    @Query(" SELECT i, p.numeroOrder, p.descontoTotal, p.valorTotal FROM ItensPedidoModel i "
            + " LEFT JOIN FETCH i.pedido p"
            + " WHERE i.numeroPedido = :numeroPedido  "
            + " ORDER BY i.dataCadastro ASC")//JPQL
    List<ItensPedidoModel> findBylistaNumeroPedidosItens(@Param("numeroPedido") Long numeroPedido);

}
