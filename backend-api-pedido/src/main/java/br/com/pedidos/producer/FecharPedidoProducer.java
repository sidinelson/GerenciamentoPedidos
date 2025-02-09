package br.com.pedidos.producer;


import br.com.pedidos.dto.PedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class FecharPedidoProducer {
    final RabbitTemplate rabbitTemplate;
    public FecharPedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void fecharPedidoParaFila(PedidoDto pedido) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
                "fecharPedido-request-exchange",
                "fecharPedido-request-rout-key",
                pedido
        );
    }
}
