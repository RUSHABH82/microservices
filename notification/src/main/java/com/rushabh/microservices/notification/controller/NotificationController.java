package com.rushabh.microservices.notification.controller;

import com.rushabh.microservices.clients.notification.NotificationRequest;
import com.rushabh.microservices.notification.service.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    private final INotificationService notificationService;

    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        LOGGER.info("New notification... {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
