package br.com.pedidos.producer;

import br.com.pedidos.dto.PedidoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {
    final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedidoParaFila(PedidoDTO pedido) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
                "pedido-request-exchange",
                "pedido-request-rout-key",
                pedido
        );
    }
}
