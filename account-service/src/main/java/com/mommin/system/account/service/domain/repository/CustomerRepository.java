package com.mommin.system.account.service.domain.repository;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;

import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);

    Optional<Customer> find(CustomerId customerId);
}
