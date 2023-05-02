package com.jarikkomarik.notification;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public record NotificationController(NotificationService notificationService) {
    @PostMapping()
    public Long addNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationService.persistNotification(notificationDto);
        return notification.getId();
    }
}
