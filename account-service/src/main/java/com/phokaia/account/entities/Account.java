package com.phokaia.account.entities;

import java.math.BigDecimal;
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
        balance = balance.add(input);
    }

    public void withdrawMoney(BigDecimal input){
        balance = balance.subtract(input);
    }

    public static String generateAccountCode(String customerCode, String currencyType){
        return ""  + currencyType + customerCode;
    }

    public void makeTransfer(Account recipient, BigDecimal amount){
        if(currencyType.equals(recipient.currencyType)){
            if(balance.compareTo(amount) < 0 || amount.compareTo(BigDecimal.ZERO)<= 0){
                System.out.println("Balance not sufficient");
            }
            else{
                recipient.addMoney(amount);
                this.withdrawMoney(amount);
            }
        }
        else{
            System.out.println("Ïncorrect Currency type");
        }
    }
}
