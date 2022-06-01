package com.mommin.system.account.service.infrastructure.persistence;

import com.mommin.system.account.service.domain.Account;
import com.mommin.system.account.service.domain.AccountId;
import com.mommin.system.account.service.domain.CustomerId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
final class AccountEntity {

    private final UUID accountId;
    private final UUID customerId;

    static AccountEntity valueOf(Account account) {
        return new AccountEntity(
                account.getAccountId().toUUID(),
                account.getCustomerId().toUUID());
    }

    Account toAccount() {
        return new Account(
                AccountId.valueOf(accountId),
                CustomerId.valueOf(customerId)
        );
    }
}
