package com.jarikkomarik.customer;

import com.jarikkomarik.amqp.RabbitMqMessageProducer;
import com.jarikkomarik.clients.fraud.clients.NotificationClient;
import com.jarikkomarik.clients.fraud.dto.FraudCheckResponse;
import com.jarikkomarik.clients.fraud.clients.FraudClient;

import com.jarikkomarik.clients.fraud.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient,
        NotificationClient notificationClient,
        KafkaTemplate<String, NotificationDto> kafkaTemplate
) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("customer is fraudster");
        }

        kafkaTemplate.send("jarikservices", new NotificationDto(
                customer.getId(),
                LocalDateTime.now(),
                "Registered new Customer",
                "Customer Service",
                customer.getEmail()
        ));

    }
}
