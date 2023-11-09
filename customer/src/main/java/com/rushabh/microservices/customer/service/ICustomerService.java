package com.rushabh.microservices.customer.service;

import com.rushabh.microservices.customer.entity.Customer;
import com.rushabh.microservices.customer.model.CustomerRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

;

public interface ICustomerService {

     void registerCustomer(CustomerRegistrationRequest request);

    ResponseEntity<List<Customer>> getCustomers();
}
