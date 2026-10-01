package com.phokaia.account.dtos;

import com.phokaia.account.entities.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class CustomerDTO {

    private CustomerDTO(){}

    public record CreateRequest(
        @NotBlank @Size(max = 50)
        String name,
        @NotBlank @Size(max=50)
        String surname
    ){}

    public record Response(String customerCode, String name, String surname){
        public static Response from(Customer customer){
            return new Response(
                customer.getCustomerCode(),
                customer.getName(),
                customer.getSurname()
            );
        }
    }

}
