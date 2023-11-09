package com.rushabh.microservices.amqp.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQMessageProducer.class);
    private final AmqpTemplate amqpTemplate;

    public RabbitMQMessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(Object payload, String exchange, String routingKey) {
        LOGGER.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        LOGGER.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }

}
