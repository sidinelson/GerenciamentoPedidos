package br.com.pedidos.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDtoTest {

    private PedidoDto pedidoDto;
    private ItensPedidoDto item1, item2;

    @BeforeEach
    void setUp() {
        item1 = new ItensPedidoDto(1L, (PedidoDto) null, 123L, LocalDate.of(2024, 2, 10),
                "PROD001", "Produto Teste 1", 2, BigDecimal.valueOf(5.00), BigDecimal.valueOf(50.00), BigDecimal.valueOf(90.00));

        item2 = new ItensPedidoDto(2L, (PedidoDto) null, 123L, LocalDate.of(2024, 2, 10),
                "PROD002", "Produto Teste 2", 1, BigDecimal.valueOf(0.00), BigDecimal.valueOf(30.00), BigDecimal.valueOf(30.00));

        pedidoDto = new PedidoDto(
                100L,
                123L,
                LocalDate.of(2024, 2, 10),
                "ABERTO",
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(120.00),
                List.of(item1, item2)
        );
    }

    @Test
    void testGetters() {
        assertEquals(100L, pedidoDto.getNumeroOrder());
        assertEquals(123L, pedidoDto.getNumeroPedido());
        assertEquals(LocalDate.of(2024, 2, 10), pedidoDto.getDataCadastro());
        assertEquals("ABERTO", pedidoDto.getSituacao());
        assertEquals(BigDecimal.valueOf(5.00), pedidoDto.getDescontoTotal());
        assertEquals(BigDecimal.valueOf(120.00), pedidoDto.getValorTotal());
        assertEquals(2, pedidoDto.getItens().size());
        assertTrue(pedidoDto.getItens().contains(item1));
        assertTrue(pedidoDto.getItens().contains(item2));
    }

    @Test
    void testSetters() {
        ItensPedidoDto novoItem = new ItensPedidoDto(3L, (PedidoDto) null, 999L, LocalDate.of(2025, 1, 1),
                "PROD003", "Produto Novo", 3, BigDecimal.valueOf(2.00), BigDecimal.valueOf(40.00), BigDecimal.valueOf(114.00));

        pedidoDto.setNumeroOrder(200L);
        pedidoDto.setNumeroPedido(999L);
        pedidoDto.setDataCadastro(LocalDate.of(2025, 1, 1));
        pedidoDto.setSituacao("FINALIZADO");
        pedidoDto.setDescontoTotal(BigDecimal.valueOf(10.00));
        pedidoDto.setValorTotal(BigDecimal.valueOf(200.00));
        pedidoDto.setItens(List.of(novoItem));

        assertEquals(200L, pedidoDto.getNumeroOrder());
        assertEquals(999L, pedidoDto.getNumeroPedido());
        assertEquals(LocalDate.of(2025, 1, 1), pedidoDto.getDataCadastro());
        assertEquals("FINALIZADO", pedidoDto.getSituacao());
        assertEquals(BigDecimal.valueOf(10.00), pedidoDto.getDescontoTotal());
        assertEquals(BigDecimal.valueOf(200.00), pedidoDto.getValorTotal());
        assertEquals(1, pedidoDto.getItens().size());
        assertEquals(novoItem, pedidoDto.getItens().get(0));
    }

    @Test
    void testEmptyConstructor() {
        PedidoDto vazio = new PedidoDto();
        assertNotNull(vazio);
        assertNull(vazio.getNumeroOrder());
        assertNull(vazio.getNumeroPedido());
        assertNull(vazio.getDataCadastro());
        assertNull(vazio.getSituacao());
        assertNull(vazio.getDescontoTotal());
        assertNull(vazio.getValorTotal());
        assertNull(vazio.getItens());
    }
}
