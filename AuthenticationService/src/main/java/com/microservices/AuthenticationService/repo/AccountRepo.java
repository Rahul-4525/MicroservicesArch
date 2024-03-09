package com.microservices.AuthenticationService.repo;

import com.microservices.AuthenticationService.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    Account findAccountByUserName(String username);
}
