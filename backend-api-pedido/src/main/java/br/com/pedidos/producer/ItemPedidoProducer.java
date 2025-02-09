package br.com.pedidos.producer;


import br.com.pedidos.dto.ItensPedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
@Component
public class ItemPedidoProducer {

    final RabbitTemplate rabbitTemplate;

    public ItemPedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarItemPedidoParaFila(ItensPedidoDto itensPedido) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
                "itemPedido-request-exchanges",
                "itemPedido-request-rout-key",
                itensPedido
        );
    }
}
