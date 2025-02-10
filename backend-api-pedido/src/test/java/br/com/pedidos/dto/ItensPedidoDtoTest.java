package br.com.pedidos.dto;

import br.com.pedidos.model.PedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItensPedidoDtoTest {

    private ItensPedidoDto itensPedidoDto;
    private PedidoDto pedidoDto;

    @BeforeEach
    void setUp() {
        pedidoDto = new PedidoDto();
        pedidoDto.setNumeroPedido(123L);

        itensPedidoDto = new ItensPedidoDto(
                1L,
                pedidoDto,
                123L,
                LocalDate.of(2024, 2, 10),
                "PROD001",
                "Produto Teste",
                2,
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(50.00),
                BigDecimal.valueOf(90.00) // 2 * (50 - 5)
        );
    }

    @Test
    void testGetters() {
        assertEquals(1L, itensPedidoDto.getIdItensPedido());
        assertEquals(pedidoDto, itensPedidoDto.getPedido());
        assertEquals(123L, itensPedidoDto.getNumeroPedido());
        assertEquals(LocalDate.of(2024, 2, 10), itensPedidoDto.getDataCadastro());
        assertEquals("PROD001", itensPedidoDto.getCodigoProduto());
        assertEquals("Produto Teste", itensPedidoDto.getDescricaoProduto());
        assertEquals(2, itensPedidoDto.getQuantidade());
        assertEquals(BigDecimal.valueOf(5.00), itensPedidoDto.getDesconto());
        assertEquals(BigDecimal.valueOf(50.00), itensPedidoDto.getPreco());
        assertEquals(BigDecimal.valueOf(90.00), itensPedidoDto.getValorTotal());
    }

    @Test
    void testSetters() {
        PedidoDto novoPedido = new PedidoDto();
        novoPedido.setNumeroPedido(999L);

        itensPedidoDto.setIdItensPedido(2L);
        itensPedidoDto.setPedido(novoPedido);
        itensPedidoDto.setNumeroPedido(999L);
        itensPedidoDto.setDataCadastro(LocalDate.of(2025, 1, 1));
        itensPedidoDto.setCodigoProduto("PROD002");
        itensPedidoDto.setDescricaoProduto("Outro Produto");
        itensPedidoDto.setQuantidade(5);
        itensPedidoDto.setDesconto(BigDecimal.valueOf(10.00));
        itensPedidoDto.setPreco(BigDecimal.valueOf(100.00));
        itensPedidoDto.setValorTotal(BigDecimal.valueOf(450.00));

        assertEquals(2L, itensPedidoDto.getIdItensPedido());
        assertEquals(novoPedido, itensPedidoDto.getPedido());
        assertEquals(999L, itensPedidoDto.getNumeroPedido());
        assertEquals(LocalDate.of(2025, 1, 1), itensPedidoDto.getDataCadastro());
        assertEquals("PROD002", itensPedidoDto.getCodigoProduto());
        assertEquals("Outro Produto", itensPedidoDto.getDescricaoProduto());
        assertEquals(5, itensPedidoDto.getQuantidade());
        assertEquals(BigDecimal.valueOf(10.00), itensPedidoDto.getDesconto());
        assertEquals(BigDecimal.valueOf(100.00), itensPedidoDto.getPreco());
        assertEquals(BigDecimal.valueOf(450.00), itensPedidoDto.getValorTotal());
    }

    @Test
    void testEmptyConstructor() {
        ItensPedidoDto vazio = new ItensPedidoDto();
        assertNotNull(vazio);
        assertNull(vazio.getIdItensPedido());
        assertNull(vazio.getPedido());
        assertNull(vazio.getNumeroPedido());
        assertNull(vazio.getDataCadastro());
        assertNull(vazio.getCodigoProduto());
        assertNull(vazio.getDescricaoProduto());
        assertNull(vazio.getQuantidade());
        assertNull(vazio.getDesconto());
        assertNull(vazio.getPreco());
        assertNull(vazio.getValorTotal());
    }
}
