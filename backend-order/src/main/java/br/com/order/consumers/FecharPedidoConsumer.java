package br.com.order.consumers;

import br.com.order.dto.PedidoDto;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.service.OrderPedidoService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FecharPedidoConsumer {
    @Autowired
    private OrderPedidoService orderPedidoService;

    @Autowired
    PedidoConverter pedidoConverter;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("fecharPedido-request-queue"),
            exchange = @Exchange(name = "fecharPedido-request-exchange"),
            key = "fecharPedido-request-rout-key"))
    public void receberMensagemFechamentoPedido(@Payload PedidoDto pedidoDTO) {
        var pedidoModel = new PedidoModel();
        pedidoModel = pedidoConverter.toEntity(pedidoDTO);

        System.out.println("Fechar Pedido"+pedidoDTO);

        orderPedidoService.fecharPedido(pedidoModel);
    }
}
