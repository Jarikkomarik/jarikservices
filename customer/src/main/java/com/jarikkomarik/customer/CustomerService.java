package com.jarikkomarik.customer;

import com.jarikkomarik.amqp.RabbitMqMessageProducer;
import com.jarikkomarik.clients.fraud.clients.NotificationClient;
import com.jarikkomarik.clients.fraud.dto.FraudCheckResponse;
import com.jarikkomarik.clients.fraud.clients.FraudClient;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient, RabbitMqMessageProducer messageProducer) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //TODO: check if email valid
        //TODO: check if email is not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("customer is fraudster");
        }

        messageProducer.publish(new NotificationDto(
                customer.getId(),
                LocalDateTime.now(),
                "Registered new Customer",
                "Customer Service",
                customer.getEmail()
        ), "internal.exchange", "internal.notification.routing-key");

    }
}
