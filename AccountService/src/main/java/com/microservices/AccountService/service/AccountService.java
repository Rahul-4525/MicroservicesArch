package com.microservices.AccountService.service;
import com.microservices.AccountService.entity.AccountData;
import com.microservices.AccountService.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    public Double getAccountBal(String accountNumber) {
        AccountData accountData = this.accountRepo.findByAccountNumber(accountNumber);
        return accountData.getBalance();
    }
    public void updateAccountData(AccountData accountData)
    {
        this.accountRepo.save(accountData);
    }
    public AccountData getAccount(String customerId)
    {
        return this.accountRepo.findByAccountNumber(customerId);
    }
}
