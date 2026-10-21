package com.phokaia.account.controllers;

import org.springframework.web.bind.annotation.*;
import com.phokaia.account.services.*;
import com.phokaia.account.dtos.AccountDTO;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;

@RestController 
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //Post
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO.Response create(@Valid @RequestBody AccountDTO.CreateRequest request){
        return accountService.createAccount(request);
    }

    @GetMapping("/{accountCode}")
    public AccountDTO.Response get(@PathVariable String accountCode){
        return accountService.getAccount(accountCode);
    }

    @PostMapping("/{accountCode}/deposit")
    public AccountDTO.Response deposit(@PathVariable String accountCode, @Valid @RequestBody AccountDTO.MoneyRequest request){
        return accountService.deposit(accountCode, request);
    }

    @GetMapping 
    public List<AccountDTO.Response> getByCustomer(@RequestParam String customerCode){
        return accountService.getAccountsOfCustomer(customerCode);
    }


}
