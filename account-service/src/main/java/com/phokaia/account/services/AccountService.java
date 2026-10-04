package com.phokaia.account.services;

import com.phokaia.account.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phokaia.account.dtos.AccountDTO;
import com.phokaia.account.entities.*;
import com.phokaia.account.exceptions.*;
import java.util.List;

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

    //Helper Function
    private Account findAccount(String accountCode){
        return accountRepository.findById(accountCode)
            .orElseThrow(()-> new AccountNotFoundException(accountCode));
    }

    @Transactional(readOnly = true)
    public AccountDTO.Response getAccount(String accountCode){
        return AccountDTO.Response.from(findAccount(accountCode));
    }

    @Transactional 
    public AccountDTO.Response deposit(String accountCode, AccountDTO.MoneyRequest request){
        Account account = findAccount(accountCode);
        account.addMoney(request.amount());
        return AccountDTO.Response.from(account);
    }

     @Transactional(readOnly = true)
    public List<AccountDTO.Response> getAccountsOfCustomer(String customerCode){

        if(!customerRepository.existsById(customerCode)){
            throw new CustomerNotFoundException(customerCode);
        }
        List<Account> accounts = accountRepository.findByHolderCustomerCode(customerCode);
         return accounts.stream().map(AccountDTO.Response :: from).toList();
    }

}

