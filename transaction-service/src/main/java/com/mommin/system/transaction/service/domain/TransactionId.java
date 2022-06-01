package com.mommin.system.transaction.service.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static com.mommin.system.common.utils.Guards.notNull;
import static java.util.UUID.randomUUID;

@EqualsAndHashCode
public final class TransactionId {

    private final UUID value;

    private TransactionId(UUID value) {
        this.value = notNull(value, "The value must not be null.");
    }

    public static TransactionId valueOf(UUID value) {
        return new TransactionId(value);
    }

    public static TransactionId newIdentity() {
        return new TransactionId(randomUUID());
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

