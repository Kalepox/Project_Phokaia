package com.phokaia.account.exceptions;
import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException( String accountCode, BigDecimal balance, BigDecimal requested){

        super("Account " + accountCode + " has insufficient funds, Balance: " + balance + " Requested amount: " + requested);
    }

}
