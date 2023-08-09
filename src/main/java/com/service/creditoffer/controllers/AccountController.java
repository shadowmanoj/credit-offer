package com.service.creditoffer.controllers;

import com.service.creditoffer.models.requests.CreateAccountRequest;
import com.service.creditoffer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{account-id}")
    public ResponseEntity<?> getAccountById(@PathVariable("account-id") Long accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        return new ResponseEntity<>(accountService.createAccount(createAccountRequest), HttpStatus.OK);
    }

}
