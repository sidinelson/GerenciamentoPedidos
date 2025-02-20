package br.com.order.consumers;

import br.com.order.dto.PedidoDto;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.service.OrderPedidoService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private OrderPedidoService orderPedidoService;

    @Autowired
    private PedidoConverter pedidoConverter;
    @Autowired
    private PedidoDLQConsumer pedidoDLQConsumer;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("pedido-request-queue"),
            exchange = @Exchange(name = "pedido-request-exchange"),
            key = "pedido-request-rout-key"))
    public void receberMensagemPedido(@Payload PedidoDto pedidoDTO) {
        try {
            // Confirma manualmente o processamento bem-sucedido
            PedidoModel pedidoModel = pedidoConverter.toEntity(pedidoDTO);
            orderPedidoService.save(pedidoModel);

        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());
            //pedidoDLQConsumer.processarMensagensDLQ(pedidoDTO);
            // Envia a mensagem para a DLQ após erro
            rabbitTemplate.convertAndSend("pedido-exchange-dlq", "pedido-dlq-rout-key", pedidoDTO);
        }
    }
}
