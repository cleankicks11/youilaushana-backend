package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Boolean doesUserExist(Integer userId){
        System.out.println("Does user exist in Service, id: " + userId);
        return accountRepository.existsById(userId);
    }

    public Account registerUser(Account account){
        Optional<Account> registerOptional = accountRepository.findByUsername(account.getUsername());
        if(registerOptional.isPresent()){
            return null;
        }else{
            return accountRepository.save(account);
        }
    }

    public Account loginUser(Account account){
        Optional<Account> loginOptional = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        System.out.println("login data:" + loginOptional);
        if(loginOptional.isPresent()){
            System.out.println("login data:" + loginOptional); 
            return loginOptional.get();
        } else {
            return null;
        }
    }

    public Account accountById(Integer accountId){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        } else {
            return null;
        }
    }
}
