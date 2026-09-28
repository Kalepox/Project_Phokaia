package com.phokaia.account;

import java.time.YearMonth;
import java.math.BigDecimal;

public class Card {

        Customer holder;
        Integer cardNumber;
        Integer securityNumber;
        YearMonth expirationDate;
        Account account;

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

        public void makePayment(Account account, Integer password, BigDecimal price){
            if(password == securityNumber){
                if(account.getBalance().compareTo(price) < 0){
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
