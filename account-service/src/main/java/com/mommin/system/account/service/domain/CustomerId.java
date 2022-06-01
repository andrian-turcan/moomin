package com.mommin.system.account.service.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static com.mommin.system.common.utils.Guards.notNull;
import static java.util.UUID.randomUUID;

@EqualsAndHashCode
public final class CustomerId {

    private final UUID value;

    private CustomerId(UUID value) {
        this.value = notNull(value, "The value must not be null.");
    }

    public static CustomerId newIdentity() {
        return new CustomerId(randomUUID());
    }

    public static CustomerId valueOf(UUID value) {
        return new CustomerId(value);
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

