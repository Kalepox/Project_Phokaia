package com.phokaia.account.entities;

import java.math.BigDecimal;

import com.phokaia.account.exceptions.CurrencyMismatchException;
import com.phokaia.account.exceptions.InsufficientFundsException;

import jakarta.persistence.*;

@Entity
@Table(name= "accounts")
public class Account {

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer holder;

    @Id @Column (updatable = false, length = 20)
    private String accountCode;
    @Column(nullable = false, precision = 19, scale =2)
    private BigDecimal balance;
    @Column(nullable = false, length = 3)
    private String currencyType;
    @Version 
    private Long version;

    protected Account(){}

    public Account(Customer holder, String currencyType){
        this.holder = holder;
        this.accountCode = generateAccountCode(holder.getCustomerCode(), currencyType);
        this.balance = BigDecimal.ZERO;
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
        if(input.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance = balance.add(input);
    }

    public void withdrawMoney(BigDecimal input){
        if(input.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(balance.compareTo(input) < 0){
            throw new InsufficientFundsException(accountCode, balance, input);
        }
        balance = balance.subtract(input);
    }

    public static String generateAccountCode(String customerCode, String currencyType){
        return ""  + currencyType + customerCode;
    }

    public void makeTransfer(Account recipient, BigDecimal amount){
        if (!recipient.currencyType.equals(currencyType)){
            throw new CurrencyMismatchException(currencyType, recipient.currencyType);
        }
        this.withdrawMoney(amount);
        recipient.addMoney(amount);
    }
}
    
