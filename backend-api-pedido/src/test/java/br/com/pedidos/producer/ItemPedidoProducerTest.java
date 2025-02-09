package br.com.pedidos.producer;

import br.com.pedidos.dto.ItensPedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemPedidoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ItemPedidoProducer itemPedidoProducer;

    @Test
    void testEnviarItemPedidoParaFila() throws JsonProcessingException {
        ItensPedidoDto itensPedidoDto = new ItensPedidoDto();

        itemPedidoProducer.enviarItemPedidoParaFila(itensPedidoDto);

        verify(rabbitTemplate).convertAndSend(
                "itemPedido-request-exchanges",
                "itemPedido-request-rout-key",
                itensPedidoDto
        );
    }
}