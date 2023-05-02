package com.jarikkomarik.notification;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import org.springframework.stereotype.Service;


@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public Notification persistNotification(NotificationDto notificationDto) {
        return notificationRepository.save(
                Notification.builder()
                        .customerId(notificationDto.customerId())
                        .sentAt(notificationDto.sentAt())
                        .message(notificationDto.message())
                        .sender(notificationDto.sender())
                        .customerEmail(notificationDto.customerEmail())
                        .build()
        );
    }
}
