package br.com.order.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoQueueConfig {

    private static final String PEDIDO_QUEUE = "pedido-request-queue";
    private static final String PEDIDO_EXCHANGE = "pedido-request-exchange";
    private static final String PEDIDO_ROUTING_KEY = "pedido-request-rout-key";

    private static final String DLQ_QUEUE = "pedido-request-dlq";
    private static final String DLQ_EXCHANGE = "pedido-dlq-exchange";
    private static final String DLQ_ROUTING_KEY = "pedido-dlq-rout-key";

    // Fila principal com DLQ configurada
    @Bean
    public Queue pedidoQueue() {
        return QueueBuilder.durable(PEDIDO_QUEUE)
                .deadLetterExchange(DLQ_EXCHANGE) // Define a exchange da DLQ
                .deadLetterRoutingKey(DLQ_ROUTING_KEY) // Define a chave de roteamento da DLQ
                .ttl(30000) // Tempo de vida da mensagem (30 segundos)
                .maxLength(5) // Limite de mensagens antes de descartar
                .build();
    }

    @Bean
    public Exchange pedidoExchange() {
        return ExchangeBuilder.directExchange(PEDIDO_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding bindingPedido() {
        return BindingBuilder.bind(pedidoQueue())
                .to(pedidoExchange())
                .with(PEDIDO_ROUTING_KEY)
                .noargs();
    }

    // Dead Letter Queue (DLQ)
    @Bean
    public Queue pedidoDLQ() {
        return QueueBuilder.durable(DLQ_QUEUE)
                .build();
    }

    @Bean
    public Exchange pedidoDLQExchange() {
        return ExchangeBuilder.directExchange(DLQ_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding bindingDLQ() {
        return BindingBuilder.bind(pedidoDLQ())
                .to(pedidoDLQExchange())
                .with(DLQ_ROUTING_KEY)
                .noargs();
    }
}
