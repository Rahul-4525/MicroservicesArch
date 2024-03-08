package com.microservices.AuthenticationService.repo;

import com.microservices.AuthenticationService.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends MongoRepository<Account,String> {
    @Query("{username:'?0'}")
    Account findAccountByUserName(String username);
}
