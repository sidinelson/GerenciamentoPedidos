package br.com.order.consumers;

import br.com.order.dto.PedidoDTO;
import br.com.order.infra.PedidoConverter;
import br.com.order.model.PedidoModel;
import br.com.order.producers.PedidoProducer;
import br.com.order.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;

@Slf4j
@Component
public class PedidoConsumer {
    @Autowired
    private PedidoProducer pedidoProducer;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    PedidoConverter pedidoConverter;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("pedido-request-queue"),
            exchange = @Exchange(name = "pedido-request-exchange"),
            key = "pedido-request-rout-key"))
    public void receberMensagemPedido(@Payload PedidoDTO pedidoDTO) {
        var pedidoModel = new PedidoModel();
        pedidoModel = pedidoConverter.toEntity(pedidoDTO);

        System.out.println("OK"+pedidoDTO);
        System.out.println("pedidoModel - OK"+pedidoModel);
        pedidoService.save(pedidoModel);
    }

}

