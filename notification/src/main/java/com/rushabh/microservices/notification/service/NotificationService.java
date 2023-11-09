package com.rushabh.microservices.notification.service;

import com.rushabh.microservices.clients.notification.NotificationRequest;
import com.rushabh.microservices.notification.entity.Notification;
import com.rushabh.microservices.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        notification.setToCustomerId(notificationRequest.toCustomerId());
        notification.setToCustomerEmail(notificationRequest.toCustomerName());
        notification.setSender("Rushabh");
        notification.setMessage(notificationRequest.message());
        notificationRepository.save(notification);
    }
}
