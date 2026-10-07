package com.phokaia.controllers;

import org.springframework.web.bind.annotation.*;
import com.phokaia.account.dtos.CustomerDTO;
import com.phokaia.account.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController 
@RequestMapping("/customers")
public class CustomerControllers {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;

    }

    //Post  /customers   body: { "name": "Ali", "surname": "Yilmaz" }
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO.Response create(@Valid @RequestBody CustomerDTO.CreateRequest request){
        return customerService.createCustomer(request);
    }

    //GET  /customers/CUS048219384593
    @GetMapping("/{customerCode}")
    public CustomerDTO.Request get(@PathVariablen String customerCode){
        return customerService.getCustomer(customerCode);
    }

    

}
