package com.mommin.system.account.service.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static com.mommin.system.common.utils.Guards.notNull;
import static java.util.UUID.randomUUID;

@EqualsAndHashCode
public final class AccountId {

    private final UUID value;

    private AccountId(UUID value) {
        this.value = notNull(value, "The value must not be null.");
    }

    public static AccountId newIdentity() {
        return new AccountId(randomUUID());
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

