package com.mommin.system.account.service;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class TestData {

    public static Customer generateRandomCustomer() {
        var customerId = CustomerId.newIdentity();
        var name = randomAlphabetic(5);
        var surname = randomAlphabetic(5);

        return new Customer(customerId, name, surname);
    }
}
