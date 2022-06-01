package com.mommin.system.transaction.service.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static com.mommin.system.common.utils.Guards.notNull;

@EqualsAndHashCode
public final class AccountId {

    private final UUID value;

    private AccountId(UUID value) {
        this.value = notNull(value, "The value must not be null.");
    }
    public static AccountId valueOf(UUID value) {
        return new AccountId(value);
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

