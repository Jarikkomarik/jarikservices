package com.jarikkomarik.clients.fraud.dto;

import java.time.LocalDateTime;

public record NotificationDto(
        Integer customerId,
        LocalDateTime sentAt,
        String message,
        String sender,
        String customerEmail
) {

}
