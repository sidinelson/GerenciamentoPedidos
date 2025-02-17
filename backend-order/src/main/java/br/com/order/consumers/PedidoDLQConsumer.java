package br.com.order.consumers;

import br.com.order.dto.PedidoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoDLQConsumer {

    @RabbitListener(queues = "pedido-request-dlq")
    public void processarMensagensDLQ(@Payload PedidoDto pedidoDTO) {
        System.err.println("⚠️ Pedido movido para DLQ: " + pedidoDTO);
        // Aqui você pode salvar no banco ou enviar alerta para análise manual
    }
}
