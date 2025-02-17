package br.com.order.consumer;

import br.com.order.consumers.PedidoConsumer;
import br.com.order.dto.PedidoDto;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.service.OrderPedidoService;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoConsumerTest {

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    @Mock
    private OrderPedidoService orderPedidoService;

    @Mock
    private PedidoConverter pedidoConverter;

    private PedidoDto pedidoDto;
    private PedidoModel pedidoModel;

    @BeforeEach
    void setUp() {
        pedidoDto = new PedidoDto(
                1001L, 2002L, LocalDate.now(),
                "ABERTO", BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(90.00), null
        );

        pedidoModel = new PedidoModel();
        pedidoModel.setNumeroPedido(2002L);
        pedidoModel.setDataCadastro(LocalDate.now());
        pedidoModel.setSituacao("ABERTO");
        pedidoModel.setDescontoTotal(BigDecimal.valueOf(10.00));
        pedidoModel.setValorTotal(BigDecimal.valueOf(90.00));
    }

    @Test
    void testReceberMensagemPedido() {
        // Simula a conversão do DTO para Model
        when(pedidoConverter.toEntity(pedidoDto)).thenReturn(pedidoModel);

        // Executa o método que recebe a mensagem


        // Verifica se o conversor foi chamado corretamente
        verify(pedidoConverter, times(1)).toEntity(pedidoDto);

        // Verifica se o serviço de salvar foi chamado corretamente
        verify(orderPedidoService, times(1)).save(pedidoModel);
    }
}
