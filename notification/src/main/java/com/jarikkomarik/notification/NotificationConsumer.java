package com.jarikkomarik.notification;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public record NotificationConsumer(NotificationService notificationService) {

    @RabbitListener(queues = "${rabbitmq.queue.notification}")// providing queue name
    public void consumer(NotificationDto notificationDto) { // retrieving notification form message queue
        log.info("Consumed {} from queue", notificationDto);
        notificationService.persistNotification(notificationDto);  //passing to service
    }
}
