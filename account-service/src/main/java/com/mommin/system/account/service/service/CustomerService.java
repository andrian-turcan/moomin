package com.mommin.system.account.service.service;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;
import com.mommin.system.account.service.domain.repository.CustomerRepository;
import com.mommin.system.account.service.service.dto.CreateCustomerDto;
import com.mommin.system.account.service.service.dto.CustomerInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public final class CustomerService {

    private final CustomerRepository repository;

    public Optional<CustomerInfoDto> getCustomer(UUID customerId) {
        return repository.find(CustomerId.valueOf(customerId)).map(CustomerInfoDto::valueOf);
    }

    public UUID create(CreateCustomerDto createAccountDto) {
        var customer = Customer.valueOf(CustomerId.newIdentity(), createAccountDto.getName(), createAccountDto.getSurname());

        repository.save(customer);

        log.info("The customer was created. {}", customer);

        return customer.getCustomerId().toUUID();
    }
}
