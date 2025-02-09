package br.com.pedidos.producer;

import br.com.pedidos.dto.PedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FecharPedidoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private FecharPedidoProducer fecharPedidoProducer;

    @Test
    void testFecharPedidoParaFila() throws JsonProcessingException {
        PedidoDto pedidoDto = new PedidoDto();

        fecharPedidoProducer.fecharPedidoParaFila(pedidoDto);

        verify(rabbitTemplate).convertAndSend(
                "fecharPedido-request-exchange",
                "fecharPedido-request-rout-key",
                pedidoDto
        );
    }
}
