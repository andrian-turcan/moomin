package com.mommin.system.account.service.domain;

import com.mommin.system.account.service.domain.Customer;
import com.mommin.system.account.service.domain.CustomerId;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.mommin.system.account.service.domain.CustomerId.newIdentity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@Tag("unit")
class CustomerTest {

    @Test
    void creation() {
        assertThat(newIdentity())
                .as("non idempotent")
                .isNotEqualTo(newIdentity());
    }

    @Test
    void customerIdIsRequired() {
        var name = randomAlphabetic(5);
        var surname = randomAlphabetic(5);

        assertThatNullPointerException()
                .isThrownBy(() -> new Customer(null, name, surname))
                .withMessage("The customerId must not be null.");
    }

    @Test
    void nameIsRequired() {
        var customerId = CustomerId.newIdentity();
        var surname = randomAlphabetic(5);

        assertThatNullPointerException()
                .isThrownBy(() -> new Customer(customerId, null, surname))
                .withMessage("The name must not be null.");
    }

    @Test
    void surnameIsRequired() {
        var customerId = CustomerId.newIdentity();
        var name = randomAlphabetic(5);

        assertThatNullPointerException()
                .isThrownBy(() -> new Customer(customerId, name, null))
                .withMessage("The surname must not be null.");
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(Customer.class)
                .withOnlyTheseFields("customerId")
                .verify();
    }
}
