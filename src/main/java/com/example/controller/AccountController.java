package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
@RequestMapping("accounts")
public class AccountController {
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public AccountController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable("account_id") Integer account_id) {
        System.out.println("Account controller account id: " + account_id);
        List<Message> messages = messageService.getMessagesByAccountId(account_id);
        if (messages == null) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(200).body(messages);
        }
    }
}
