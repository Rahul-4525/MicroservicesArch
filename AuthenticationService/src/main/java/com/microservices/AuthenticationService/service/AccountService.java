package com.microservices.AuthenticationService.service;

import com.microservices.AuthenticationService.entity.Account;
import com.microservices.AuthenticationService.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    public Account addAccount(Account account)
    {
        account.setPassword(Utility.encode(account.getPassword()));
        return this.accountRepo.save(account);
    }
    public Account getAccountDetails(String username) {
        return accountRepo.findAccountByUserName(username);
    }

    public boolean verifyAccount(Account account, String pwd) {
        return Utility.decode(pwd.trim()).equals(account.getPassword());
    }

    private class Utility {
        public static String encode(String str) {
            String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
            return encodedString;
        }

        public static String decode(String str) {
            System.out.println(str+" "+true);
            byte[] decodedBytes = Base64.getDecoder().decode(str);
            System.out.println(decodedBytes);
            String decodedString = new String(decodedBytes);
            System.out.println(decodedString);
            return decodedString;
        }
    }
}
