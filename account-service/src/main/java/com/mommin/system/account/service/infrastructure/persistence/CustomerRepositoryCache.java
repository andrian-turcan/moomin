package com.mommin.system.account.service.infrastructure.persistence;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;
import com.mommin.system.account.service.domain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
final class CustomerRepositoryCache implements CustomerRepository {

    private static final Set<CustomerEntity> _tmpCache = new LinkedHashSet<>();

    @Override
    public void save(Customer customer) {
        _tmpCache.add(CustomerEntity.valueOf(customer));
        log.info("Customer {} was modified.", customer.getCustomerId());
    }

    @Override
    public Optional<Customer> find(CustomerId customerId) {
        return _tmpCache.stream()
                .map(CustomerEntity::toCustomer)
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst();
    }
}
