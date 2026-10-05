package com.phokaia.account.exceptions;

public class CurrencyMismatchException extends RuntimeException {

    public CurrencyMismatchException( String fromCurrency, String toCurrency){

        super("Cannot transfer between " + fromCurrency + " and " + toCurrency + " accounts");
    }

}
