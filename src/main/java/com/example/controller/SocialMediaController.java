package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;

    @Autowired
    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    //Register user controller
    @PostMapping("register")
    public ResponseEntity<Account> registerUser(@RequestBody Account userInfo){
        Account submittedInfo = accountService.registerUser(userInfo);
        System.out.println(submittedInfo);
        if(submittedInfo != null){
            return ResponseEntity.status(200).body(submittedInfo);
        } else {
            return ResponseEntity.status(409).build();
        }
    } 

    @PostMapping("login")
    public ResponseEntity<Account> loginUser(@RequestBody Account loginData){
        Account submittedInfo = accountService.loginUser(loginData);
        System.out.println("data: " + submittedInfo);
        if(submittedInfo != null){
            return ResponseEntity.status(200).body(submittedInfo);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
