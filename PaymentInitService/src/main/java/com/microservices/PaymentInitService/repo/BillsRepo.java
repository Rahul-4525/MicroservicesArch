package com.microservices.PaymentInitService.repo;

import com.microservices.PaymentInitService.BillData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillsRepo extends MongoRepository<BillData,String> {
}
