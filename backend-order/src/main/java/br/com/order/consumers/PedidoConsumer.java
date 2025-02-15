package br.com.order.consumers;

import br.com.order.dto.PedidoDto;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.service.OrderPedidoService;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;

@Component
public class PedidoConsumer {
    @Autowired
    private OrderPedidoService orderPedidoService;

    @Autowired
    PedidoConverter pedidoConverter;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("pedido-request-queue"),
            exchange = @Exchange(name = "pedido-request-exchange"),
            key = "pedido-request-rout-key"))
    public void receberMensagemPedido(@Payload PedidoDto pedidoDTO) {
        var pedidoModel = new PedidoModel();
        pedidoModel = pedidoConverter.toEntity(pedidoDTO);

        System.out.println("OK"+pedidoDTO);
        System.out.println("pedidoModel - OK"+pedidoModel);
        orderPedidoService.save(pedidoModel);
    }

}

