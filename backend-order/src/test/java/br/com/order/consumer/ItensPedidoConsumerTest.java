package br.com.order.consumer;

import br.com.order.consumers.ItensPedidoConsumer;
import br.com.order.dto.ItensPedidoDto;
import br.com.order.dto.PedidoDto;
import br.com.order.infra.ItemPedidoConverter;
import br.com.order.model.ItensPedidoModel;
import br.com.order.service.ItensPedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensPedidoConsumerTest {

    @InjectMocks
    private ItensPedidoConsumer itensPedidoConsumer;

    @Mock
    private ItensPedidoService itensPedidoService;

    @Mock
    private ItemPedidoConverter itemPedidoConverter;

    private ItensPedidoDto itensPedidoDto;
    private ItensPedidoModel itensPedidoModel;

    @BeforeEach
    void setUp() {
        itensPedidoDto = new ItensPedidoDto(
                1L, (PedidoDto) null, 123L, LocalDate.now(),
                "P001", "Produto Teste", 2,
                BigDecimal.valueOf(5.00), BigDecimal.valueOf(50.00), BigDecimal.valueOf(95.00)
        );

        itensPedidoModel = new ItensPedidoModel();
        itensPedidoModel.setIdItensPedido(1L);
        itensPedidoModel.setNumeroPedido(123L);
        itensPedidoModel.setCodigoProduto("P001");
        itensPedidoModel.setDescricaoProduto("Produto Teste");
        itensPedidoModel.setQuantidade(2);
        itensPedidoModel.setDesconto(BigDecimal.valueOf(5.00));
        itensPedidoModel.setPreco(BigDecimal.valueOf(50.00));
        itensPedidoModel.setValorTotal(BigDecimal.valueOf(95.00));
    }

    @Test
    void testReceberMensagemItensPedido() {
        // Simula a conversão do DTO para entidade
        when(itemPedidoConverter.toEntity(itensPedidoDto)).thenReturn(itensPedidoModel);

        // Executa o método que escuta mensagens da fila
        itensPedidoConsumer.receberMensagemItensPedido(itensPedidoDto);

        // Verifica se a conversão foi chamada corretamente
        verify(itemPedidoConverter, times(1)).toEntity(itensPedidoDto);

        // Verifica se o serviço de salvar foi chamado corretamente
        verify(itensPedidoService, times(1)).save(itensPedidoModel);
    }
}
