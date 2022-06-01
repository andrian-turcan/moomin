package com.mommin.system.transaction.service.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static com.mommin.system.common.utils.Guards.notNull;
import static java.util.UUID.randomUUID;

@EqualsAndHashCode
public final class AccountWalletId {

    private final UUID value;

    private AccountWalletId(UUID value) {
        this.value = notNull(value, "The value must not be null.");
    }

    public static AccountWalletId newIdentity() {
        return new AccountWalletId(randomUUID());
    }

    public static AccountWalletId valueOf(UUID value) {
        return new AccountWalletId(value);
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

