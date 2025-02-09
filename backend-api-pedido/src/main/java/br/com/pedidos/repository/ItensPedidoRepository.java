package br.com.pedidos.repository;

import br.com.pedidos.model.ItensPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Long> {
    @Query("FROM ItensPedidoModel i WHERE i.numeroPedido =:numeroPedido")//JPQL
    List<ItensPedidoModel> findByItensPedidosPedidos(@Param("numeroPedido") Long  numeroPedido);
    @Query(" SELECT i, p.numeroOrder, p.descontoTotal, p.valorTotal FROM ItensPedidoModel i "
            + " LEFT JOIN FETCH i.pedido p"
            + " WHERE i.numeroPedido = :numeroPedido  "
            + " ORDER BY i.dataCadastro ASC")//JPQL
    List<ItensPedidoModel> findBylistaNumeroPedidosItens(@Param("numeroPedido") Long numeroPedido);

}
