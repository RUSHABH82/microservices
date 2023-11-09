package com.rushabh.microservices.fraud.service;

import com.rushabh.microservices.fraud.entity.FraudCheckHistory;
import com.rushabh.microservices.fraud.repository.FraudCheckHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckService implements IFraudCheckService {


    public static final Logger LOGGER = LoggerFactory.getLogger(FraudCheckService.class);
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        FraudCheckHistory fraudCheckHistory = new FraudCheckHistory();
        fraudCheckHistory.setCustomerId(customerId);
        fraudCheckHistory.setFraudster(Boolean.FALSE);
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        LOGGER.info("checking is fraud for customer id={}", customerId);
        return false;
    }
}
