package com.phokaia.account.repositories;

import com.phokaia.account.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
