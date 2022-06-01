package com.mommin.system.account.service.infrastructure.persistence;

import com.mommin.system.account.service.domain.CustomerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.mommin.system.account.service.TestData.generateRandomCustomer;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
class CustomerRepositoryCacheTest {

    CustomerRepositoryCache repository;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepositoryCache();
    }

    @Test
    void addAndFindCustomer() {
        var customer = generateRandomCustomer();

        repository.save(customer);

        assertThat(repository.find(customer.getCustomerId()))
                .usingRecursiveComparison()
                .isEqualTo(of(customer));
    }

    @Test
    void unknownCustomer() {
        var customer = generateRandomCustomer();

        repository.save(customer);

        assertThat(repository.find(CustomerId.newIdentity()))
                .isEmpty();
    }
}