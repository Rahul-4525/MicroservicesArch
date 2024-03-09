package com.microservices.PaymentInitService.repo;

import com.microservices.PaymentInitService.BillData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepo extends JpaRepository<BillData,Integer> {
}
