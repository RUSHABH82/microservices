package com.rushabh.microservices.customer.controller;

import com.rushabh.microservices.customer.entity.Customer;
import com.rushabh.microservices.customer.model.CustomerRegistrationRequest;
import com.rushabh.microservices.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        LOGGER.info("new customer registration {}", customerRegistrationRequest.toString());
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> registerCustomer() {
        return customerService.getCustomers();
    }
}
