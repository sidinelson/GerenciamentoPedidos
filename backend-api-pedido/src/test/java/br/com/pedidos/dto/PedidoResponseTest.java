package br.com.pedidos.dto;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoResponseTest {

    private PedidoModel pedidoModel;
    private ItensPedidoModel item1, item2;
    private PedidoResponse pedidoResponse;

    @BeforeEach
    void setUp() {
        item1 = new ItensPedidoModel(1L, null, 123L, LocalDate.of(2024, 2, 10),
                "PROD001", "Produto Teste 1", 2, BigDecimal.valueOf(5.00), BigDecimal.valueOf(50.00), BigDecimal.valueOf(90.00));

        item2 = new ItensPedidoModel(2L, null, 123L, LocalDate.of(2024, 2, 10),
                "PROD002", "Produto Teste 2", 1, BigDecimal.valueOf(0.00), BigDecimal.valueOf(30.00), BigDecimal.valueOf(30.00));

        pedidoModel = new PedidoModel();
        pedidoModel.setNumeroPedido(123L);
        pedidoModel.setDataCadastro(LocalDate.of(2024, 2, 10));
        pedidoModel.setSituacao("ABERTO");
        pedidoModel.setDescontoTotal(BigDecimal.valueOf(5.00));
        pedidoModel.setValorTotal(BigDecimal.valueOf(120.00));
        pedidoModel.setItens(List.of(item1, item2));

        pedidoResponse = PedidoResponse.of(pedidoModel);
    }

    @Test
    void testOfMethod() {
        assertNotNull(pedidoResponse);
        assertEquals(pedidoModel.getNumeroPedido(), pedidoResponse.getNumeroPedido());
        assertEquals(pedidoModel.getDataCadastro(), pedidoResponse.getDataCadastro());
        assertEquals(pedidoModel.getSituacao(), pedidoResponse.getSituacao());
        assertEquals(pedidoModel.getDescontoTotal(), pedidoResponse.getDescontoTotal());
        assertEquals(pedidoModel.getValorTotal(), pedidoResponse.getValorTotal());
    }

    @Test
    void testSettersAndGetters() {
        PedidoResponse response = new PedidoResponse();
        response.setNumeroPedido(456L);
        response.setDataCadastro(LocalDate.of(2025, 1, 1));
        response.setSituacao("FINALIZADO");
        response.setDescontoTotal(BigDecimal.valueOf(10.00));
        response.setValorTotal(BigDecimal.valueOf(200.00));
        response.setItens(List.of(item1));

        assertEquals(456L, response.getNumeroPedido());
        assertEquals(LocalDate.of(2025, 1, 1), response.getDataCadastro());
        assertEquals("FINALIZADO", response.getSituacao());
        assertEquals(BigDecimal.valueOf(10.00), response.getDescontoTotal());
        assertEquals(BigDecimal.valueOf(200.00), response.getValorTotal());
        assertEquals(1, response.getItens().size());
        assertEquals(item1, response.getItens().get(0));
    }

    @Test
    void testEmptyConstructor() {
        PedidoResponse vazio = new PedidoResponse();
        assertNotNull(vazio);
        assertNull(vazio.getNumeroPedido());
        assertNull(vazio.getDataCadastro());
        assertNull(vazio.getSituacao());
        assertNull(vazio.getDescontoTotal());
        assertNull(vazio.getValorTotal());
        assertNull(vazio.getItens());
    }
}
