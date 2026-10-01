package com.phokaia.account.dtos;

import com.phokaia.account.entities.Account;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public final class AccountDTO {

    private AccountDTO(){}

    public record CreateRequest(
        @NotBlank @Size(max = 20)
        String customerCode,
        @NotBlank @Pattern(regexp = "[A-Z]{3}")
        String currencyType
    ){}

    public record MoneyRequest(
        @NotNull @Positive @Digits(integer = 17, fraction = 2)
        BigDecimal amount 
    ){}
        

    public record Response(String customerCode, String accountCode, String currencyType, BigDecimal balance){
        public static Response from (Account account){
            return new Response(
                account.getHolder().getCustomerCode(),
                account.getAccountCode(),
                account.getCurrencyType(),
                account.getBalance()
            );
        }
    }
}
