package com.rushabh.microservices.fraud.controller;

import com.rushabh.microservices.clients.fraud.FraudCheckResponse;
import com.rushabh.microservices.fraud.service.FraudCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    public static final Logger LOGGER = LoggerFactory.getLogger(FraudController.class);

    private final FraudCheckService fraudCheckService;

    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        LOGGER.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
