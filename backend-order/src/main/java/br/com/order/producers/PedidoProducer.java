package br.com.order.producers;

import br.com.order.dto.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedido(PedidoDTO pedido) {
        rabbitTemplate.convertAndSend(
                "pedido-response-sucesso-exchanges",
                "pedido-response-sucesso-rout-key",
                pedido);
    }
}
