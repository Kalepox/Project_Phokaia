package com.phokaia.account;

import java.math.BigDecimal;

public class Account {
    
    Customer holder;
    String accountCode;
    BigDecimal balance;
    String currencyType;
    //String AccountType; //1 type only for now

    public Account(Customer holder, String accountCode, BigDecimal balance, String currencyType){
        this.holder = holder;
        this.accountCode = accountCode;
        this.balance = balance;
        this.currencyType = currencyType;
       
    }

    public Customer getHolder(){
        return holder;
    }

    public String getAccountCode(){
        return accountCode;
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public String getCurrencyType(){
        return currencyType;
    }

    public void addMoney(BigDecimal input ){
        balance = balance.add(input);
    }

    public void withdrawMoney(BigDecimal input){
        balance = balance.subtract(input);
    }

    public void makeTransfer(Account recipient, BigDecimal amount){
        if(currencyType.equals(recipient.currencyType)){
            if(balance.compareTo(amount) < 0 || amount.compareTo(BigDecimal.ZERO)<= 0){
                recipient.addMoney(amount);
                this.withdrawMoney(balance);
            }
            else{
                System.out.println("Balance not sufficient");
            }
        }
        else{
            System.out.println("Ïncorrect Currency type");
        }
    }
}
