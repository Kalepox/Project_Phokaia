package com.phokaia.account.entities;

import java.util.concurrent.*;
import jakarta.persistence.*;



@Entity
@Table( name = "customers")
public class Customer {

    private static final String CODE_PREFIX = "CUS";

    @Column(nullable = false, length= 50)
    private String name;

    @Column(nullable = false, length= 50)
    private String surname;

    @Id @Column(length = 20, updatable = false)
    private String customerCode;
    @Version 
    private Long version;

    protected Customer(){}

    public Customer(String name, String surname, String customerCode){
        this.name = name;
        this.surname = surname;
        this.customerCode = customerCode;
       
    }

    public static String generateCustomerCode() {
        long number = ThreadLocalRandom.current().nextLong(100_000_000L);   
        return CODE_PREFIX + String.format("%08d", number);                 
    }
    
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
    public String getCustomerCode(){
        return customerCode;
    }

}
