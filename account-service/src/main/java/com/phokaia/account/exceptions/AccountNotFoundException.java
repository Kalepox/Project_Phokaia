package com.phokaia.account.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String accountCode){

        super("Account " + accountCode + " not found");
    }

}
