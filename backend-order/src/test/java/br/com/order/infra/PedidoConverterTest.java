package br.com.order.infra;

import br.com.order.dto.ItensPedidoDto;
import br.com.order.dto.PedidoDto;
import br.com.order.model.ItensPedidoModel;
import br.com.order.model.PedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoConverterTest {

    private PedidoConverter pedidoConverter;
    private PedidoModel pedidoModel;
    private PedidoDto pedidoDto;

    @BeforeEach
    void setUp() {
        pedidoConverter = new PedidoConverter();

        // Criando PedidoModel com ItensPedidoModel
        pedidoModel = new PedidoModel();
        pedidoModel.setNumeroOrder(1L);
        pedidoModel.setNumeroPedido(123L);
        pedidoModel.setDataCadastro(LocalDate.now());
        pedidoModel.setSituacao("PROCESSANDO");
        pedidoModel.setDescontoTotal(BigDecimal.valueOf(10.0));
        pedidoModel.setValorTotal(BigDecimal.valueOf(200.0));

        // Criando ItensPedidoModel e associando ao PedidoModel
        ItensPedidoModel item = new ItensPedidoModel();
        item.setIdItensPedido(1L);
        item.setNumeroPedido(123L);
        item.setCodigoProduto("ABC123");
        item.setDescricaoProduto("Produto de Teste");
        item.setQuantidade(2);
        item.setPreco(BigDecimal.valueOf(50.0));
        item.setDesconto(BigDecimal.valueOf(5.0));
        item.setValorTotal(BigDecimal.valueOf(90.0));  // 2 * (50 - 5)

        pedidoModel.setItens(List.of(item));  // Associa o item ao pedido
    }

    @Test
    void testConverterParaDTO() {
        // Converte PedidoModel para PedidoDto
        PedidoDto result = pedidoConverter.converterParaDTO(pedidoModel);

        // Verificando se a conversão está correta
        assertNotNull(result);
        assertEquals(pedidoModel.getNumeroOrder(), result.getNumeroOrder());
        assertEquals(pedidoModel.getNumeroPedido(), result.getNumeroPedido());
        assertEquals(pedidoModel.getDataCadastro(), result.getDataCadastro());
        assertEquals(pedidoModel.getSituacao(), result.getSituacao());
        assertEquals(pedidoModel.getDescontoTotal(), result.getDescontoTotal());
        assertEquals(pedidoModel.getValorTotal(), result.getValorTotal());

        // Verificando os itens
        assertNotNull(result.getItens());
        assertEquals(1, result.getItens().size());
        ItensPedidoDto itemDto = result.getItens().get(0);
        assertEquals("ABC123", itemDto.getCodigoProduto());
        assertEquals("Produto de Teste", itemDto.getDescricaoProduto());
        assertEquals(2, itemDto.getQuantidade());
        assertEquals(BigDecimal.valueOf(90.0), itemDto.getValorTotal());  // 2 * (50 - 5)
    }

    @Test
    void testToEntity() {
        // Convertendo PedidoDto de volta para PedidoModel
        PedidoDto pedidoDto = new PedidoDto(
                1L, 123L, LocalDate.now(), "PROCESSANDO", BigDecimal.valueOf(10.0),
                BigDecimal.valueOf(200.0), List.of(new ItensPedidoDto(1L, 123L, "ABC123", "Produto de Teste", 2, BigDecimal.valueOf(5.0), BigDecimal.valueOf(50.0), BigDecimal.valueOf(90.0)))
        );

        PedidoModel result = pedidoConverter.toEntity(pedidoDto);

        // Verificando se a conversão está correta
        assertNotNull(result);
        assertEquals(pedidoDto.getNumeroOrder(), result.getNumeroOrder());
        assertEquals(pedidoDto.getNumeroPedido(), result.getNumeroPedido());
        assertEquals(pedidoDto.getDataCadastro(), result.getDataCadastro());
        assertEquals(pedidoDto.getSituacao(), result.getSituacao());
        assertEquals(pedidoDto.getDescontoTotal(), result.getDescontoTotal());
        assertEquals(pedidoDto.getValorTotal(), result.getValorTotal());

        // Verificando os itens
        assertNotNull(result.getItens());
        assertEquals(1, result.getItens().size());
        ItensPedidoModel itemModel = result.getItens().get(0);

    }

    @Test
    void testConverterParaDTOComNull() {
        // Teste com pedido nulo
        PedidoDto result = pedidoConverter.converterParaDTO(null);
        assertNull(result);
    }

    @Test
    void testToEntityComNull() {
        // Teste com PedidoDto nulo
        PedidoModel result = pedidoConverter.toEntity(null);
        assertNull(result);
    }
}
