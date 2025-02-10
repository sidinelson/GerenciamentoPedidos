package br.com.pedidos.dto;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemPedidoResponseTest {

    private ItensPedidoModel itensPedidoModel;
    private PedidoModel pedidoModel;

    @BeforeEach
    void setUp() {
        pedidoModel = new PedidoModel();
        pedidoModel.setNumeroOrder(1001L);

        itensPedidoModel = new ItensPedidoModel();
        itensPedidoModel.setIdItensPedido(1L);
        itensPedidoModel.setNumeroPedido(123L);
        itensPedidoModel.setPedido(pedidoModel);
        itensPedidoModel.setCodigoProduto("PROD001");
        itensPedidoModel.setDescricaoProduto("Produto de Teste");
        itensPedidoModel.setQuantidade(2);
        itensPedidoModel.setPreco(BigDecimal.valueOf(50.00));
        itensPedidoModel.setDesconto(BigDecimal.valueOf(5.00));
        itensPedidoModel.setValorTotal(BigDecimal.valueOf(90.00)); // 2 * (50 - 5)
        itensPedidoModel.setDataCadastro(LocalDate.now());
    }

    @Test
    void testOf_ConvertsItensPedidoModelToItemPedidoResponse() {
        ItemPedidoResponse response = ItemPedidoResponse.of(itensPedidoModel);

        assertNotNull(response);
        assertEquals(itensPedidoModel.getIdItensPedido(), response.getIdItensPedido());
        assertEquals(itensPedidoModel.getNumeroPedido(), response.getNumeroPedido());
        assertEquals(itensPedidoModel.getPedido(), response.getNumeroOrder());
        assertEquals(itensPedidoModel.getCodigoProduto(), response.getCodigoProduto());
        assertEquals(itensPedidoModel.getDescricaoProduto(), response.getDescricaoProduto());
        assertEquals(itensPedidoModel.getQuantidade(), response.getQuantidade());
        assertEquals(itensPedidoModel.getPreco(), response.getPreco());
        assertEquals(itensPedidoModel.getDesconto(), response.getDesconto());
        assertEquals(itensPedidoModel.getValorTotal(), response.getValorTotal());
        assertEquals(itensPedidoModel.getDataCadastro(), response.getDataCadastro());
    }

}
