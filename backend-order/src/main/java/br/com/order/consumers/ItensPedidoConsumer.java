package br.com.order.consumers;

import br.com.order.dto.ItensPedidoDto;
import br.com.order.infra.ItemPedidoConverter;
import br.com.order.model.ItensPedidoModel;
import br.com.order.service.ItensPedidoService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ItensPedidoConsumer {
    @Autowired
    private ItensPedidoService pedidoService;
    @Autowired
    ItemPedidoConverter itemPedidoConverter;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("itemPedido-request-queue"),
            exchange = @Exchange(name = "itemPedido-request-exchanges"),
            key = "itemPedido-request-rout-key"))
    public void receberMensagemItensPedido(@Payload ItensPedidoDto itensPedidoDTO) {
        var itemPedidoModel = new ItensPedidoModel();
        itemPedidoModel = itemPedidoConverter.toEntity(itensPedidoDTO);

        System.out.println("OK" + itensPedidoDTO);
        System.out.println("ItensPedidoModel - OK" + itemPedidoModel);
        pedidoService.save(itemPedidoModel);
    }
}
