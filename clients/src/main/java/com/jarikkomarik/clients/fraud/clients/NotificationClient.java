package com.jarikkomarik.clients.fraud.clients;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(
        value = "notification",
        url = "${clients.notification.url}",
        path = "api/v1/notification"
)
public interface NotificationClient {
    @PostMapping()
    public Long addNotification(@RequestBody NotificationDto notificationDto);
}
