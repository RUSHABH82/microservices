package com.rushabh.microservices.fraud.service;

public interface IFraudCheckService {

    boolean isFraudulentCustomer(Integer customerId);
}
