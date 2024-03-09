package com.microservices.AccountService.repo;

import com.microservices.AccountService.entity.AccountData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<AccountData,Integer> {
    AccountData findByAccountNumber(String accountNumber);
}
