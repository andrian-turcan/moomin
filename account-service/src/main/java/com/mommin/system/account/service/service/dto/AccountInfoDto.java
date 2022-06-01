package com.mommin.system.account.service.service.dto;

import com.mommin.system.account.service.domain.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountInfoDto {

    private final UUID accountId;
    private final UUID customerId;

    public static AccountInfoDto valueOf(Account account) {
        return new AccountInfoDto(
                account.getAccountId().toUUID(),
                account.getCustomerId().toUUID());
    }
}
