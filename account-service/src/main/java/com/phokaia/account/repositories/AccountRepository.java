package com.phokaia.account.repositories;

import com.phokaia.account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByHolderCustomerCode(String customerCode);

}
