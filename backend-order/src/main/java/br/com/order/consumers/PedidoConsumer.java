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
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private OrderPedidoService orderPedidoService;

    @Autowired
    private PedidoConverter pedidoConverter;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "pedido-request-queue", durable = "true"),
            exchange = @Exchange(name = "pedido-request-exchange"),
            key = "pedido-request-rout-key"),
            ackMode = "MANUAL") // Controle manual do RabbitMQ
    public void receberMensagemPedido(@Payload PedidoDto pedidoDTO,
                                      Channel channel,
                                      @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            PedidoModel pedidoModel = pedidoConverter.toEntity(pedidoDTO);
            orderPedidoService.save(pedidoModel);

            // Confirma manualmente o processamento bem-sucedido
            channel.basicAck(tag, false);
        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());

            // Envia a mensagem para a DLQ após erro
            try {
                channel.basicNack(tag, false, false);
            } catch (Exception ex) {
                System.err.println("Erro ao enviar para DLQ: " + ex.getMessage());
            }
        }
    }
}
