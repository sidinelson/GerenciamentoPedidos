package br.com.order.infra;

import br.com.order.dto.ItensPedidoDto;
import br.com.order.model.ItensPedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemPedidoConverterTest {

    private ItemPedidoConverter itemPedidoConverter;
    private ItensPedidoModel itensPedidoModel;
    private ItensPedidoDto itensPedidoDto;

    @BeforeEach
    void setUp() {
        itemPedidoConverter = new ItemPedidoConverter();

        // Criando ItensPedidoModel
        itensPedidoModel = new ItensPedidoModel();
        itensPedidoModel.setIdItensPedido(1L);
        itensPedidoModel.setNumeroPedido(123L);
        itensPedidoModel.setCodigoProduto("ABC123");
        itensPedidoModel.setDescricaoProduto("Produto de Teste");
        itensPedidoModel.setQuantidade(2);
        itensPedidoModel.setPreco(BigDecimal.valueOf(50.0));
        itensPedidoModel.setDesconto(BigDecimal.valueOf(5.0));
        itensPedidoModel.setValorTotal(BigDecimal.valueOf(90.0));  // 2 * (50 - 5)
        itensPedidoModel.setDataCadastro(LocalDate.now());
    }


    @Test
    void testToEntity() {
        // Converte ItensPedidoDto de volta para ItensPedidoModel
        ItensPedidoDto itensPedidoDto = new ItensPedidoDto(
                1L, 123L, LocalDate.now(), "ABC123", "Produto de Teste", 2,
                BigDecimal.valueOf(5.0), BigDecimal.valueOf(50.0), BigDecimal.valueOf(90.0)
        );

        ItensPedidoModel result = itemPedidoConverter.toEntity(itensPedidoDto);

        // Verificando se a conversão está correta
        assertNotNull(result);
        assertEquals(itensPedidoDto.getIdItensPedido(), result.getIdItensPedido());
        assertEquals(itensPedidoDto.getNumeroPedido(), result.getNumeroPedido());
        assertEquals(itensPedidoDto.getCodigoProduto(), result.getCodigoProduto());
        assertEquals(itensPedidoDto.getDescricaoProduto(), result.getDescricaoProduto());
        assertEquals(itensPedidoDto.getQuantidade(), result.getQuantidade());
        assertEquals(itensPedidoDto.getPreco(), result.getPreco());
        assertEquals(itensPedidoDto.getDesconto(), result.getDesconto());
        assertEquals(itensPedidoDto.getValorTotal(), result.getValorTotal());

    }

    @Test
    void testConverterParaDTOComNull() {
        // Teste com ItensPedidoModel nulo
        ItensPedidoDto result = itemPedidoConverter.converterParaDTO(null);
        assertNull(result);
    }

    @Test
    void testToEntityComNull() {
        // Teste com ItensPedidoDto nulo
        ItensPedidoModel result = itemPedidoConverter.toEntity(null);
        assertNull(result);
    }
}
