package br.com.order.consumer;

import br.com.order.consumers.FecharPedidoConsumer;
import br.com.order.dto.PedidoDto;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.service.PedidoService;
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
class FecharPedidoConsumerTest {

    @InjectMocks
    private FecharPedidoConsumer fecharPedidoConsumer;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PedidoConverter pedidoConverter;

    private PedidoDto pedidoDto;
    private PedidoModel pedidoModel;

    @BeforeEach
    void setUp() {
        pedidoDto = new PedidoDto(123L, 123L, LocalDate.of(2024, 2, 10),
                "ABERTO", BigDecimal.valueOf(10.00), BigDecimal.valueOf(100.00), null);

        pedidoModel = new PedidoModel();
        pedidoModel.setNumeroPedido(123L);
        pedidoModel.setDataCadastro(LocalDate.of(2024, 2, 10));
        pedidoModel.setSituacao("ABERTO");
        pedidoModel.setDescontoTotal(BigDecimal.valueOf(10.00));
        pedidoModel.setValorTotal(BigDecimal.valueOf(100.00));
    }

    @Test
    void testReceberMensagemFechamentoPedido() {
        // Mockando o comportamento do PedidoConverter
        when(pedidoConverter.toEntity(pedidoDto)).thenReturn(pedidoModel);

        // Chamando o método de escuta da fila
        fecharPedidoConsumer.receberMensagemFechamentoPedido(pedidoDto);

        // Verificando se o método de conversão foi chamado
        verify(pedidoConverter, times(1)).toEntity(pedidoDto);

        // Verificando se o método fecharPedido foi chamado com o objeto convertido
        verify(pedidoService, times(1)).fecharPedido(pedidoModel);
    }
}
