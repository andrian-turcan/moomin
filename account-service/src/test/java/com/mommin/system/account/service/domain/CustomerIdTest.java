package com.mommin.system.account.service.domain;

import com.mommin.system.account.service.domain.CustomerId;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.mommin.system.account.service.domain.CustomerId.newIdentity;
import static com.mommin.system.account.service.domain.CustomerId.valueOf;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@Tag("unit")
class CustomerIdTest {

    @Test
    void generation() {
        assertThat(newIdentity())
                .as("non idempotent")
                .isNotEqualTo(newIdentity());
    }

    @Test
    void creation() {
        UUID uuid = randomUUID();
        assertThat(valueOf(uuid))
                .isNotNull()
                .extracting(CustomerId::toUUID)
                .isEqualTo(uuid);
    }

    @Test
    void creationFailsWhenUUIDIsNull() {
        assertThatNullPointerException()
                .isThrownBy(() -> valueOf(null))
                .withMessage("The value must not be null.");
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(CustomerId.class).verify();
    }
}
