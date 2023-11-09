package com.rushabh.microservices.notification.rabbitmq;

import com.rushabh.microservices.clients.notification.NotificationRequest;
import com.rushabh.microservices.notification.service.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

    private final INotificationService notificationService;

    public NotificationConsumer(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        LOGGER.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}