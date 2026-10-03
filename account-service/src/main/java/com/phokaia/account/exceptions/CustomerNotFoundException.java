package com.phokaia.account.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customerCode){

        super("Customer " + customerCode + " not found");
    }

}
