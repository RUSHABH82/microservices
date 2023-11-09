package com.rushabh.microservices.customer.service;

import com.rushabh.microservices.amqp.message.RabbitMQMessageProducer;
import com.rushabh.microservices.clients.fraud.FraudCheckResponse;
import com.rushabh.microservices.clients.fraud.FraudClient;
import com.rushabh.microservices.clients.notification.NotificationRequest;
import com.rushabh.microservices.customer.entity.Customer;
import com.rushabh.microservices.customer.model.CustomerRegistrationRequest;
import com.rushabh.microservices.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    private Base64.Encoder ENCODER = Base64.getEncoder();
    private Base64.Decoder DECODER = Base64.getDecoder();

    public CustomerService(CustomerRepository customerRepository,
                           FraudClient fraudClient,
                           RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {

        if (customerRepository.existsByEmail(ENCODER.encodeToString(request.email().getBytes(StandardCharsets.UTF_8)))) {
            LOGGER.warn("Email is already taken!");
            throw new IllegalStateException("Email is already taken!");
        }

        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(ENCODER.encodeToString(request.email().getBytes(StandardCharsets.UTF_8)));

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            LOGGER.warn("Customer is fraudster!");
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Rushabh Microservice...", customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }

    @Override
    public ResponseEntity<List<Customer>> getCustomers() {
        LOGGER.info("Getting customers data!");

        return new ResponseEntity<>(customerRepository.findAll().stream()
                .peek(customer ->
                        customer.setEmail(new String(DECODER.decode(customer.getEmail().getBytes(StandardCharsets.UTF_8)))))
                .collect(Collectors.toList()), HttpStatus.OK);

    }
}
