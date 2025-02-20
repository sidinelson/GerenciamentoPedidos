package br.com.order.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoQueueConfig {

    private static final String PEDIDO_QUEUE = "pedido-request-queue";
    private static final String PEDIDO_EXCHANGE = "pedido-request-exchange";
    private static final String PEDIDO_ROUTING_KEY = "pedido-request-rout-key";

    private static final String PEDIDO_DLQ_QUEUE = "pedido-queue-dlq";

    public static final String PEDIDO_DLQ_EXCHANGE = "order.exchange.dlq";
    private static final String PEDIDO_DLQ_ROUTING_KEY = "pedido-dlq-rout-key";


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(PEDIDO_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(PEDIDO_QUEUE)
                .deadLetterExchange(PEDIDO_DLQ_EXCHANGE)
                .deadLetterRoutingKey(PEDIDO_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(PEDIDO_ROUTING_KEY);
    }

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(PEDIDO_DLQ_EXCHANGE);
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(PEDIDO_DLQ_QUEUE).build();
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue()).to(dlqExchange()).with(PEDIDO_DLQ_ROUTING_KEY);
    }
}
