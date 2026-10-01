package com.phokaia.account.entities;

import java.time.YearMonth;
import java.math.BigDecimal;
import jakarta.persistence.*;

public class Card {
        @Column(nullable = false, length= 50)
        Customer holder;
        @Id 
        Integer cardNumber;
        @Column(nullable = false, length= 50)
        Integer securityNumber;
        @Column(nullable = false, length= 50)
        YearMonth expirationDate;
        @Column(nullable = false, length= 50)
        Account account;

        public Card(){}

        public Card(Customer holder, Integer cardNumber, Integer securityNumber, YearMonth expirationDate, Account account){
            this.holder = holder;
            this.cardNumber =cardNumber;
            this.securityNumber =  securityNumber;
            this.expirationDate = expirationDate;
            this.account = account;
        }

        public Customer getHolder(){
            return holder;
        }

        public Integer getCardNumber(){
            return cardNumber;
        }

        public Integer getSecurityNumber(){
            return securityNumber;
        }

        public YearMonth getExpirationDate(){
            return expirationDate;
        }

        public Account getAccount(){
            return account;
        }

        public void makePayment(Integer password, BigDecimal price){
            if(password.equals( securityNumber)){
                if(getAccount().getBalance().compareTo(price) >= 0){
                    getAccount().withdrawMoney(price);
                }
                else{
                    System.out.println("Account Balance not sufficinent");
                }
            }
            else{
                System.out.println("Password does not match");
            }
            
        }
}
