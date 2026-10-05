package com.phokaia.account.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String customerCode, String currencyType){

        super("Customer " + customerCode + " already has a " + currencyType + " account");
    }

}
