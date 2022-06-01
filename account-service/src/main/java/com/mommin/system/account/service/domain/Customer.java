package com.mommin.system.account.service.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.mommin.system.common.utils.Guards.notNull;

@Getter
@EqualsAndHashCode(of = "customerId")
@ToString
public final class Customer {

    private final CustomerId customerId;
    private final String name;
    private final String surname;

    public Customer(CustomerId customerId, String name, String surname) {
        this.customerId = notNull(customerId, "The customerId must not be null.");
        this.name = notNull(name, "The name must not be null.");
        this.surname = notNull(surname, "The surname must not be null.");
    }

    public static Customer valueOf(CustomerId customerId, String name, String surname) {
        return new Customer(customerId, name, surname);
    }
}