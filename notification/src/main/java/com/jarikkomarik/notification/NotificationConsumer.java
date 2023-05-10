package com.jarikkomarik.notification;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public record NotificationConsumer(NotificationService notificationService) {
    @KafkaListener(topics = "jarikservices", groupId = "${spring.kafka.group-id}")
    public void listenGroupFoo(NotificationDto notificationDto) {
        log.info("Consumed {} from queue", notificationDto);
        notificationService.persistNotification(notificationDto);  //passing to service
    }
}
