package com.rushabh.microservices.customer.model;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
