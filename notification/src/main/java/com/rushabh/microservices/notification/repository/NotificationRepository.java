package com.rushabh.microservices.notification.repository;

import com.rushabh.microservices.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
