package com.microservices.AccountService.repo;

import com.microservices.AccountService.entity.AccountData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends MongoRepository<AccountData,String> {
   @Query("{accountNumber:'?0'}")
    AccountData findByAccountNumber(String accountNumber);
}
