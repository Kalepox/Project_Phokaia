package com.phokaia.account.services;

import com.phokaia.account.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phokaia.account.dtos.AccountDTO;
import com.phokaia.account.entities.*;
import com.phokaia.account.exceptions.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional 
    public AccountDTO.Response createAccount(AccountDTO.CreateRequest request){
        Customer customer = customerRepository.findById(request.customerCode())
                .orElseThrow(() -> new CustomerNotFoundException(request.customerCode()));

        String code = Account.generateAccountCode(request.customerCode(), request.currencyType());
        if(accountRepository.existsById(code)){
            throw new AccountAlreadyExistsException(request.customerCode(), request.currencyType());
        }

        Account account = new Account(customer, request.currencyType());
        Account saved = accountRepository.save(account);
        return AccountDTO.Response.from(saved);
    }

    @Transactional(readOnly = true)
    public AccountDTO.Response getAccount(String accountCode){
        Account account = accountRepository.findById(accountCode)
            .orElseThrow(()-> new AccountNotFoundException(accountCode));
        
        return AccountDTO.Response.from(account);
    }

}
