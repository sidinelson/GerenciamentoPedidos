package br.com.pedidos.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoQueueConfig {

    public static final String EXCHANGE_NAME = "pedido-request-exchange";
    public static final String QUEUE_NAME = "pedido-request-queue";
    public static final String ROUTING_KEY = "pedido-request-rout-key";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .deadLetterExchange("pedido-exchange-dlq")  // Define a DLQ
                .deadLetterRoutingKey("pedido-dlq-rout-key")
                .build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
