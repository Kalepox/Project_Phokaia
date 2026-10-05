package com.phokaia.account.services;

import org.springframework.stereotype.Service;
import com.phokaia.account.repositories.*;
import com.phokaia.account.dtos.CustomerDTO;
import com.phokaia.account.entities.Customer;
import org.springframework.transaction.annotation.Transactional;
import com.phokaia.account.exceptions.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional 
    public CustomerDTO.Response createCustomer(CustomerDTO.CreateRequest request){
        String code;
        do{
            code = Customer.generateCustomerCode();
        }
        while(customerRepository.existsById(code));

        Customer customer = new Customer(request.name(), request.surname(), code);
        Customer saved = customerRepository.save(customer);
        return CustomerDTO.Response.from(saved);
    }

    @Transactional(readOnly = true)
    public CustomerDTO.Response getCustomer(String customerCode){

        Customer customer = customerRepository.findById(customerCode)
            .orElseThrow(()-> new CustomerNotFoundException(customerCode));
        
        return CustomerDTO.Response.from(customer);
    }

}
