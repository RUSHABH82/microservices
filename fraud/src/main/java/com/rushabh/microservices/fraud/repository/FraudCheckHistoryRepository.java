package com.rushabh.microservices.fraud.repository;

import com.rushabh.microservices.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
