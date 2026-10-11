package com.phokaia.controllers;

import org.springframework.web.bind.annotation.*;
import com.phokaia.account.services.*;
import com.phokaia.account.dtos.AccountDTO;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO.Response create(@Valid @RequestBody AccountDTO.CreateRequest request){
        return accountService.createAccount(request);
    }



}
