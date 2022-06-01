package com.mommin.system.account.service.service.dto;

import com.mommin.system.account.service.domain.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public final class CustomerInfoDto {

    private final UUID customerId;
    private final String name;
    private final String surname;

    public static CustomerInfoDto valueOf(Customer customer) {
        return new CustomerInfoDto(
                customer.getCustomerId().toUUID(),
                customer.getName(),
                customer.getSurname()
        );
    }
}
