package br.com.pedidos.producer;

import br.com.pedidos.dto.PedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PedidoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PedidoProducer pedidoProducer;

    @Test
    public void testEnviarPedidoParaFila() throws JsonProcessingException {
        // Arrange
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroPedido(123L); // Ajuste conforme os campos do seu DTO
        pedidoDto.setValorTotal(BigDecimal.TEN); // Ajuste conforme os campos do seu DTO

        // Act
        pedidoProducer.enviarPedidoParaFila(pedidoDto);

        // Assert
        Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(
                "pedido-request-exchange",
                "pedido-request-rout-key",
                pedidoDto
        );
    }
}
