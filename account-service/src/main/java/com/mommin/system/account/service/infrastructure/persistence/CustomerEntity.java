package com.mommin.system.account.service.infrastructure.persistence;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
final class CustomerEntity {

    private final UUID customerId;
    private final String name;
    private final String surname;

    static CustomerEntity valueOf(Customer customer) {
        return new CustomerEntity(
                customer.getCustomerId().toUUID(),
                customer.getName(),
                customer.getSurname());
    }

    Customer toCustomer() {
        return new Customer(
                CustomerId.valueOf(customerId),
                name,
                surname
        );
    }
}
