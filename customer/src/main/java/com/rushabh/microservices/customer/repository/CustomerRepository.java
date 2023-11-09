package com.rushabh.microservices.customer.repository;

import com.rushabh.microservices.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
}
