package com.phokaia.account.controllers;

import org.springframework.web.bind.annotation.*;
import com.phokaia.account.services.*;
import com.phokaia.account.dtos.AccountDTO;
import com.phokaia.account.dtos.CustomerDTO;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService, CustomerService customerService){
        this.accountService = accountService;
        this.customerService = customerService;
    }

    //Post
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO.Response create(@Valid @RequestBody AccountDTO.CreateRequest request){
        return accountService.createAccount(request);
    }

    @GetMapping 
    public AccountDTO.Response get(@PathVariable String accountCode){
        return accountService.getAccount(accountCode);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO.Response create(@Valid @RequestBody CustomerDTO.CreateRequest request){
        return customerService.createCustomer(request);
    }

    //GET  /customers/CUS048219384593
    @GetMapping("/{customerCode}")
    public CustomerDTO.Response get(@PathVariable String customerCode){
        return customerService.getCustomer(customerCode);
    }

}
