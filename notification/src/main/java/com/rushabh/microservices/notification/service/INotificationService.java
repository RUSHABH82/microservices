package com.rushabh.microservices.notification.service;

import com.rushabh.microservices.clients.notification.NotificationRequest;

public interface INotificationService {

    void send(NotificationRequest notificationRequest);

}
