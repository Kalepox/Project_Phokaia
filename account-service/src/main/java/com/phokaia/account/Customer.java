package com.phokaia.account;

import java.util.concurrent.*;

public class Customer {

    String name;
    String surname;
    String customerCode;
    DayMonthYear dateofBirth;

    public Customer(String name, String surname, String customerCode, Integer age){
        this.name = name;
        this.surname = surname;
        this.customerCode = createCustomerCode(name, surname);
        this.age = age;
    }

    public String createCustomerCode(String name, String surname){
        return "" + name.charAt(1) + surname.charAt(1) + ThreadLocalRandom.current().nextInt(0,10);
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

    public Integer getAge(){
        return age;
    }



}
